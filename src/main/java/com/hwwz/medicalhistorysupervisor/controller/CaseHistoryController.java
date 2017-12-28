package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.configuration.Authorization;
import com.hwwz.medicalhistorysupervisor.domain.CaseHistory;
import com.hwwz.medicalhistorysupervisor.service.CaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

/**
 * @author: Aliweea
 * @date: 2017/12/5/005 11:06
 */
@Controller
@RequestMapping(value = "/case-history")
@Authorization//该类方法都需登录
public class CaseHistoryController {

	@Autowired
	private CaseHistoryService caseHistoryService;

//	@RequestMapping("/")
//	public String index() {
//		return "redirect:/case-history/list";
//	}
//
//	@GetMapping(value = "/list")
//	public String list(Model model) {
//		List<CaseHistory> caseHistoryList = caseHistoryService.getAllCaseHistory();
//		model.addAttribute("caseHistoryList", caseHistoryList);
//		return "case-history/list";
//	}

	@GetMapping(value = "/toAdd", params = {"patientId"})
	public String toAdd(Model model, @RequestParam("patientId") Integer patientId) {
		model.addAttribute("patientId", patientId);
		return "case-history/add";
	}

	@PostMapping(value = "/add")
	public String add(@Valid CaseHistory caseHistory, @RequestParam("patientId") Integer patientId) {
		caseHistoryService.add(caseHistory, patientId);
		return "redirect:/case-history/list";
	}


//	@GetMapping(value = "/toEdit", params = {"id"})
//	public String toEdit(Model model, @RequestParam("id") Integer id) {
//		CaseHistory caseHistory = caseHistoryService.getById(id);
//		model.addAttribute("caseHistory", caseHistory);
//		return "case-history/edit";
//	}
//
//	@PutMapping(value = "/edit")
//	public String edit(@Valid CaseHistory caseHistory) {
//		caseHistoryService.update(caseHistory);
//		return "redirect:/case-history/list";
//	}
//
//	@DeleteMapping(value = "/delete", params = {"id"})
//	public String delete(@RequestParam("id") Integer id){
//		caseHistoryService.delete(id);
//		return "redirect:/case-history/list";
//	}

}
