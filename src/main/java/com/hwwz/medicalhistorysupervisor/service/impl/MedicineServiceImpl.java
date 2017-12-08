package com.hwwz.medicalhistorysupervisor.service.impl;

import com.hwwz.medicalhistorysupervisor.domain.Medicine;
import com.hwwz.medicalhistorysupervisor.repository.MedicineRepository;
import com.hwwz.medicalhistorysupervisor.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/8/008 12:10
 */
@Service("MedicineService")
public class MedicineServiceImpl implements MedicineService {

	@Autowired
	private MedicineRepository medicineRepository;


	@Override
	public void add(Medicine medicine) {
		medicineRepository.save(medicine);
	}

	@Override
	public List<Medicine> getAllMedicines() {
		return medicineRepository.findAll();
	}

	@Override
	public Medicine getMedicineById(Integer id) {
		return medicineRepository.findOne(id);
	}

	@Override
	public List<Medicine> getMedicinesByCaseHistoryId(Integer caseHistoryId) {
		return medicineRepository.findByCaseHistoryId(caseHistoryId);
	}

	@Override
	public void updateMedicine(Medicine medicine) {
		medicineRepository.save(medicine);
	}

	@Override
	public void deleteMedicine(Integer id) {
		medicineRepository.delete(id);
	}
}
