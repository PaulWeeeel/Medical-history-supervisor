package com.hwwz.medicalhistorysupervisor.service;

import org.springframework.stereotype.Component;

import java.io.File;

/**
 * @author: HuShili
 * @date: 2017/12/8
 * @description: none
 */
@Component
public interface FaceRecognizeService {
    /**
     * get the user of a img file of the face
     * @param file
     * @return string of the face token of the user (if new, new a token)
     */
    public String doRecognize(File file);
}
