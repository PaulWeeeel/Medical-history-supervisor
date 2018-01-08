package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.configuration.Authorization;
import com.hwwz.medicalhistorysupervisor.configuration.GlobalMed;
import com.hwwz.medicalhistorysupervisor.domain.CaseHistory;
import com.hwwz.medicalhistorysupervisor.domain.Patient;
import com.hwwz.medicalhistorysupervisor.service.CaseHistoryService;
import com.hwwz.medicalhistorysupervisor.service.DataUrlConvertService;
import com.hwwz.medicalhistorysupervisor.service.FaceRecognizeService;
import com.hwwz.medicalhistorysupervisor.service.PatientService;
import com.hwwz.medicalhistorysupervisor.utils.fileReception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
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

	@Autowired
	private FaceRecognizeService faceRecognizeService;

	@Autowired
	private DataUrlConvertService dataUrlConvertService;

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

	@PostMapping(value = "/add")
	public String add(@RequestParam("photo")MultipartFile file,@Valid Patient patient,Model model, BindingResult bindingResult) throws Exception {
		if (bindingResult.hasErrors()) {
			throw new Exception(bindingResult.getFieldError().getDefaultMessage());
		}
		try {
			String fileName= fileReception.receiveSingle(file,GlobalMed.getPhoto_path());
			if(!fileName.equals(""))
			{
				String url = "/"+GlobalMed.getPhoto_dir()+fileName;
				patient.setPhotoURL(url);

				File newFace = new File(GlobalMed.getPhoto_path() + fileName);
				String faceToken = faceRecognizeService.addNewFace(newFace);
				if(faceToken == null)
				{
					model.addAttribute("error","没有识别出病人脸部特征，换一张图试试？");
					return "patient/add";
				}

				patient.setFaceToken(faceToken);
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
			String url = patient.getPhotoURL();
			try {
				patient.setPhotoURL(dataUrlConvertService.convertFileToDataUrl(url.substring(url.lastIndexOf('/') + 1)));
			}catch (Exception e){
				patient.setPhotoURL(null);
			}
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
