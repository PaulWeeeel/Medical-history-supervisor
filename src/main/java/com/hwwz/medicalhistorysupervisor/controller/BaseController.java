package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.configuration.Authorization;
import com.hwwz.medicalhistorysupervisor.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.ClassUtils;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;

/**
 * @author: Aliweea
 * @date: 2017/12/12/012 9:11
 */
@Controller
@RequestMapping("/")
public class BaseController {

    public final static Integer RECORDSIZE = 5;

    @Autowired
    private BaseService baseService;


    @RequestMapping("/index")
    @Authorization//有此标记的方法检查登录状态
    public String index(Model model) throws Exception{
        baseService.getNumber(model);
        model.addAttribute("title", "hello");
        return "index";
    }

    @RequestMapping("/home")
    @Authorization//有此标记的方法检查登录状态
    public String home(Model model) {
        baseService.getRecentInfo(model, RECORDSIZE);
        return "home";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }


    @PostMapping("/register")
    public String register(HttpServletRequest request, HttpServletResponse response){
        String username = WebUtils.findParameterValue(request, "username");
        String password1 = WebUtils.findParameterValue(request, "password1");
        String password2 = WebUtils.findParameterValue(request, "password2");
        String phone=WebUtils.findParameterValue(request, "phone");
        if (baseService.register(username, password1, password2,phone)){
            return "login";
        }else {
            return "register";
        }
    }

    @GetMapping("/login")
    public String login(){
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam("username") String username,
                        @RequestParam("password") String password, Model model){
        String token;
        if (!(token=baseService.login(username,password)).equals("")){
            //登录成功，返回服务器生成的token
            model.addAttribute("token",token);
            baseService.getNumber(model);
            return "index";
        }else {
            //登录失败
            return "login";
        }
    }

    @GetMapping("/404")
    public String error404(){
        return "error/404";
    }

    @GetMapping("/500")
    public String error500(){
        return "error/500";
    }
}
