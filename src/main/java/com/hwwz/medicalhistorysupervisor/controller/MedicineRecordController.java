package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.configuration.Authorization;
import com.hwwz.medicalhistorysupervisor.domain.*;
import com.hwwz.medicalhistorysupervisor.repository.CaseHistoryRepository;
import com.hwwz.medicalhistorysupervisor.service.*;
import com.hwwz.medicalhistorysupervisor.utils.Common;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author: Aliweea
 * @date: 2017/12/8/008 11:56
 */

@Controller
@RequestMapping(value = "/medicine")
@Authorization//该类方法都需登录
public class MedicineRecordController {
	
	@Autowired
	private MedicineRecordService medicineRecordService;



	@GetMapping(value = "/medicine")
	public String list(Model model) {
		model.addAttribute("medicineList", medicineRecordService.getAllMedicineRecord());
		return "patient/today";
	}

	@PostMapping(value = "/add")
	public String add(@Valid MedicineRecord medicineRecord,@RequestParam("patientId")Integer patientId) throws Exception{

		medicineRecordService.add(medicineRecord,patientId);

		return "patient/today";
	}

	@PutMapping(value = "/edit")
	public String edit(@Valid MedicineRecord medicineRecord) {
		medicineRecordService.update(medicineRecord);
		return "patient/today";
	}


	@DeleteMapping(value = "/delete")
	public String delete(@RequestParam("id") Integer id) {
		medicineRecordService.delete(id);
		return "patient/today";
	}
}
