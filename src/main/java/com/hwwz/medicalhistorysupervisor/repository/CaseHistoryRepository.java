package com.hwwz.medicalhistorysupervisor.repository;

import com.hwwz.medicalhistorysupervisor.domain.CaseHistory;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/4/004 0:24
 */
public interface CaseHistoryRepository extends JpaRepository<CaseHistory, Integer> {
	//public List<CaseHistory> findByPatientId(Integer id);

	@Query(value = "select * from case_history left outer join patient", nativeQuery = true)
	List<CaseHistory> findAllCaseHistory();

	@Query(value = "select * from case-history ch left outer join patient p where p.id = ?1", nativeQuery = true)
	List<CaseHistory> findByPatientId(Integer patientId);
}
