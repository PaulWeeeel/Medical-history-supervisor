package com.hwwz.medicalhistorysupervisor.repository;

import com.hwwz.medicalhistorysupervisor.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

/**
 * @author: Aliweea
 * @date: 2017/12/4/004 0:47
 */
public interface StockRepository extends JpaRepository<Stock, Integer>  {

    @Query(value = "select * from stock as s where s.medicine_record_id = ?1", nativeQuery = true)
    Stock findByMedicineRecordId(Integer medicineRecordId);
}
