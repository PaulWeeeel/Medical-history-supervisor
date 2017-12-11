package com.hwwz.medicalhistorysupervisor.service;

import com.hwwz.medicalhistorysupervisor.domain.MedicineRecord;

import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/8/008 11:57
 */
public interface MedicineRecordService {
	public void add(MedicineRecord medicineRecordRecord);
	public List<MedicineRecord> getAllMedicineRecord();
	public MedicineRecord getById(Integer id);
	public List<MedicineRecord> getByCaseHistoryId(Integer caseHistoryId);
	public void update(MedicineRecord medicineRecord);
	public void delete(Integer id);
}
