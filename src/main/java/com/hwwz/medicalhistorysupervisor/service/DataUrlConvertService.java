package com.hwwz.medicalhistorysupervisor.service;

import org.springframework.stereotype.Component;

/**
 * @author: HuShili
 * @date: 2017/12/30
 * @description: none
 */
@Component
public interface DataUrlConvertService {
    /**
     * Convert a string of data url to file and return the absolute path
     * @param dataUrl
     * @return string of the face token of the user
     */
    public String saveDataUrlToFile (String dataUrl);
    /**
     * Convert a string of data url to file and return the absolute path
     * @param fileName
     * @return string of the face token of the user
     */
    public String convertFileToDataUrl (String fileName);
}
