package com.hwwz.medicalhistorysupervisor.service.impl;

import com.hwwz.medicalhistorysupervisor.domain.SymptomFigure;
import com.hwwz.medicalhistorysupervisor.repository.SymptomFigureRepository;
import com.hwwz.medicalhistorysupervisor.service.SymptomFigureService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("SymptomFigureService")
public class SymptomFigureServiceImpl implements SymptomFigureService {
    @Autowired
    private SymptomFigureRepository symptomFigureRepository;
    @Override
    public void add(SymptomFigure symptomFigure) {
        symptomFigureRepository.save(symptomFigure);
    }

    @Override
    public void update(SymptomFigure stock) {
        symptomFigureRepository.save(stock);
    }

    @Override
    public void delete(Integer id) {
        symptomFigureRepository.delete(id);
    }
}
