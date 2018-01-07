package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.configuration.Authorization;
import com.hwwz.medicalhistorysupervisor.configuration.GlobalMed;
import com.hwwz.medicalhistorysupervisor.domain.Disease;
import com.hwwz.medicalhistorysupervisor.service.DiseaseService;
import com.hwwz.medicalhistorysupervisor.utils.fileReception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.multipart.MultipartHttpServletRequest;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 *@Author: PaulWell
 *@Description:
 *@Date: 22:01 2017/12/26
 */
@Controller
@RequestMapping(value="/disease")
@Authorization
public class DiseaseController {

    @Autowired
    private DiseaseService diseaseService;

    @GetMapping("/list")
    public String disease(Model model) {
        model.addAttribute("diseaseList", diseaseService.getAllDiseases());
        return "disease/list";
    }
    @PostMapping(value = "/add")
    public String add(@RequestBody Disease disease) {
        diseaseService.addDisease(disease);
        return "disease/list";
    }
    @PostMapping(value = "/edit")
    public String edit(@RequestBody Disease disease) {
        diseaseService.update(disease);
        return "disease/list";
    }

    @PostMapping(value = "/delete")
    public String delete(@RequestParam("id") String name) {
        diseaseService.delete(name);
        return "disease/list";
    }




    @PostMapping(value = "/addPhotoSet")
    public String addTrainingSet(@RequestParam("file") MultipartFile[] files) throws Exception {
        List<String> nameList=new ArrayList<>();
        nameList=fileReception.receiveMultiple(files,GlobalMed.getDisease_path());
        //对文件名进行一些操作：

        return "disease/list";
    }

}
