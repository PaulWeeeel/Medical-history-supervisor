package com.hwwz.medicalhistorysupervisor.repository;

import com.hwwz.medicalhistorysupervisor.domain.Disease;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: Aliweea
 * @date: 2017/12/4/004 0:28
 */
public interface DiseaseRepository extends JpaRepository<Disease, String> {
}
