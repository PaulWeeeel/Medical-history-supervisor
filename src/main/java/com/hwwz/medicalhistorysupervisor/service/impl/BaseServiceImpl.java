package com.hwwz.medicalhistorysupervisor.service.impl;

import com.hwwz.medicalhistorysupervisor.domain.User;
import com.hwwz.medicalhistorysupervisor.repository.*;
import com.hwwz.medicalhistorysupervisor.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

/**
 * @author: Aliweea
 * @date: 2017/12/12/012 9:26
 */
@Service("BaseService")
public class BaseServiceImpl implements BaseService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private CaseHistoryRepository caseHistoryRepository;

    @Autowired
    private StockRepository stockRepository;

    @Autowired
    private PaymentRecordRepository paymentRecordRepository;

    @Override
    public void getRecentInfo(Model model, Integer size) {
        model.addAttribute("recentPatientList", patientRepository.getLastestPatients(size));
        model.addAttribute("recentCaseHistoryList", caseHistoryRepository.getLastestCaseHistories(size));
        model.addAttribute("recentPaymentList", paymentRecordRepository.getLastestPaymentRecords(size));
        model.addAttribute("recentStockList", stockRepository.getLastestStocks(size));
    }

    @Override
    public Boolean register(String username, String password1, String password2) {
        if (!password1.equals(password2)) {
            return false;
        }
        if (userRepository.findByUsername(username) != null) {
            return false;
        }

        User user = new User();
        user.setUsername(username);
        user.setPassword(password1);
        userRepository.save(user);
        return true;
    }

    @Override
    public Boolean login(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            return true;
        }
        return false;
    }
}
