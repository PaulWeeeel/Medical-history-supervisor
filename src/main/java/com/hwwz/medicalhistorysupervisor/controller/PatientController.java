package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.configuration.Authorization;
import com.hwwz.medicalhistorysupervisor.domain.CaseHistory;
import com.hwwz.medicalhistorysupervisor.domain.Patient;
import com.hwwz.medicalhistorysupervisor.service.CaseHistoryService;
import com.hwwz.medicalhistorysupervisor.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/1/001 14:30
 */
@Controller
@RequestMapping(value = "/patient")
@Authorization//该类方法都需登录
public class PatientController {

	@Autowired
	private PatientService patientService;

	@Autowired
	private CaseHistoryService caseHistoryService;

	@RequestMapping("/")
	public String index() {
		return "redirect:/patient/list";
	}

	@RequestMapping(value = "/list")
	public String list(Model model) throws Exception {
		model.addAttribute("patientList", patientService.getAllPatients());
		return "patient/list";
	}

//	@GetMapping(value = "/toAdd")
//	public String toAdd() {
//		return "patient/add";
//	}

	@PostMapping(value = "/add")
	public String add(@Valid Patient patient, BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			throw new Exception(bindingResult.getFieldError().getDefaultMessage());
		}
		try {
			patientService.addPatient(patient);
		} catch (Exception e) {
			throw e;
		}
		return "redirect:/patient/list";
	}

	@GetMapping(value = "/home/{id}")
	public String getPatientById(Model model, @PathVariable("id") Integer id) throws Exception {
		try {
			Patient patient = patientService.getPatientById(id);
			List<CaseHistory> caseHistoryList = patient.getCaseHistoryList();
			model.addAttribute("patient", patient);
			model.addAttribute("caseHistoryList", caseHistoryList);
		} catch (Exception e) {
			throw e;
		}
		return "patient/home";
	}


//	@GetMapping(value = "/list", params = {"name"})
//	public String getPatientsByName(Model model, @RequestParam("name") String name) throws Exception {
//		List<Patient> patients;
//		try {
//			patients = patientService.getPatientsByName(name);
//			model.addAttribute("patients", patients);
//		} catch (Exception e) {
//			throw e;
//		}
//		return "patient/list";
//	}

//	@GetMapping(value = "/toEdit")
//	public String toEdit(Model model, @RequestParam("id") Integer id) {
//		Patient patient = patientService.getPatientById(id);
//		model.addAttribute("patient", patient);
//		return "patient/edit";
//	}

	@PutMapping(value = "/edit")
	public String edit(@Valid Patient patient, BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			throw new Exception(bindingResult.getFieldError().getDefaultMessage());
		}
		try {
			patientService.updatePatient(patient);
		} catch (Exception e) {
			throw e;
		}
		return "redirect:/patient/list";
	}


	@DeleteMapping(value = "/delete")
	public String delete(@RequestParam("id") Integer id) throws Exception {
		try {
			patientService.deletePatient(id);
		} catch (Exception e) {
			throw e;
		}
		return "redirect:/patient/list";
	}
}
