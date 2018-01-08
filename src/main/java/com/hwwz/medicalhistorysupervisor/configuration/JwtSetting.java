package com.hwwz.medicalhistorysupervisor.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 *@Author: PaulWell
 *@Description:
 *@Date: 21:18 2017/12/24
 */

@Component
@ConfigurationProperties(prefix = "JwtSetting")
public class JwtSetting {

    private String SECRET;

    private Long maxAge;

    public String getSECRET() {
        return SECRET;
    }

    public void setSECRET(String SECRET) {
        this.SECRET = SECRET;
    }

    public Long getMaxAge() {
        return maxAge;
    }

    public void setMaxAge(Long maxAge) {
        this.maxAge = maxAge;
    }
}
