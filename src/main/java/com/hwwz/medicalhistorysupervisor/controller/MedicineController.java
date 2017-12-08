package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.domain.Medicine;
import com.hwwz.medicalhistorysupervisor.service.MedicineService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/8/008 11:56
 */
@Controller
@RequestMapping(value = "/medicine")
public class MedicineController {
	
	@Autowired
	private MedicineService medicineService;
	
	@GetMapping(value = "/list")
	public String list(Model model) {
		List<Medicine> medicines = medicineService.getAllMedicines();
		model.addAttribute("medicines", medicines);
		return "medicine/list";
	}

	@GetMapping(value = "/toEdit")
	public String toEdit(Model model, @RequestParam("id") Integer id) {
		Medicine medicine = medicineService.getMedicineById(id);
		model.addAttribute("medicine", medicine);
		return "medicine/edit";
	}

	@PutMapping(value = "/edit")
	public String edit(@Valid Medicine medicine, BindingResult bindingResult) throws Exception {
		if(bindingResult.hasErrors()){
			throw new Exception(bindingResult.getFieldError().getDefaultMessage());
		}
		try {
			medicineService.updateMedicine(medicine);
		} catch (Exception e) {
			throw e;
		}
		return "redirect:/medicine/list";
	}


	@DeleteMapping(value = "/delete")
	public String delete(@RequestParam("id") Integer id) throws Exception {
		try {
			medicineService.deleteMedicine(id);
		} catch (Exception e) {
			throw e;
		}
		return "redirect:/medicine/list";
	}
}
