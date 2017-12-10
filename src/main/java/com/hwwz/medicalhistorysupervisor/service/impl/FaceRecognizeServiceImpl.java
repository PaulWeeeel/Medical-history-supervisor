package com.hwwz.medicalhistorysupervisor.service.impl;

import com.hwwz.medicalhistorysupervisor.service.FaceRecognizeService;

import java.io.File;

/**
 * @author: HuShili
 * @date: 2017/12/8
 * @description: none
 */
public class FaceRecognizeServiceImpl implements FaceRecognizeService {
    @Override
    public String doRecognize(File file){
        return null;
    }
    @Override
    public boolean addFace(File file){ return false; }
    @Override
    public boolean deleteFace(File file){ return false; }
}
