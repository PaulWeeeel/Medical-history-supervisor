package com.hwwz.medicalhistorysupervisor.service.impl;

import com.hwwz.medicalhistorysupervisor.domain.User;
import com.hwwz.medicalhistorysupervisor.repository.*;
import com.hwwz.medicalhistorysupervisor.service.BaseService;
import com.hwwz.medicalhistorysupervisor.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import java.util.HashMap;

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
    @Autowired
    private JwtUtil jwtUtil;

    @Override
    public void getRecentInfo(Model model, Integer size) {
        model.addAttribute("recentPatientList", patientRepository.getLastestPatients(size));
        model.addAttribute("recentCaseHistoryList", caseHistoryRepository.getLastestCaseHistories(size));
        model.addAttribute("recentPaymentRecordList", paymentRecordRepository.getLastestPaymentRecords(size));
        model.addAttribute("recentStockList", stockRepository.getLastestStocks(size));
    }

    @Override
    public void getNumber(Model model) {

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
    public String login(String username, String password) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            HashMap<String, Object> m = new HashMap<String, Object>();
                        m.put("userid", user.getId());
                        String token =jwtUtil.createJavaWebToken(m);
            return token;
        }
        return "";
    }
}
