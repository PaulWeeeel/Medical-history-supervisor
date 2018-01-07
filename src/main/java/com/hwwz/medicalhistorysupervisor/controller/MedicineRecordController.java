package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.configuration.Authorization;
import com.hwwz.medicalhistorysupervisor.domain.CaseHistory;
import com.hwwz.medicalhistorysupervisor.domain.MedicineRecord;
import com.hwwz.medicalhistorysupervisor.domain.Payment;
import com.hwwz.medicalhistorysupervisor.domain.Stock;
import com.hwwz.medicalhistorysupervisor.repository.CaseHistoryRepository;
import com.hwwz.medicalhistorysupervisor.service.CaseHistoryService;
import com.hwwz.medicalhistorysupervisor.service.MedicineRecordService;
import com.hwwz.medicalhistorysupervisor.service.PaymentService;
import com.hwwz.medicalhistorysupervisor.service.StockService;
import com.hwwz.medicalhistorysupervisor.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Aliweea
 * @date: 2017/12/8/008 11:56
 */

@Controller
@RequestMapping(value = "/medicine")
@Authorization//该类方法都需登录
public class MedicineRecordController {
	
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



	@GetMapping(value = "/medicine")
	public String list(Model model) {
		model.addAttribute("medicineList", medicineRecordService.getAllMedicineRecord());
		return "patient/today";
	}

	@PostMapping(value = "/add")
	public String add(@Valid MedicineRecord medicineRecord,@RequestParam("patientId")Integer patientId) throws Exception{
		//更新库存
		Stock stock=stockService.getByName(medicineRecord.getMedicine());
		stock.setStock(stock.getStock()-medicineRecord.getTotalDose());
		stockService.update(stock);

		//新增开药记录
		CaseHistory caseHistory=caseHistoryRepository.getLastestCaseHistoryByPatientId(patientId);
		medicineRecord.setCaseHistory(caseHistory);
		medicineRecord.setStock(stock);
		medicineRecordService.add(medicineRecord);

		//更新总消费金额
		Double fee=medicineRecord.getTotalDose()*(double)stock.getUnitPrice();
		Double oldFee=caseHistory.getFee();
		oldFee=(oldFee==null)?0:oldFee;
		caseHistory.setFee(oldFee+fee);
		caseHistoryService.update(caseHistory);
		//新增收支记录
		Payment payment=new Payment();
		payment.setType("开药");
		payment.setDateTime(Common.getCurTimeString());
		payment.setNumber(fee);
		paymentService.add(payment);

		return "patient/today";
	}

	@PutMapping(value = "/edit")
	public String edit(@Valid MedicineRecord medicineRecord) {
		medicineRecordService.update(medicineRecord);
		return "patient/today";
	}


	@DeleteMapping(value = "/delete")
	public String delete(@RequestParam("id") Integer id) {
		medicineRecordService.delete(id);
		return "patient/today";
	}
}
