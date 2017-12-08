package com.hwwz.medicalhistorysupervisor.service.impl;

import com.hwwz.medicalhistorysupervisor.domain.CaseHistory;
import com.hwwz.medicalhistorysupervisor.repository.CaseHistoryRepository;
import com.hwwz.medicalhistorysupervisor.service.CaseHistoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/5/005 11:11
 */
@Service("CaseHistoryService")
public class CaseHistoryServiceImpl implements CaseHistoryService {

	@Autowired
	private CaseHistoryRepository caseHistoryRepository;

	@Override
	public List<CaseHistory> getAllCaseHistory() {
		return caseHistoryRepository.findAllCaseHistory();
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
	public void add(CaseHistory caseHistory) {
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
