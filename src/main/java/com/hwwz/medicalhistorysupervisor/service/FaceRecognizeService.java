package com.hwwz.medicalhistorysupervisor.service;

import java.io.File;

/**
 * @author: HuShili
 * @date: 2017/12/8
 * @description: none
 */
public interface FaceRecognizeService {
    /*get the user of a img file of the face*/
    public String doRecognize(File file);
    /*add a new face to the faceset*/
    public boolean addFace(File file);
    /*delete a face from the faceset*/
    public boolean deleteFace(File file);
}
