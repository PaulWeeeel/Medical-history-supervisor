package com.hwwz.medicalhistorysupervisor.configuration;

import com.hwwz.medicalhistorysupervisor.aspect.AuthInterceptor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;
/**
 *@Author: PaulWell
 *@Description:
 *@Date: 22:50 2017/12/19
 */

@Configuration
public class myInterceptorConfig extends WebMvcConfigurerAdapter {

    @Autowired
    AuthInterceptor authInterceptor;

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
//        registry.addInterceptor(authInterceptor).addPathPatterns("/").excludePathPatterns("/login", "/");
        registry.addInterceptor(authInterceptor).addPathPatterns("/**").excludePathPatterns("/register","/login");
        super.addInterceptors(registry);
    }
}
