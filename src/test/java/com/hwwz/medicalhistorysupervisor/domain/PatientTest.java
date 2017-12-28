package com.hwwz.medicalhistorysupervisor.domain;

import com.hwwz.medicalhistorysupervisor.service.impl.PatientServiceImpl;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.sql.Timestamp;

/**
 * @author: Aliweea
 * @date: 2017/12/28/028 12:34
 */
public class PatientTest {

    private static final Logger logger = LoggerFactory.getLogger(PatientServiceImpl.class);

    @Test
    public void findLastTime() throws Exception {
        Patient patient = new Patient();
        patient.setLastDate(new Timestamp(System.currentTimeMillis()));
        logger.info(patient.findLastTime());
    }

    @Test
    public void getLastCaseHistory() {

    }
}