package com.hwwz.medicalhistorysupervisor.service;

import java.io.File;

/**
 * @author: HuShili
 * @date: 2017/12/7
 * @description: none
 */

public interface VoiceRecognizeService {
    /*upload a word file to improve the accuracy*/
    public boolean doUpload(String string);
    /*transform voice file to a String buffer*/
    public String doRecognize(File file) throws InterruptedException;
}
