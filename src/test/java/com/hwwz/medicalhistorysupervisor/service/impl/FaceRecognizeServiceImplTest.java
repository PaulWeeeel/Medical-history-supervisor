package com.hwwz.medicalhistorysupervisor.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;

/**
 * @author: HuShili
 * @date: 2017/12/18
 * @description: none
 */
@RunWith(SpringRunner.class)
@SpringBootTest
public class FaceRecognizeServiceImplTest {

    @Autowired
    private FaceRecognizeServiceImpl faceRecognizeServiceImpl = new FaceRecognizeServiceImpl();


    @Test
    public void APITest() throws Exception {
        File file = new File("src/test/file/test.jpg");
        System.out.println(faceRecognizeServiceImpl.doRecognize(file));
    }
}