package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.configuration.Authorization;
import org.springframework.stereotype.Controller;
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
    @GetMapping("/disease")
    public String disease()
    {
        return "disease/disease";
    }

}
