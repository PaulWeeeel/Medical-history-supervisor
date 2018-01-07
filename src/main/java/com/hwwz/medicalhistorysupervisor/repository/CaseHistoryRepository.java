package com.hwwz.medicalhistorysupervisor.repository;

import com.hwwz.medicalhistorysupervisor.domain.CaseHistory;
import com.hwwz.medicalhistorysupervisor.utils.Common;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/4/004 0:24
 */
public interface CaseHistoryRepository extends JpaRepository<CaseHistory, Integer> {
	List<CaseHistory> findByPatientId(Integer id);

	@Query(value = "select * from case_history order by id desc limit ?1", nativeQuery = true)
	List<CaseHistory> getLastestCaseHistories(Integer size);

//	@Query(value = "select * from case_history left outer join patient", nativeQuery = true)
//	List<CaseHistory> findAllCaseHistory();
//
//	@Query(value = "select * from case-history ch left outer join patient p where p.id = ?1", nativeQuery = true)
//	List<CaseHistory> findByPatientId(Integer patientId);

	@Query(value = "select * from case_history where patient_id=? order by id desc limit 1", nativeQuery = true)
	CaseHistory getLastestCaseHistoryByPatientId(Integer patientId);

	@Query(value = "select sum(fee) from case_history where date_time like %:curDate%", nativeQuery = true)
	Double getTodayPayment(@Param("curDate")String curDate);

	@Query(value = "select sum(fee) from case_history ", nativeQuery = true)
	Double getTotalPayment();
}
