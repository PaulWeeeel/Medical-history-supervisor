package com.hwwz.medicalhistorysupervisor.service.impl;

import com.hwwz.medicalhistorysupervisor.domain.*;
import com.hwwz.medicalhistorysupervisor.repository.CaseHistoryRepository;
import com.hwwz.medicalhistorysupervisor.repository.MedicineRecordRepository;
import com.hwwz.medicalhistorysupervisor.service.*;
import com.hwwz.medicalhistorysupervisor.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/8/008 12:10
 */
@Service("MedicineService")
public class MedicineRecordServiceImpl implements MedicineRecordService {

	@Autowired
	private MedicineRecordRepository medicineRecordRepository;
    @Autowired
    private MedicineRecordService medicineRecordService;

    @Autowired
    private CaseHistoryRepository caseHistoryRepository;

    @Autowired
    private CaseHistoryService caseHistoryService;

    @Autowired
    private StockService stockService;

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private PatientService patientService;

    @Override
    public void add(MedicineRecord medicineRecord,Integer patientId) {
        //补全总量字段
        medicineRecord.setTotalDose(medicineRecord.getDuration()*medicineRecord.getTimeInterval());
        //更新库存
        Stock stock=stockService.getByName(medicineRecord.getMedicine());
        stock.setStock(stock.getStock()-medicineRecord.getTotalDose());
        stockService.update(stock);

        //新增开药记录
        CaseHistory caseHistory=caseHistoryRepository.getLastestCaseHistoryByPatientId(patientId);
        medicineRecord.setCaseHistory(caseHistory);
        medicineRecord.setStock(stock);
        medicineRecordRepository.save(medicineRecord);

        //更新总消费金额
        Double fee=medicineRecord.getTotalDose()*(double)stock.getUnitPrice();
        caseHistory.setFee(Common.nullToZero(caseHistory.getFee())+fee);
        caseHistoryService.update(caseHistory);
        //更新病人消费总金额
        Patient patient=patientService.getPatientById(patientId);
        patient.setTotalFee(Common.nullToZero(patient.getTotalFee())+fee);
        patientService.updatePatient(patient);
        //新增收支记录
        Payment payment=new Payment();
        payment.setType("开药");
        payment.setDateTime(Common.getCurTimeString());
        payment.setNumber(fee);
        paymentService.add(payment);

    }

    @Override
    public List<MedicineRecord> getAllMedicineRecord() {
        return medicineRecordRepository.findAll();
    }

    @Override
    public MedicineRecord getById(Integer id) {
        return medicineRecordRepository.getOne(id);
    }

    @Override
    public List<MedicineRecord> getByCaseHistoryId(Integer caseHistoryId) {
        return medicineRecordRepository.findByCaseHistoryId(caseHistoryId);
    }

    @Override
    public void update(MedicineRecord medicineRecord) {
        medicineRecordRepository.save(medicineRecord);
    }

    @Override
    public void delete(Integer id) {
        medicineRecordRepository.delete(id);
    }
}
