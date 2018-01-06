package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.configuration.Authorization;
import com.hwwz.medicalhistorysupervisor.domain.Payment;
import com.hwwz.medicalhistorysupervisor.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/8/008 12:34
 */
@Controller
@RequestMapping(value = "/record")
@Authorization//该类方法都需登录
public class RecordController {
	
	@Autowired
	private PaymentService paymentService;

	@RequestMapping("/")
	public String index() {
		return "redirect: /record/list";
	}

	@GetMapping(value = "/list")
	public String list(Model model) {
		List<Payment> paymentList = paymentService.getAllPayments();
		model.addAttribute("Payments", paymentList);
		return "/record/list";
	}

	@RequestMapping(value = "/add")
	public void add(@RequestBody  Payment payment) {
	    payment.setId(null);
		paymentService.add(payment);
	}

	@RequestMapping(value = "/edit")
	public void edit(@RequestBody  Payment payment) {
		paymentService.update(payment);
	}

	@RequestMapping(value = "/delete", params = {"id"})
	public void delete(@RequestParam("id") Integer id){
		paymentService.delete(id);
	}
}
