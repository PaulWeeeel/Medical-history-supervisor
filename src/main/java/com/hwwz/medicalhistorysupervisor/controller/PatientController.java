package com.hwwz.medicalhistorysupervisor.controller;

<<<<<<< HEAD
import com.hwwz.medicalhistorysupervisor.domain.CaseHistory;
import com.hwwz.medicalhistorysupervisor.domain.Patient;
import com.hwwz.medicalhistorysupervisor.service.CaseHistoryService;
import com.hwwz.medicalhistorysupervisor.service.PatientService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
=======
import com.hwwz.medicalhistorysupervisor.domain.Patient;
import com.hwwz.medicalhistorysupervisor.domain.Result;
import com.hwwz.medicalhistorysupervisor.enums.ResultEnum;
import com.hwwz.medicalhistorysupervisor.repository.PatientRepository;
import com.hwwz.medicalhistorysupervisor.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
>>>>>>> 990e1f0dff9dafcbbe9b46f756d96fb37af66f2f
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/1/001 14:30
 */
<<<<<<< HEAD
@Controller
@RequestMapping(value = "/patient")
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
	public String list(Model model) throws Exception{
		List<Patient> patients = patientService.getAllPatients();;
		model.addAttribute("patients", patients);
		return "patient/list";
	}

	@GetMapping(value = "toAdd")
	public String toAdd() {
		return "patient/add";
	}

	@PostMapping(value = "/add")
	public String add(@Valid Patient patient, BindingResult bindingResult) throws Exception{
		if(bindingResult.hasErrors()){
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
			List<CaseHistory> caseHistories = patient.getCaseHistoryList();
			model.addAttribute("patient", patient);
			model.addAttribute("caseHistories", caseHistories);
		} catch (Exception e) {
			throw e;
		}
		return "patient/home";
	}


	@GetMapping(value = "/list", params = {"name"})
	public String getPatientsByName(Model model, @RequestParam("name") String name) throws Exception {
		List<Patient> patients;
		try {
			patients = patientService.getPatientsByName(name);
			model.addAttribute("patients", patients);
		} catch (Exception e) {
			throw e;
		}
		return "patient/list";
	}

	@GetMapping(value = "/toEdit")
	public String toEdit(Model model, @RequestParam("id") Integer id) {
		Patient patient = patientService.getPatientById(id);
		model.addAttribute("patient", patient);
		return "patient/edit";
	}

	@PutMapping(value = "/edit")
	public String edit(@Valid Patient patient, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()){
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

=======
@RestController
@RequestMapping(value = "/patients")
public class PatientController {

	@Autowired
	private PatientRepository patientRepository;

	/**
		*@description: insert a patient into database
		*@params: a patient, bindingResult used to get valid result
		*@return: a patient
		*@date: 2017/12/3/003 12:50
		*/
	@PostMapping(value = "/add")
	public Result<Patient> addPatient(@Valid Patient patient, BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			return ResultUtil.error(ResultEnum.VALID_ERROR.getCode(),
													bindingResult.getFieldError().getDefaultMessage());
		}
		return ResultUtil.success(patientRepository.save(patient));
	}

	/**
	 *@description: List all patients
	 *@params: null
	 *@return: a list of all patients
	 *@date: 2017/12/1/001 19:14
	 */
	@GetMapping(value = "/list/all")
	public List<Patient> getAllPatients(){
		return patientRepository.findAll();
	}

	/**
		*@description: Find a patient by id
		*@params: patient's id
		*@return: a patient
		*@date: 2017/12/3/003 12:36
		*/
	@GetMapping(value = "/list")
	public Result<Patient> getPatientById(@RequestParam("id") Integer id) {
		Patient patient = patientRepository.findOne(id);
		if(patient == null) {
			return ResultUtil.error(ResultEnum.FIND_ERROR.getCode(), ResultEnum.FIND_ERROR.getMessage());
		}
		return ResultUtil.success(patient);
	}

	/**
	 *@description:  find patients by name
	 *@params: patient's, bindingResult used to get valid result
	 *@return: one or more patients
	 *@date: 2017/12/3/003 13:05
	 */
	@GetMapping(value = "/list")
	public Result<List<Patient>> getPatientsByName(@RequestParam("name") String name) {
		List<Patient> list = patientRepository.findByName(name);
		if(list == null) {
			return ResultUtil.error(ResultEnum.FIND_ERROR.getCode(), ResultEnum.FIND_ERROR.getMessage());
		}
		return ResultUtil.success(list);
	}


	@PutMapping(value = "/update/{id}")
	public Result<Patient> updatePatientById(@Valid Patient patient, BindingResult bindingResult) {
		if(bindingResult.hasErrors()){
			return ResultUtil.error(ResultEnum.VALID_ERROR.getCode(),
													bindingResult.getFieldError().getDefaultMessage());
		}
		return ResultUtil.success(patientRepository.save(patient));
	}

	/**
		*@description: Delete a patient by id
		*@params: patient's id
		*@return: void
		*@date: 2017/12/3/003 12:42
		*/
	@DeleteMapping(value = "/delete/{id}")
	public void deletePatientById(@PathVariable("id") Integer id) {
		patientRepository.delete(id);
	}


>>>>>>> 990e1f0dff9dafcbbe9b46f756d96fb37af66f2f
}
