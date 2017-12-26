package com.hwwz.medicalhistorysupervisor.aspect;

import com.hwwz.medicalhistorysupervisor.configuration.Authorization;
import com.hwwz.medicalhistorysupervisor.utils.JwtUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.lang.reflect.Method;
import java.util.Map;


/**
 *@Author: PaulWell
 *@Description:
 *@Date: 22:49 2017/12/19
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Autowired
    private JwtUtil jwtUtil;
    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //如果有Authorization注解则检查权限
        HandlerMethod handlerMethod=(HandlerMethod)handler;
        Method method=handlerMethod.getMethod();//获取被拦截的方法
        Authorization auth=method.getAnnotation(Authorization.class);
        if (auth==null) {
            return true;
        }
        String jwt = "";
        Cookie[] cookies=request.getCookies();
        for(Cookie cookie:cookies)
        {
            if(cookie.getName().equals("token"))
            {
                jwt=cookie.getValue();
            }
        }
        Map<String,Object> claims=jwtUtil.parserJavaWebToken(jwt);
        if(claims!=null)
        {
            //获取用户id并以此身份进行操作
            //......
            return true;
        }
        response.sendRedirect("login");
        return false;
    }
}