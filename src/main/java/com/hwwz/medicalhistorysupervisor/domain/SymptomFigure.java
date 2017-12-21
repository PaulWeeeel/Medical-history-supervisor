package com.hwwz.medicalhistorysupervisor.domain;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;

/**
 * @author: Aliweea
 * @date: 2017/12/21/021 17:06
 */
@Entity
public class SymptomFigure {

    private Integer id;

    private String imageUrl;

    private CaseHistory caseHistory;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public void setImageUrl(String imageUrl) {
        this.imageUrl = imageUrl;
    }

    @ManyToOne
    public CaseHistory getCaseHistory() {
        return caseHistory;
    }

    public void setCaseHistory(CaseHistory caseHistory) {
        this.caseHistory = caseHistory;
    }
}
