package com.hwwz.medicalhistorysupervisor.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;

import static java.lang.Thread.sleep;

/**
 * @author: HuShili
 * @date: 2017/12/18
 * @description: none
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class VoiceRecognizeServiceImplTest {
    @Autowired
    private VoiceRecognizeServiceImpl voiceRecognizeServiceImpl = new VoiceRecognizeServiceImpl();


    @Test
    public void APITest() throws Exception {
        File file = new File("src/test/file/iflytek01_1.wav");
        ArrayList<String> userWords = new ArrayList<String>();
        System.out.println(voiceRecognizeServiceImpl.doRecognize(file));

        userWords.add("New Test");
        System.out.println(voiceRecognizeServiceImpl.doUpload("Test"));

        System.out.println(voiceRecognizeServiceImpl.resetUserWord(userWords));
    }
}
