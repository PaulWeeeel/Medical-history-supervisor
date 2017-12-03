package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.domain.Patient;
import com.hwwz.medicalhistorysupervisor.domain.Result;
import com.hwwz.medicalhistorysupervisor.enums.ResultEnum;
import com.hwwz.medicalhistorysupervisor.repository.PatientRepository;
import com.hwwz.medicalhistorysupervisor.utils.ResultUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/1/001 14:30
 */
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
	@GetMapping(value = "/list/{name}")
	public Result<List<Patient>> getPatientsByName(@PathVariable("name") String name) {
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


}
