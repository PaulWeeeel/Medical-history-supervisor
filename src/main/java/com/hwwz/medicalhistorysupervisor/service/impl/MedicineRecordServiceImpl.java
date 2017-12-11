package com.hwwz.medicalhistorysupervisor.service.impl;

import com.hwwz.medicalhistorysupervisor.domain.MedicineRecord;
import com.hwwz.medicalhistorysupervisor.repository.MedicineRecordRepository;
import com.hwwz.medicalhistorysupervisor.service.MedicineRecordService;
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


    @Override
    public void add(MedicineRecord medicineRecordRecord) {
        medicineRecordRepository.save(medicineRecordRecord);
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
