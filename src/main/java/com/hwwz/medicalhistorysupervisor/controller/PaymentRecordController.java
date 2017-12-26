package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.configuration.Authorization;
import com.hwwz.medicalhistorysupervisor.domain.PaymentRecord;
import com.hwwz.medicalhistorysupervisor.service.PaymentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/8/008 12:34
 */
@Controller
@RequestMapping(value = "/payment-record")
@Authorization//该类方法都需登录
public class PaymentRecordController {
	
	@Autowired
	private PaymentRecordService paymentRecordService;

	@RequestMapping("/")
	public String index() {
		return "redirect: /payment-record/list";
	}

	@GetMapping(value = "/list")
	public String list(Model model) {
		List<PaymentRecord> paymentRecordList = paymentRecordService.getAllPaymentRecords();
		model.addAttribute("paymentRecords", paymentRecordList);
		return "payment-record/list";
	}

	@PostMapping(value = "/add")
	public String add(@Valid PaymentRecord paymentRecord) {
		paymentRecord.setDateTime(new Timestamp(System.currentTimeMillis()));
		paymentRecordService.add(paymentRecord);
		return "redirect:/payment-record/list";
	}


	@GetMapping(value = "/toEdit", params = {"id"})
	public String toEdit(Model model, @RequestParam("id") Integer id) {
		PaymentRecord paymentRecord = paymentRecordService.getById(id);
		model.addAttribute("paymentRecord", paymentRecord);
		return "payment-record/edit";
	}

	@PutMapping(value = "/edit")
	public String edit(@Valid PaymentRecord paymentRecord) {
		paymentRecordService.update(paymentRecord);
		return "redirect:/payment-record/list";
	}

	@DeleteMapping(value = "/delete", params = {"id"})
	public String delete(@RequestParam("id") Integer id){
		paymentRecordService.delete(id);
		return "redirect:/payment-record/list";
	}
}
