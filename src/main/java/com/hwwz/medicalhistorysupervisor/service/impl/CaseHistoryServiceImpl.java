package com.hwwz.medicalhistorysupervisor.service.impl;

import com.hwwz.medicalhistorysupervisor.domain.CaseHistory;
import com.hwwz.medicalhistorysupervisor.domain.Patient;
import com.hwwz.medicalhistorysupervisor.repository.CaseHistoryRepository;
import com.hwwz.medicalhistorysupervisor.repository.PatientRepository;
import com.hwwz.medicalhistorysupervisor.service.CaseHistoryService;
import com.hwwz.medicalhistorysupervisor.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/5/005 11:11
 */
@Service("CaseHistoryService")
public class CaseHistoryServiceImpl implements CaseHistoryService {

	@Autowired
	private CaseHistoryRepository caseHistoryRepository;

	@Autowired
	private PatientRepository patientRepository;

	@Override
	public List<CaseHistory> getAllCaseHistory() {
		return caseHistoryRepository.findAll();
	}

	@Override
	public CaseHistory getById(Integer id) {
		return caseHistoryRepository.findOne(id);
	}

	@Override
	public List<CaseHistory> getByPatientId(Integer id) {
		return caseHistoryRepository.findByPatientId(id);
	}

	@Override
	public void add(CaseHistory caseHistory, Integer patientId) {
        caseHistory.setDateTime(Common.getCurTimeString());
		Patient patient = patientRepository.getOne(patientId);
		caseHistory.setPatient(patient);
		caseHistoryRepository.save(caseHistory);
	}

	@Override
	public void update(CaseHistory caseHistory) {
		caseHistoryRepository.save(caseHistory);
	}

	@Override
	public void delete(Integer id) {
		caseHistoryRepository.delete(id);
	}

}
