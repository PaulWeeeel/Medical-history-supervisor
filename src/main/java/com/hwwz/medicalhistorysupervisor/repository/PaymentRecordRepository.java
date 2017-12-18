package com.hwwz.medicalhistorysupervisor.repository;

import com.hwwz.medicalhistorysupervisor.domain.PaymentRecord;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/4/004 0:41
 */
public interface PaymentRecordRepository extends JpaRepository<PaymentRecord, Integer> {
	List<PaymentRecord> findAllByDateTimeBetween(Timestamp start, Timestamp end);

	@Query(value = "select * from payment_record order by id desc limit ?1", nativeQuery = true)
	List<PaymentRecord> getLastestPaymentRecords(Integer size);
}
