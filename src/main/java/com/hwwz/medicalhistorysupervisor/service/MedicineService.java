package com.hwwz.medicalhistorysupervisor.service;

import com.hwwz.medicalhistorysupervisor.domain.Medicine;

import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/8/008 11:57
 */
public interface MedicineService {
	public void add(Medicine medicine);
	public List<Medicine> getAllMedicines();
	public Medicine getMedicineById(Integer id);
	public List<Medicine> getMedicinesByCaseHistoryId(Integer caseHistoryId);
	public void updateMedicine(Medicine medicine);
	public void deleteMedicine(Integer id);
}
