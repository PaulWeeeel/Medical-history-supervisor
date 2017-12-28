package com.hwwz.medicalhistorysupervisor.service.impl;

import com.hwwz.medicalhistorysupervisor.domain.Patient;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @author: Aliweea
 * @date: 2017/12/7/007 12:24
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class PatientServiceImplTest {

	private static final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

	@Autowired
	private PatientServiceImpl patientService;

	@Test
	public void addPatient() throws Exception {
		Patient patient = new Patient();
		patient.setId(123);
		patient.setName("小芳");
		patient.setAge(20);
		patient.setAddress("上海");
		patient.setGender(false);
		patient.setOccupation("学生");
		patient.setPhone("2983012803");
		logger.info(patient.toString());
		/*patientService.addPatient(patient);*/
	}

	@Test
	public void getAllPatients() throws Exception {
	}

	@Test
	public void getPatientById() throws Exception {
	}

	@Test
	public void getPatientsByName() throws Exception {
	}

	@Test
	public void updatePatient() throws Exception {
	}

	@Test
	public void deletePatient() throws Exception {
    }

}