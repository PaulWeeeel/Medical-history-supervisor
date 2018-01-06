package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.configuration.Authorization;
import com.hwwz.medicalhistorysupervisor.domain.CaseHistory;
import com.hwwz.medicalhistorysupervisor.service.CaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author: Aliweea
 * @date: 2017/12/5/005 11:06
 */
@Controller
@RequestMapping(value = "/case-history")
@Authorization  //该类方法都需登录
public class CaseHistoryController {

	@Autowired
	private CaseHistoryService caseHistoryService;

	@GetMapping(value = "/toAdd", params = {"patientId"})
	public String toAdd(Model model, @RequestParam("patientId") Integer patientId) {
		model.addAttribute("patientId", patientId);
		return "case-history/add";
	}

	@PostMapping(value = "/add")
	public String add(HttpServletRequest request, HttpServletResponse response, @RequestParam("files") MultipartFile[] files) {
        Integer patientId = Integer.valueOf(request.getParameter("patientId"));
        String onset = request.getParameter("onset");
        String description = request.getParameter("description");
        CaseHistory caseHistory = new CaseHistory();
        caseHistory.setOnset(onset);
        caseHistory.setDescription(description);
        //处理disease

        //处理症状图

        //处理medicine
        //也许可以在插入
        caseHistoryService.add(caseHistory, patientId);
		return "redirect:/patient/home/" + patientId;
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
