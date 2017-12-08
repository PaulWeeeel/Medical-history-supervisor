package com.hwwz.medicalhistorysupervisor.service;

import com.hwwz.medicalhistorysupervisor.domain.PaymentRecord;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/8/008 12:38
 */
public interface PaymentRecordService {
	public void add(PaymentRecord paymentRecord);
	public void update(PaymentRecord paymentRecord);
	public void delete(Integer id);
	public List<PaymentRecord> getAllPaymentRecords();
	public PaymentRecord getById(Integer id);
	public List<PaymentRecord> getByDateTimeBetween(Timestamp start, Timestamp end);
}
