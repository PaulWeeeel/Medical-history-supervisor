package com.hwwz.medicalhistorysupervisor.service;

import com.hwwz.medicalhistorysupervisor.domain.Payment;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/8/008 12:38
 */
public interface PaymentService {
	public void add(Payment Payment);
	public void update(Payment Payment);
	public void delete(Integer id);
	public List<Payment> getAllPayments();
	public Payment getById(Integer id);
	public List<Payment> getByDateTimeBetween(Timestamp start, Timestamp end);
}
