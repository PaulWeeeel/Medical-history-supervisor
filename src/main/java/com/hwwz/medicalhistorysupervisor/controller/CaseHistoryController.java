package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.domain.CaseHistory;
import com.hwwz.medicalhistorysupervisor.service.CaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.sql.Timestamp;
import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/5/005 11:06
 */
@Controller
@RequestMapping(value = "/case-history")
public class CaseHistoryController {

	@Autowired
	private CaseHistoryService caseHistoryService;

	@RequestMapping("/")
	public String index() {
		return "redirect:/case-history/list";
	}

	@GetMapping(value = "/list")
	public String list(Model model) {
		List<CaseHistory> caseHistoryList = caseHistoryService.getAllCaseHistory();
		model.addAttribute("caseHistoryList", caseHistoryList);
		return "case-history/list";
	}

	@GetMapping(value = "/toAdd", params = {"patientId"})
	public String toAdd(Model model, @RequestParam("patientId") Integer patientId) {
		model.addAttribute("patientId", patientId);
		return "case_history/add";
	}

	@PostMapping(value = "/add")
	public String add(@Valid CaseHistory caseHistory) {
		caseHistory.setDateTime(new Timestamp(System.currentTimeMillis()));
		caseHistoryService.add(caseHistory);
		return "redirect:/case-history/list";
	}


	@GetMapping(value = "/toEdit", params = {"id"})
	public String toEdit(Model model, @RequestParam("id") Integer id) {
		CaseHistory caseHistory = caseHistoryService.getById(id);
		model.addAttribute("caseHistory", caseHistory);
		return "case-history/edit";
	}

	@PutMapping(value = "/edit")
	public String edit(@Valid CaseHistory caseHistory) {
		caseHistoryService.update(caseHistory);
		return "redirect:/case-history/list";
	}

	@DeleteMapping(value = "/delete", params = {"id"})
	public String delete(@RequestParam("id") Integer id){
		caseHistoryService.delete(id);
		return "direct:/case-history/list";
	}
}
