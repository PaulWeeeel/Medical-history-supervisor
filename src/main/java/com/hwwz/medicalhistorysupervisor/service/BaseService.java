package com.hwwz.medicalhistorysupervisor.service;

import org.springframework.ui.Model;

/**
 * @author: Aliweea
 * @date: 2017/12/12/012 9:23
 */
public interface BaseService {
    void getRecentInfo(Model model, Integer size);
    Boolean register(String username, String password1, String password2);
    String login(String username, String password);
}
