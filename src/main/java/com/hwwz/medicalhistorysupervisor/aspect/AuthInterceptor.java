package com.hwwz.medicalhistorysupervisor.aspect;

import com.hwwz.medicalhistorysupervisor.utils.JwtUtil;
import org.springframework.stereotype.Component;
import org.springframework.web.method.HandlerMethod;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

/**
 *@Author: PaulWell
 *@Description:
 *@Date: 22:49 2017/12/19
 */
@Component
public class AuthInterceptor extends HandlerInterceptorAdapter {

    @Override
    public boolean preHandle(HttpServletRequest request,
                             HttpServletResponse response, Object handler) throws Exception {
        //如果不是映射到方法直接通过
        if (!(handler instanceof HandlerMethod)) {
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
        Map<String,Object> claims= JwtUtil.parserJavaWebToken(jwt);
        if(claims!=null)
        {
            //获取用户id并以此身份进行操作
            //......
            return true;
        }
        response.sendRedirect("toLogin");
        return false;
    }
}