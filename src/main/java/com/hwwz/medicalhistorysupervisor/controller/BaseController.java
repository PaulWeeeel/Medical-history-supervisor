package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * @author: Aliweea
 * @date: 2017/12/12/012 9:11
 */
@Controller
@RequestMapping("/")
public class BaseController {

    @Autowired
    private BaseService baseService;

    @RequestMapping("/index")
    public String index() {
        return "index";
    }

    @RequestMapping("/toRegister")
    public String register(){
        return "base/register";
    }

    @PostMapping("/register")
    public String register(@RequestParam("username") String username,
                           @RequestParam("password1") String password1,
                           @RequestParam("password2") String password2){
        if (baseService.register(username, password1, password2)){
            return "index";
        }else {
            return "base/register";
        }
    }

    @RequestMapping("/toLogin")
    public String login(){
        return "base/login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password){
        if (baseService.login(username, password)){
            return "index";
        }else {
            return "base/login";
        }
    }
}
