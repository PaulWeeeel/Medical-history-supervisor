package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.configuration.Authorization;
import com.hwwz.medicalhistorysupervisor.configuration.GlobalMed;
import com.hwwz.medicalhistorysupervisor.domain.CaseHistory;
import com.hwwz.medicalhistorysupervisor.domain.Patient;
import com.hwwz.medicalhistorysupervisor.service.CaseHistoryService;
import com.hwwz.medicalhistorysupervisor.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.File;
import java.util.List;
import java.util.UUID;

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

	private String photo_path;

	@RequestMapping("/")
	public String index() {
		return "redirect:/patient/listAll";
	}

	@RequestMapping(value = "/listAll")
	public String list(Model model) throws Exception {
		model.addAttribute("patientList", patientService.getAllPatients());
		return "patient/listAll";
	}

//	@GetMapping(value = "/toAdd")
//	public String toAdd() {
//		return "patient/add";
//	}

	@PostMapping(value = "/add")
	public String add(@RequestParam("photo")MultipartFile file,@Valid Patient patient, BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			throw new Exception(bindingResult.getFieldError().getDefaultMessage());
		}
		try {

			if (file!=null&&!file.isEmpty()) {
				// 获取文件名
				String fileName = file.getOriginalFilename();
				// 获取文件的后缀名
				String suffixName = fileName.substring(fileName.lastIndexOf("."));
				// 文件上传后的路径
				//new file name:
				fileName= UUID.randomUUID()+suffixName;
				String filePath = GlobalMed.getPhoto_path();
				// fileName = UUID.randomUUID() + suffixName;
				File dest = new File(filePath + fileName);
				// 检测是否存在目录
				if (!dest.getParentFile().exists()) {
					dest.getParentFile().mkdirs();
				}
				try {
					file.transferTo(dest);
				} catch (Exception e) {
					e.printStackTrace();
					return "上传失败";
				}
				patient.setPhotoURL(fileName);
				patientService.addPatient(patient);
			}
		} catch (Exception e) {
			throw e;
		}
		return "redirect:/patient/listAll";
	}


	@GetMapping(value="/add")
	public String add()
	{
		return "patient/add";
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
	@GetMapping(value = "/today")
	public String getTodayPatient(Model model) throws Exception {
		try {
			model.addAttribute("patientList", patientService.getTodayPatient());
		} catch (Exception e) {
			throw e;
		}
		return "patient/today";
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
		return "redirect:/patient/listAll";
	}


	@DeleteMapping(value = "/delete")
	public String delete(@RequestParam("id") Integer id) throws Exception {
		try {
			patientService.deletePatient(id);
		} catch (Exception e) {
			throw e;
		}
		return "redirect:/patient/listAll";
	}
}
