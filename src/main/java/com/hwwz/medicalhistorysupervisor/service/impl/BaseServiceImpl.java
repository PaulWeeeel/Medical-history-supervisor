package com.hwwz.medicalhistorysupervisor.service.impl;

import com.hwwz.medicalhistorysupervisor.domain.User;
import com.hwwz.medicalhistorysupervisor.repository.UserRepository;
import com.hwwz.medicalhistorysupervisor.service.BaseService;
import com.hwwz.medicalhistorysupervisor.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

/**
 * @author: Aliweea
 * @date: 2017/12/12/012 9:26
 */
@Service("BaseService")
public class BaseServiceImpl implements BaseService {

    @Autowired
    private UserRepository userRepository;

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
    public Boolean login(String username, String password,Model model) {
        User user = userRepository.findByUsernameAndPassword(username, password);
        if (user != null) {
            Map<String, Object> m = new HashMap<String, Object>();
            m.put("userid", user.getId());
            String token = JwtUtil.createJavaWebToken(m);
            model.addAttribute("token",token);
            return true;
        }
        return false;
    }
}
