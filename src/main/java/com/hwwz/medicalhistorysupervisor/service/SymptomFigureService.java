package com.hwwz.medicalhistorysupervisor.service;

import com.hwwz.medicalhistorysupervisor.domain.SymptomFigure;
import com.hwwz.medicalhistorysupervisor.repository.SymptomFigureRepository;
import org.springframework.beans.factory.annotation.Autowired;

public interface SymptomFigureService {
    public void add(SymptomFigure symptomFigure);

    public void update(SymptomFigure stock);

    public void delete(Integer id);
}
