package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.util.WebUtils;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

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
    public String index(Model model) {
        baseService.getRecentInfo(model, RECORDSIZE);
        model.addAttribute("title", "hello");
        return "index";
    }

    @RequestMapping("/home")
    public String home(Model model) {
        baseService.getRecentInfo(model, RECORDSIZE);
        model.addAttribute("title", "hello");
        return "home";
    }

    @GetMapping("/register")
    public String register(){
        return "register";
    }

//    @PostMapping("/register")
//    public String register(@RequestParam("username") String username,
//                           @RequestParam("password1") String password1,
//                           @RequestParam("password2") String password2){
//        if (baseService.register(username, password1, password2)){
//            return "index";
//        }else {
//            return "register";
//        }
//    }

    @PostMapping("/register")
    public String register(HttpServletRequest request, HttpServletResponse response, HttpSession session){
        String username = WebUtils.findParameterValue(request, "username");
        String password1 = WebUtils.findParameterValue(request, "password1");
        String password2 = WebUtils.findParameterValue(request, "password2");
        if (baseService.register(username, password1, password2)){
            return "index";
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
                        @RequestParam("password") String password){
        if (baseService.login(username, password)){
            return "index";
        }else {
            return "login";
        }
    }
}
