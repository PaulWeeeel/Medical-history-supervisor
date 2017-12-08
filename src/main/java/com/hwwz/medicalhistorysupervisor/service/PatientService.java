package com.hwwz.medicalhistorysupervisor.service;

import com.hwwz.medicalhistorysupervisor.domain.Patient;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/4/004 23:40
 */
@Component
public interface PatientService {
	public void addPatient(Patient patient);
	public List<Patient> getAllPatients();
	public Patient getPatientById(Integer id);
	public List<Patient> getPatientsByName(String name);
	public void updatePatient(Patient patient);
	public void deletePatient(Integer id);
}
