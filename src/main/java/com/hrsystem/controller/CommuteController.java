package com.hrsystem.controller;

import java.security.Principal;
import java.time.LocalDateTime;
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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrsystem.dto.CommuteDto;
import com.hrsystem.dto.CommuteSearchDto;
import com.hrsystem.dto.HrFormDto;
import com.hrsystem.entity.Commute;
import com.hrsystem.entity.User;
import com.hrsystem.repository.UserRepository;
import com.hrsystem.service.CommuteService;
import com.hrsystem.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CommuteController {
	private final CommuteService commuteService;
    private final UserService userService;
	
	@GetMapping(value = {"/commute/main", "/commute/main/{page}"})
	public String loginUser(Principal principal, Model model ,User user,
			@PathVariable("page") Optional<Integer> page, CommuteSearchDto commuteSearchDto) {
		
		String userNo = principal.getName();
		model.addAttribute("userNo", userNo);
		
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
		Page<Commute> commutes = commuteService.getCommutePage(commuteSearchDto, pageable);
		
		model.addAttribute("commutes", commutes);
		model.addAttribute("commuteSearchDto", commuteSearchDto);
		model.addAttribute("maxPage", 5);
		
		return "commute/commutemain";
	}
	
//	//출근하기
//	@PostMapping(value = "/commute/main/start")
//	public String commute (Principal principal, Model model) {
//		
//		try {
//			String no = principal.getName();
//			commuteService.startCommute(no);
//			
//		} catch (IllegalStateException e) {
//			//e.printStackTrace();
//			model.addAttribute("errorMessage", e.getMessage());
//		} 
//		
//		
//	    return "redirect:/"; //성공 시 
//	}
	
	//출근하기 자바스크립트
	@PostMapping(value = "/commute/main/start")
	public @ResponseBody ResponseEntity startUser(Principal principal, Model model) {
		String no = principal.getName();
		
		try {
			commuteService.startCommute(no);
			
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", e.getMessage());
		}
		
		return new ResponseEntity<String>(no, HttpStatus.OK);
	}
	
//	//퇴근하기
//	@PostMapping(value = "/commute/main/leave")
//	public String commuteLeave ( Principal principal, Model model) {
//		
//		
//		String no = principal.getName();
//		commuteService.endCommute(no);
//		
//		return "commute/commutemain";
//	}
	
	
	//퇴근하기 자바스크립트
		@PostMapping(value = "/commute/main/leave")
		public @ResponseBody ResponseEntity leaveUser (Principal principal) {
			String no = principal.getName();
			
			try {
				commuteService.endCommute(no);
				
			} catch (Exception e) {
				e.printStackTrace();
			}
			
			
			return new ResponseEntity<String>(no, HttpStatus.OK);
		}
	
	
	
}
