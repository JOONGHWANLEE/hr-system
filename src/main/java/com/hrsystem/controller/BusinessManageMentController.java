package com.hrsystem.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrsystem.dto.BusinessFormDto;
import com.hrsystem.dto.BusinessSearchDto;
import com.hrsystem.dto.HrFormDto;
import com.hrsystem.entity.BusinessManageMent;
import com.hrsystem.service.BusinessService;
import com.hrsystem.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BusinessManageMentController {
	
	private final BusinessService businessService;
	private final UserService userService;
	
	//결재신청화면
	@GetMapping(value = "/business/request")
	public String businessManageMentMain(Model model) {
		model.addAttribute("businessFormDto", new BusinessFormDto());
		return "businessmanagement/businessmanagement";
	}
	
	//결재신청
	@PostMapping(value = "/business/request")
	public String businessForm(@Valid BusinessFormDto businessFormDto, BindingResult bindingResult, Model model,
			Principal principal) {
		
		if(bindingResult.hasErrors()) {
			return "redirect:/";
		}
		
		try {
			String no = principal.getName();
			businessService.saveBusiness(businessFormDto, no);
			
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "결재신청 중 에러가 발생");
			
			return "redirect:/";
		}
		
		return "redirect:/";
		
	}
	
	//결재현황페이지
	@GetMapping(value = {"/business/situation", "/business/situation/{page}"})
	public String viewBusiness(Model model, @PathVariable("page") Optional<Integer> page, BusinessSearchDto businessSearchDto, Principal principal) {
		
		String no = principal.getName();
		model.addAttribute("businessFormDto", new BusinessFormDto());
		
		
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
		Page<BusinessManageMent> business = businessService.getBusinessPage(businessSearchDto, no, pageable);
		
		model.addAttribute("business", business);
		model.addAttribute("businessSearchDto", businessSearchDto);
		model.addAttribute("maxPage", 5);
		
		
		return "businessmanagement/businessSituation";
	}
	
	//결재현황상세페이지
	@GetMapping(value = "/business/situationInfo/{manageNo}")
	public String viewBusinessPage(Model model, @PathVariable("manageNo") Long manageNo) {
		BusinessFormDto businessFormDto = businessService.getBusinessDtl(manageNo);
		model.addAttribute("business", businessFormDto);
		
		return "businessmanagement/businessSituationInfo";
	}
	
	
	//결재승인페이지
	@GetMapping(value = {"/business/recognition", "/business/recognition/{page}"})
	public String viewBusinessRecognition(Model model, @PathVariable("page") Optional<Integer> page, BusinessSearchDto businessSearchDto, Principal principal) {
		String no = principal.getName();
		model.addAttribute("businessFormDto", new BusinessFormDto());
		
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
		Page<BusinessManageMent> business = businessService.getBusinessPage(businessSearchDto, no, pageable);
		
		model.addAttribute("business", business);
		model.addAttribute("businessSearchDto", businessSearchDto);
		model.addAttribute("maxPage", 5);
		
		return "businessmanagement/businessRecognition";
	}
	
	
	//결재승인상세페이지
	@GetMapping(value = "/business/recognitionInfo/{manageNo}")
	public String recognitionPage(Model model, @PathVariable("manageNo") Long manageNo) {
		BusinessFormDto businessFormDto = businessService.getBusinessDtl(manageNo);
		model.addAttribute("business", businessFormDto);
		
		return "businessmanagement/businessRecognitionInfo";
	}
	
	//결재 승인
	@PostMapping(value = "/business/recognitionInfo/recognition/{manageNo}")
	public String approveRecognition(Model model, @PathVariable("manageNo") Long manageNo, Principal principal) {
		String no = principal.getName();
		BusinessFormDto businessFormDto = businessService.getBusinessDtl(manageNo);
		model.addAttribute("business", businessFormDto);
		businessService.approveRecognition(no, manageNo);
		
		return "redirect:/";
	}
	
	//결재 반려
	@PostMapping(value = "/business/recognitionInfo/Rejected/{manageNo}")
	public String approveRejected(Model model, @PathVariable("manageNo") Long manageNo, Principal principal) {
		String no = principal.getName();
		BusinessFormDto businessFormDto = businessService.getBusinessDtl(manageNo);
		model.addAttribute("business", businessFormDto);
		businessService.approveRejected(no, manageNo);
			
		return "redirect:/";
	}
	
//	//결재 반려 자바스크립트
//	@PostMapping(value = "/business/recognitionInfo/Rejected/{manageNo}")
//	public @ResponseBody ResponseEntity approveRejected(Model model, @PathVariable("manageNo") Long manageNo, Principal principal,
//			String returnReason) {
//		String no = principal.getName();
//		BusinessFormDto businessFormDto = businessService.getBusinessDtl(manageNo);
//		model.addAttribute("business", businessFormDto);
//		businessService.approveRejected(no, manageNo, returnReason);
//			
//		return new ResponseEntity<String>(no, HttpStatus.OK);
//	}
	
}
