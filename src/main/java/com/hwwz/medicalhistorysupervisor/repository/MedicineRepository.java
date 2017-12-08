package com.hwwz.medicalhistorysupervisor.repository;

import com.hwwz.medicalhistorysupervisor.domain.Medicine;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/4/004 0:34
 */
public interface MedicineRepository extends JpaRepository<Medicine, Integer> {
	public List<Medicine> findByCaseHistoryId(Integer caseHistoryId);
}
