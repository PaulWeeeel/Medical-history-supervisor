package com.hwwz.medicalhistorysupervisor.repository;

import com.hwwz.medicalhistorysupervisor.domain.MedicineRecord;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/4/004 0:34
 */
public interface MedicineRecordRepository extends JpaRepository<MedicineRecord, Integer> {
	List<MedicineRecord> findByCaseHistoryId(Integer caseHistoryId);
}
