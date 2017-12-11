package com.hwwz.medicalhistorysupervisor.repository;

import com.hwwz.medicalhistorysupervisor.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/1/001 19:07
 */

public interface PatientRepository  extends JpaRepository<Patient, Integer>{
	List<Patient> findByName(String name);
}
