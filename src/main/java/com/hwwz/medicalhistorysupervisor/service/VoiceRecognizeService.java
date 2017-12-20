package com.hwwz.medicalhistorysupervisor.service;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;

/**
 * @author: HuShili
 * @date: 2017/12/7
 * @description: none
 */
@Component
public interface VoiceRecognizeService {
    /**
     * upload a word file to improve the accuracy
     * @param string
     * @return true for success or false for failure
     */
    public boolean doUpload(String string);

    /**
     * reset userWords with existing userWords
     * @param userWords
     * @return true for success or false for failure
     */
    public boolean resetUserWord(ArrayList<String> userWords);

    /**
     * transform voice file to a String buffer
     * IMPORTANT: the sampling rate of the voice file should be 8000, otherwise change the parameter in the function
     * Only 8000 and 16000 supported
     * @param file
     * @return string (all content) or null for error
     */
    public String doRecognize(File file);
}
