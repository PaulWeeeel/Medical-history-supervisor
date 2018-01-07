package com.hwwz.medicalhistorysupervisor.controller;

import com.hwwz.medicalhistorysupervisor.configuration.Authorization;
import com.hwwz.medicalhistorysupervisor.configuration.GlobalMed;
import com.hwwz.medicalhistorysupervisor.domain.CaseHistory;
import com.hwwz.medicalhistorysupervisor.domain.Disease;
import com.hwwz.medicalhistorysupervisor.domain.Patient;
import com.hwwz.medicalhistorysupervisor.domain.SymptomFigure;
import com.hwwz.medicalhistorysupervisor.service.CaseHistoryService;
import com.hwwz.medicalhistorysupervisor.service.DiseaseService;
import com.hwwz.medicalhistorysupervisor.service.PatientService;
import com.hwwz.medicalhistorysupervisor.service.SymptomFigureService;
import com.hwwz.medicalhistorysupervisor.utils.fileReception;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author: Aliweea
 * @date: 2017/12/5/005 11:06
 */
@Controller
@RequestMapping(value = "/case-history")
@Authorization  //该类方法都需登录
public class CaseHistoryController {

	@Autowired
	private CaseHistoryService caseHistoryService;

	@Autowired
	private SymptomFigureService symptomFigureService;

	@Autowired
	private DiseaseService diseaseService;

	@Autowired
	private PatientService patientService;

	@GetMapping(value = "/toAdd", params = {"patientId"})
	public String toAdd(Model model, @RequestParam("patientId") Integer patientId) {
		model.addAttribute("patientId", patientId);
		List<Disease> diseaseList=diseaseService.getAllDiseases();
		model.addAttribute("diseases",diseaseList);
		return "case-history/add";
	}

	@PostMapping(value = "/add")
	public String add(HttpServletRequest request, HttpServletResponse response, @RequestParam("files") MultipartFile[] files) {
        Integer patientId = Integer.valueOf(request.getParameter("patientId"));
        String onset = request.getParameter("onset");
        String[] diseases=request.getParameterValues("disease");
        CaseHistory caseHistory = new CaseHistory();
        caseHistory.setOnset(onset);
        //处理disease
		List<Disease> diseaseList=new ArrayList<>();
		for(String disease:diseases)
		{
			Disease dis=diseaseService.getByName(disease);
			diseaseList.add(dis);
		}
		caseHistory.setDiseaseList(diseaseList);
		//处理症状图
		List<String> nameList=new ArrayList<>();
		//获得图片url名单
		nameList= fileReception.receiveMultiple(files, GlobalMed.getSymptom_path());
		List<SymptomFigure> objList=new ArrayList<>();
		for(String fileName:nameList)
		{
			SymptomFigure symptomFigure=new SymptomFigure();
			symptomFigure.setImageUrl("/"+GlobalMed.getSymptom_dir()+fileName);
			symptomFigure.setCaseHistory(caseHistory);
			objList.add(symptomFigure);
		}

		//处理medicine
		//也许可以在插入
		caseHistory.setImageUrlList(objList);
		caseHistoryService.add(caseHistory, patientId);

		for(SymptomFigure symp:objList)
		{
			symptomFigureService.add(symp);
		}
		Patient patient=patientService.getPatientById(patientId);
		Date d = new Date();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
		patient.setLastDate(sdf.format(d));
		patientService.updatePatient(patient);

		return "redirect:/patient/home/" + patientId;
	}

//	@GetMapping(value = "/toEdit", params = {"id"})
//	public String toEdit(Model model, @RequestParam("id") Integer id) {
//		CaseHistory caseHistory = caseHistoryService.getById(id);
//		model.addAttribute("caseHistory", caseHistory);
//		return "case-history/edit";
//	}
//
//	@PutMapping(value = "/edit")
//	public String edit(@Valid CaseHistory caseHistory) {
//		caseHistoryService.update(caseHistory);
//		return "redirect:/case-history/list";
//	}
//
//	@DeleteMapping(value = "/delete", params = {"id"})
//	public String delete(@RequestParam("id") Integer id){
//		caseHistoryService.delete(id);
//		return "redirect:/case-history/list";
//	}

}
