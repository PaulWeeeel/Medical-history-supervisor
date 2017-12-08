package com.hwwz.medicalhistorysupervisor.repository;

import com.hwwz.medicalhistorysupervisor.domain.Stock;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @author: Aliweea
 * @date: 2017/12/4/004 0:47
 */
public interface StockRepository extends JpaRepository<Stock, String>  {

}
