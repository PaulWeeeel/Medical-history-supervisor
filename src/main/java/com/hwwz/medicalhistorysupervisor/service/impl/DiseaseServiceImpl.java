package com.hwwz.medicalhistorysupervisor.service.impl;

import com.hwwz.medicalhistorysupervisor.domain.Disease;
import com.hwwz.medicalhistorysupervisor.repository.DiseaseRepository;
import com.hwwz.medicalhistorysupervisor.service.DiseaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service("DiseaseService")
public class DiseaseServiceImpl implements DiseaseService {

    @Autowired
    private DiseaseRepository diseaseRepository;

    @Override
    public List<Disease> getAllDiseases() {
        return diseaseRepository.findAll();
    }

    @Override
    public void addDisease(Disease disease){
        diseaseRepository.save(disease);
    }

    @Override
    public  Disease getByName(String diseaseName){
        return diseaseRepository.findDiseaseByDisease(diseaseName);
    }

    @Override
    public void update(Disease disease) {
        diseaseRepository.save(disease);
    }

    @Override
    public void delete(String name) {
        diseaseRepository.delete(name);
    }
}
