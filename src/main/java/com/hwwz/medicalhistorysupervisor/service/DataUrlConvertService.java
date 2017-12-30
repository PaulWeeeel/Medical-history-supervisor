package com.hwwz.medicalhistorysupervisor.service;

import org.springframework.stereotype.Component;

/**
 * @author: HuShili
 * @date: 2017/12/30
 * @description: none
 */
@Component
public interface DataUrlConvertService {
    public String saveDataUrlToFile (String dataUrl) throws Exception;
}
