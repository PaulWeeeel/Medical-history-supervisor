package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.configuration.Authorization;
import com.hwwz.medicalhistorysupervisor.domain.MedicineRecord;
import com.hwwz.medicalhistorysupervisor.service.MedicineRecordService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

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
	
	/*@GetMapping(value = "/list")
	public String list(Model model) {
		List<MedicineRecord> medicineRecordList = medicineRecordService.getAllMedicineRecord();
		model.addAttribute("medicines", medicineRecordList);
		return "medicine-record/list";
	}

	@PostMapping(value = "/add")
	public String add(@Valid MedicineRecord medicineRecord) {
		medicineRecordService.add(medicineRecord);
		return "redirect:/medicine-record/list";
	}

	@GetMapping(value = "/toEdit")
	public String toEdit(Model model, @RequestParam("id") Integer id) {
		MedicineRecord medicineRecord = medicineRecordService.getById(id);
		model.addAttribute("medicineRecord", medicineRecord);
		return "medicine-record/edit";
	}

	@PutMapping(value = "/edit")
	public String edit(@Valid MedicineRecord medicineRecord) {
		medicineRecordService.update(medicineRecord);
		return "redirect:/medicine-record/list";
	}


	@DeleteMapping(value = "/delete")
	public String delete(@RequestParam("id") Integer id) {
		medicineRecordService.delete(id);
		return "redirect:/medicine-record/list";
	}*/

	@GetMapping(value = "/medicine")
	public String list(Model model) {
		model.addAttribute("medicineList",medicineRecordService.getAllMedicineRecord());
		return "medicine/medicine";
	}

	@PostMapping(value = "/add")
	public String add(@Valid MedicineRecord medicineRecord) {
		medicineRecordService.add(medicineRecord);
		return "medicine/medicine";
	}

	@PutMapping(value = "/edit")
	public String edit(@Valid MedicineRecord medicineRecord) {
		medicineRecordService.update(medicineRecord);
		return "medicine/medicine";
	}


	@DeleteMapping(value = "/delete")
	public String delete(@RequestParam("id") Integer id) {
		medicineRecordService.delete(id);
		return "medicine/medicine";
	}
}
