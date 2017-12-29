package com.hwwz.medicalhistorysupervisor.repository;

import com.hwwz.medicalhistorysupervisor.domain.Patient;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/1/001 19:07
 */

public interface PatientRepository  extends JpaRepository<Patient, Integer>{
	List<Patient> findByName(String name);

    @Query(value = "select * from patient order by id desc limit ?1", nativeQuery = true)
	List<Patient> getLastestPatients(Integer size);

    @Query(value="select * from patient as p,case_history as c where p.id=c.patient_id and c.date_time=CURRENT_DATE ",
	nativeQuery = true)
	List<Patient> getTodayPatient();

    List<Patient> findPatientByFaceToken(String faceToken);
}
