package com.hwwz.medicalhistorysupervisor.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * @author: HuShili
 * @date: 2017/12/18
 * @description: none
 */
public class VoiceRecognizeServiceImplTest {
    @Autowired
    private VoiceRecognizeServiceImpl voiceRecognizeServiceImpl = new VoiceRecognizeServiceImpl();


    @Test
    public void APITest() throws Exception {
        File file = new File("src/test/file/iflytek01_1.wav");
        System.out.println(voiceRecognizeServiceImpl.doRecognize(file));
    }
}
