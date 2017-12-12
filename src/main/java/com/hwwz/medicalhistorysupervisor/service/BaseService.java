package com.hwwz.medicalhistorysupervisor.service;

/**
 * @author: Aliweea
 * @date: 2017/12/12/012 9:23
 */
public interface BaseService {
    public Boolean register(String username, String password1, String password2);
    public Boolean login(String username, String password);
}
