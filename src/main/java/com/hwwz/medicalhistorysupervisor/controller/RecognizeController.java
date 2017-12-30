package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.configuration.Authorization;
import com.hwwz.medicalhistorysupervisor.domain.Patient;
import com.hwwz.medicalhistorysupervisor.repository.PatientRepository;
import com.hwwz.medicalhistorysupervisor.service.DataUrlConvertService;
import com.hwwz.medicalhistorysupervisor.service.FaceRecognizeService;
import com.hwwz.medicalhistorysupervisor.service.VoiceFileConvertService;
import com.hwwz.medicalhistorysupervisor.service.VoiceRecognizeService;
import com.hwwz.medicalhistorysupervisor.utils.ResJsonTemplate;
import org.bytedeco.javacpp.avcodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
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

    @ResponseBody
    @RequestMapping(value = "/voice", method = RequestMethod.POST)
    public ResJsonTemplate recognizeVoice(HttpServletRequest request, HttpServletResponse response, @RequestBody String audioData){

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

    @ResponseBody
    @RequestMapping(value = "/addFace", method = RequestMethod.POST)
    public ResJsonTemplate add(HttpServletRequest request, HttpServletResponse response, @RequestBody String filepath){

        File file = new File(filepath);
        String faceToken = faceRecognizeService.addNewFace(file);

        return new ResJsonTemplate("200", new Date(), faceToken);
    }

    @ResponseBody
    @RequestMapping(value = "/face", method = RequestMethod.POST)
    public ResJsonTemplate recognize(HttpServletRequest request, HttpServletResponse response, @RequestBody String image){

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

