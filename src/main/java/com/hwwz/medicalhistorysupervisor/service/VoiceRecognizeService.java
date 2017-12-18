package com.hwwz.medicalhistorysupervisor.service;

import java.io.File;

/**
 * @author: HuShili
 * @date: 2017/12/7
 * @description: none
 */

public interface VoiceRecognizeService {
    /**
     * upload a word file to improve the accuracy
     * @param string
     * @return true for success or false for failure
     */
    public boolean doUpload(String string);

    /**
     * transform voice file to a String buffer
     * @param file
     * @return string (all content) or null for error
     */
    public String doRecognize(File file);
}
