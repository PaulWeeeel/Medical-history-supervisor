package com.hwwz.medicalhistorysupervisor.service;

import org.springframework.stereotype.Component;

/**
 * @author: HuShili
 * @date: 2017/12/26
 * @description: none
 */
@Component
public interface VoiceFileConvertService {
    //Origin voice file is base on 44.1hHz which need to be converted to 8kHz
    //See the usage in the test file
}
