package com.hwwz.medicalhistorysupervisor.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.NotNull;

/**
 * @author: Aliweea
 * @date: 2017/12/11/011 21:46
 */
@Entity
public class MedicineRecord {

    private  Integer id;

    private String medicine;

    private Integer totalDose;

    private Integer duration;

    private Integer timeInterval;

    private CaseHistory caseHistory;

    private Stock stock;

    @Id
    @GeneratedValue
    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(length = 40)
    public String getMedicine() {
        return medicine;
    }

    public void setMedicine(String medicine) {
        this.medicine = medicine;
    }

    @Max(value = 200)
    public Integer getTotalDose() {
        return totalDose;
    }

    public void setTotalDose(Integer totalDose) {
        this.totalDose = totalDose;
    }

    @NotNull
    public Integer getDuration() {
        return duration;
    }

    public void setDuration(Integer duration) {
        this.duration = duration;
    }

    @NotNull
    public Integer getTimeInterval() {
        return timeInterval;
    }

    public void setTimeInterval(Integer timeInterval) {
        this.timeInterval = timeInterval;
    }

    @ManyToOne
    public CaseHistory getCaseHistory() {
        return caseHistory;
    }

    @JsonBackReference
    public void setCaseHistory(CaseHistory caseHistory) {
        this.caseHistory = caseHistory;
    }

    @ManyToOne
    public Stock getStock() {
        return stock;
    }

    @JsonBackReference
    public void setStock(Stock stock) {
        this.stock = stock;
    }
}
