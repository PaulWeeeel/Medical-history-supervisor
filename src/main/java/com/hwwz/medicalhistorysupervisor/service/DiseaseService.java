package com.hwwz.medicalhistorysupervisor.service;

import com.hwwz.medicalhistorysupervisor.domain.Disease;

import java.util.List;

public interface DiseaseService {
    List<Disease> getAllDiseases();
    void addDisease(Disease disease);
    Disease getByName(String diseaseName);
    void update(Disease disease);
    void delete(String name);
}
