package com.hwwz.medicalhistorysupervisor.service.impl;

import com.hwwz.medicalhistorysupervisor.domain.Patient;
import com.hwwz.medicalhistorysupervisor.repository.PatientRepository;
import com.hwwz.medicalhistorysupervisor.service.PatientService;
import com.hwwz.medicalhistorysupervisor.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/5/005 0:11
 */
@Service("PatientService")
public class PatientServiceImpl implements PatientService {

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public void addPatient(Patient patient) {
		patientRepository.save(patient);
	}

	@Override
	public List<Patient> getAllPatients()  {
		return patientRepository.findAll();
	}

	@Override
	public Patient getPatientById(Integer id) {
		return patientRepository.findOne(id);
	}

	@Override
	public List<Patient> getPatientsByName(String name) {
		return  patientRepository.findByName(name);
	}

	@Override
	public void updatePatient(Patient patient) {
		patientRepository.save(patient);
	}

	@Override
	public void deletePatient(Integer id) {
		patientRepository.delete(id);
	}


	public List<Patient> getTodayPatient(){return patientRepository.getTodayPatient(Common.getCurDateString());}
}
