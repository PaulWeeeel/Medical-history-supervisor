package com.hwwz.medicalhistorysupervisor.configuration;

import org.springframework.util.ClassUtils;

import java.io.File;


/**
 *@Author: PaulWell
 *@Description: create the directories for patients' photos and symptom pictures if not exist,under the project's root path
 *@Date: 23:59 2017/12/28
 */
public class GlobalMed {
    private static String abs_path;
    private static String photo_dir;
    private static String symptom_dir;
    private static String photo_path;
    private static String symptom_path;

    private GlobalMed()
    {
        abs_path= ClassUtils.getDefaultClassLoader().getResource("").getPath();
        photo_dir="/photos";
        symptom_dir="/symptoms";
        photo_path=abs_path+photo_dir;
        symptom_path=abs_path+symptom_path;
        File photo_dir=new File(photo_path);
        File symp_dir=new File(symptom_path);
        if(!photo_dir.exists())
        {
            photo_dir.mkdir();
        }
        if(!symp_dir.exists())
        {
            symp_dir.mkdir();
        }
    }

    public static String getAbs_path() {
        return abs_path;
    }

    public static String getPhoto_dir() {
        return photo_dir;
    }

    public static String getSymptom_dir() {
        return symptom_dir;
    }

    public static String getPhoto_path() {
        return photo_path;
    }

    public static String getSymptom_path() {
        return symptom_path;
    }
}
