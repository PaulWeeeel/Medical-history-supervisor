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
}
