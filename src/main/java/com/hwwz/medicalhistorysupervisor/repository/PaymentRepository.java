package com.hwwz.medicalhistorysupervisor.repository;

import com.hwwz.medicalhistorysupervisor.domain.Payment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/4/004 0:41
 */
public interface PaymentRepository extends JpaRepository<Payment, Integer> {
	List<Payment> findAllByDateTimeBetween(Timestamp start, Timestamp end);

	@Query(value = "select * from payment order by id desc limit ?1", nativeQuery = true)
	List<Payment> getLastestPayments(Integer size);
}
