package com.hwwz.medicalhistorysupervisor.service;

import com.hwwz.medicalhistorysupervisor.domain.CaseHistory;

import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/5/005 21:40
 */
public interface CaseHistoryService {
	public List<CaseHistory> getAllCaseHistory();
	public CaseHistory getById(Integer id);
	public List<CaseHistory> getByPatientId(Integer id);
	public void add(CaseHistory caseHistory, Integer patientId);
	public void update(CaseHistory caseHistory);
	public void delete(Integer id);
}
