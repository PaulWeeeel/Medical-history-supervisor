package com.hwwz.medicalhistorysupervisor.service.impl;

import com.hwwz.medicalhistorysupervisor.domain.Payment;
import com.hwwz.medicalhistorysupervisor.repository.PaymentRepository;
import com.hwwz.medicalhistorysupervisor.service.PaymentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/8/008 12:49
 */
@Service("PaymentService")
public class PaymentServiceImpl implements PaymentService {

	@Autowired
	private PaymentRepository PaymentRepository;

	@Override
	public void add(Payment Payment) {
        Date d = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        Payment.setDateTime(sdf.format(d));
		PaymentRepository.save(Payment);
	}

	@Override
	public void update(Payment Payment) {
		PaymentRepository.save(Payment);
	}

	@Override
	public void delete(Integer id) {
		PaymentRepository.delete(id);
	}

	@Override
	public List<Payment> getAllPayments() {
		return PaymentRepository.findAll();
	}

	@Override
	public Payment getById(Integer id) {
		return PaymentRepository.getOne(id);
	}

	@Override
	public List<Payment> getByDateTimeBetween(Timestamp start, Timestamp end) {
		return PaymentRepository.findAllByDateTimeBetween(start, end);
	}
}
