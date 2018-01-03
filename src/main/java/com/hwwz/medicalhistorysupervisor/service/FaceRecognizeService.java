package com.hwwz.medicalhistorysupervisor.service;

import org.springframework.stereotype.Component;

import java.io.File;
import java.util.ArrayList;

/**
 * @author: HuShili
 * @date: 2017/12/8
 * @description: none
 */
@Component
public interface FaceRecognizeService {
    /**
     * get the user of a img file of the face
     * @param file
     * @return string of the face token of the user
     */
    public String doRecognize(File file);
    /**
     * reset the face set using existing face tokens
     * @param faceTokens
     * @return true if success, otherwise false
     */
    public boolean resetFaceSet(ArrayList<String> faceTokens);
    /**
     * add a new face to the existed faceset
     * @param file
     * @return true if success, otherwise false
     */
    public String addNewFace(File file);
}
