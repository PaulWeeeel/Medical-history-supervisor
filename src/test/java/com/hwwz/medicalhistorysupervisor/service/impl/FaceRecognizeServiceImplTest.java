package com.hwwz.medicalhistorysupervisor.service.impl;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.File;
import java.util.ArrayList;

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
        String token = new String();
        ArrayList<String> tokens = new ArrayList<String>();

        token = faceRecognizeServiceImpl.doRecognize(file);
        System.out.println(token);

        tokens.add(token);
        System.out.println(faceRecognizeServiceImpl.resetFaceSet(tokens));
    }
}