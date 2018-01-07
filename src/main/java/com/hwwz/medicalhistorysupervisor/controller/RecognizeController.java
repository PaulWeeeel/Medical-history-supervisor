package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.configuration.Authorization;
import com.hwwz.medicalhistorysupervisor.domain.Patient;
import com.hwwz.medicalhistorysupervisor.repository.PatientRepository;
import com.hwwz.medicalhistorysupervisor.service.*;
import com.hwwz.medicalhistorysupervisor.utils.ResJsonTemplate;
import org.bytedeco.javacpp.avcodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.io.*;
import java.util.Date;
import java.util.List;

/**
 * @author: HuShili
 * @date: 2017/12/29
 * @description: none
 */
@Controller
@RequestMapping(value = "/recognize")
@Authorization//该类方法都需登录
public class RecognizeController {

    @Autowired
    private PatientRepository patientRepository;

    @Autowired
    private VoiceRecognizeService voiceRecognizeService;

    @Autowired
    private FaceRecognizeService faceRecognizeService;

    @Autowired
    private VoiceFileConvertService voiceFileConvertService;

    @Autowired
    private DataUrlConvertService dataUrlConvertService;

    @Autowired
    private PatientService patientService;

    /**
     * get the user of a img file of the face
     * @param audioData
     * @return Json contains string of the face token of the user with status 200
     */
    @ResponseBody
    @RequestMapping(value = "/voice", method = RequestMethod.POST)
    public ResJsonTemplate recognizeVoice(@RequestBody String audioData){

        String fileName = new String();

        try{
            fileName = dataUrlConvertService.saveDataUrlToFile(audioData);
        }
        catch (Exception e){
            return new ResJsonTemplate("500", new Date(), "File error");
        }

        //voiceFileConvertService.convert(fileName, fileName.substring(0, fileName.indexOf('.')) + "_temp.wav", avcodec.AV_CODEC_ID_PCM_S16LE, 8000, 16,1);
        //File file = new File(fileName.substring(0, fileName.indexOf('.')) + "_temp.wav");
        File file = new File(fileName);
        String result = voiceRecognizeService.doRecognize(file);

        return new ResJsonTemplate("200", new Date(), result);
    }

    /**
     * upload a new user word to accurate the voice recognize
     * @param newWord
     * @return result message success with status code 200 or failed with 400
     */
    @ResponseBody
    @RequestMapping(value = "/voice/add", method = RequestMethod.POST)
    public ResJsonTemplate addUserWord(@RequestBody String newWord){

        if(voiceRecognizeService.doUpload(newWord)) {
            return new ResJsonTemplate("200", new Date(), "Success");
        }
        else{
            return new ResJsonTemplate("400", new Date(), "Failed");
        }
    }

    /**
     * get a new token of the user of a img file of the face
     * @param filepath
     * @return Json contains a string of the face token of the user
     */
    @ResponseBody
    @RequestMapping(value = "/face/add", method = RequestMethod.POST)
    public ResJsonTemplate addFace(@RequestBody String filepath){

        File file = new File(filepath);
        String faceToken = faceRecognizeService.addNewFace(file);

        if(faceToken.length() != 0) {

            return new ResJsonTemplate("200", new Date(), faceToken);
        }
        else{
            return new ResJsonTemplate("400", new Date(), null);
        }
    }

    /**
     * get the user of a img file of the face
     * @param image
     * @return string of the face token of the user (if new, new a token)
     */
    @ResponseBody
    @RequestMapping(value = "/face", method = RequestMethod.POST)
    public ResJsonTemplate recognize(@RequestBody String image){

        String fileName = new String();

        try{
            fileName = dataUrlConvertService.saveDataUrlToFile(image);
        }
        catch (Exception e){
            return new ResJsonTemplate("500", new Date(), "File error");
        }

        String faceToken = faceRecognizeService.doRecognize(new File(fileName));

        System.out.println(faceToken);

        List<Patient> patients = patientRepository.findPatientByFaceToken(faceToken);

        if(patients.isEmpty() || faceToken == null) {

            return new ResJsonTemplate("404", new Date(), "Not found");
        }
        else {
            return new ResJsonTemplate("200", new Date(), patients.get(0));
        }
    }
}

