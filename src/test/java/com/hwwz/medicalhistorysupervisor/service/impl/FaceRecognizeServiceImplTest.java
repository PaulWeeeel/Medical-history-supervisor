package com.hwwz.medicalhistorysupervisor.service.impl;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.File;

/**
 * @author: HuShili
 * @date: 2017/12/18
 * @description: none
 */
public class FaceRecognizeServiceImplTest {

    @Autowired
    private FaceRecognizeServiceImpl faceRecognizeServiceImpl = new FaceRecognizeServiceImpl();


    @Test
    public void APITest() throws Exception {
        File file = new File("src/test/file/test.jpg");
        System.out.println(faceRecognizeServiceImpl.doRecognize(file));
    }
}