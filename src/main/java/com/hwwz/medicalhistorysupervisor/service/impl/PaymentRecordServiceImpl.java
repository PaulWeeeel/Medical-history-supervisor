package com.hwwz.medicalhistorysupervisor.service.impl;

import com.hwwz.medicalhistorysupervisor.domain.PaymentRecord;
import com.hwwz.medicalhistorysupervisor.repository.PaymentRecordRepository;
import com.hwwz.medicalhistorysupervisor.service.PaymentRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/8/008 12:49
 */
@Service("PaymentRecordService")
public class PaymentRecordServiceImpl implements PaymentRecordService {

	@Autowired
	private PaymentRecordRepository paymentRecordRepository;

	@Override
	public void add(PaymentRecord paymentRecord) {
		paymentRecordRepository.save(paymentRecord);
	}

	@Override
	public void update(PaymentRecord paymentRecord) {
		paymentRecordRepository.save(paymentRecord);
	}

	@Override
	public void delete(Integer id) {
		paymentRecordRepository.delete(id);
	}

	@Override
	public List<PaymentRecord> getAllPaymentRecords() {
		return paymentRecordRepository.findAll();
	}

	@Override
	public PaymentRecord getById(Integer id) {
		return paymentRecordRepository.getOne(id);
	}

	@Override
	public List<PaymentRecord> getByDateTimeBetween(Timestamp start, Timestamp end) {
		return paymentRecordRepository.findAllByDateTimeBetween(start, end);
	}
}
