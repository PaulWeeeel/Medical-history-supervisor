package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.configuration.Authorization;
import com.hwwz.medicalhistorysupervisor.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

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

}
