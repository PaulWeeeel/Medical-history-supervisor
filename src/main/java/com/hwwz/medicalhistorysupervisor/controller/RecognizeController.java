package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.configuration.Authorization;
import com.hwwz.medicalhistorysupervisor.domain.Patient;
import com.hwwz.medicalhistorysupervisor.repository.PatientRepository;
import com.hwwz.medicalhistorysupervisor.service.FaceRecognizeService;
import com.hwwz.medicalhistorysupervisor.service.VoiceFileConvertService;
import com.hwwz.medicalhistorysupervisor.service.VoiceRecognizeService;
import org.bytedeco.javacpp.avcodec;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.io.File;
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

    public class Form{
        String result;
    }

    @GetMapping("/voice")
    public Form recognizeVoice(String filepath){
        voiceFileConvertService.convert(filepath, "temp_" + filepath, avcodec.AV_CODEC_ID_PCM_S16LE, 8000, 16,1);
        File file = new File("temp_" + filepath);
        String result = voiceRecognizeService.doRecognize(file);

        Form form = new Form();
        form.result = result;

        return form;
    }

    @PostMapping("/addFace")
    public String add(@Valid Patient patient, String filepath){
        File file = new File(filepath);
        String token = faceRecognizeService.addNewFace(file);

        patient.setFaceToken(token);
        patientRepository.save(patient);
        return "redirect:/patient/listAll";
    }

    @GetMapping("/face")
    public String recognizeFace(@Valid Patient patient, String filepath){
        File file = new File(filepath);
        String token = faceRecognizeService.doRecognize(file);

        List<Patient> patients = patientRepository.findPatientByFaceToken(token);

        if(patients.isEmpty())
            patient = null;
        else
            patient = patients.get(0);

        return "redirect:/patient/listAll";
    }
}

