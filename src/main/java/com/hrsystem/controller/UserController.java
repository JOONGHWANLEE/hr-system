package com.hrsystem.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.hrsystem.dto.HrFormDto;
import com.hrsystem.dto.HrSearchDto;
import com.hrsystem.entity.User;
import com.hrsystem.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class UserController {

	private final UserService userService;
	private final PasswordEncoder passwordEncoder; 
	
	//로그인 화면
		@GetMapping(value = "/users/login")
		public String loginUser() {
			return "user/userLogin";
		}
		
	//회원가입 화면
		@GetMapping(value = "users/new")
		public String userForm(Model model) {
			model.addAttribute("hrFormDto", new HrFormDto());
			return "user/userForm";
		}
		
	//회원가입
		@PostMapping(value = "/users/new")
		public String userForm(@Valid HrFormDto hrFormDto, BindingResult bindingResult, Model model) {
			
			if(bindingResult.hasErrors()) {
				return "user/userForm";
			}
			
			try {
				User user = User.createUser(hrFormDto, passwordEncoder);
				userService.saveUser(user);
			} catch (IllegalStateException e) {
				e.printStackTrace();
				model.addAttribute("errorMessage", e.getMessage());
				return "user/userForm";
			}
			return "redirect:/";
			
		}
		
		@GetMapping(value="/users/login/error")
		public String loginError(Model model) {
			model.addAttribute("loginErrorMsg", "아이디 또는 비밀번호를 확인해주세요.");
			return "user/userLogin";
		}
		
		//직원 상세 페이지
		@GetMapping(value = {"/users/userinfo", "/users/userinfo/{userId}"})
		public String userInfo(Model model, @PathVariable(required = false) String userId, Principal principal) {
			
			String userIdPricipal = userId;
			
			if (userId == null) {
				userIdPricipal = principal.getName();
			} 
			
			HrFormDto hrFormDto = userService.getUserDtl(userIdPricipal);
			model.addAttribute("user", hrFormDto);
			return "user/userInfo";
		}
		
		//직원 정보 수정 페이지
		@GetMapping(value = "/users/usermodify/{userId}")
		public String userDtl(@PathVariable("userId") String userId, Model model) {
			try {
				HrFormDto hrFormDto = userService.getUserDtl(userId);
				model.addAttribute("hrFormDto", hrFormDto);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMessage", "직원 정보를 가져오는 과정에서 에러");
				
				model.addAttribute("hrFormDto", new HrFormDto());
				return "user/userInfo";
			}
			
			
			return "user/userInfoForm";
		}
		
		//직원 정보 수정
		@PostMapping(value = "/users/usermodify/{userId}")
		public String userUpdate(@Valid HrFormDto hrFormDto, Model model, BindingResult bindingResult) {
			
			if(bindingResult.hasErrors()) {
				return "user/userInfo";
			}
			
			try {
				userService.updateUser(hrFormDto);
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("errorMessage", "직원 정보 수정 중 에러가 발생");
				return "user/userInfo";
			}
			
			return "redirect:/";
		}
		
		
	
		//직원 관리 페이지
		@GetMapping(value = {"/users/usermanage", "/users/usermanage/{page}"})
		public String usermanagement(Model model, HrSearchDto hrSearchDto, Optional<Integer> page) {
			Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 6);
			Page<User> users = userService.getMainUserPage(hrSearchDto, pageable);
			
			model.addAttribute("users", users);
			model.addAttribute("hrSearchDto", hrSearchDto);
			model.addAttribute("maxPage", 5);
			
			return "user/userManage";
		}
		
		@DeleteMapping("/users/{userId}/delete")
		public @ResponseBody ResponseEntity deleteUser(@PathVariable("userId") String userId) {
			
			userService.deleteUser(userId);
			return new ResponseEntity<String>(userId, HttpStatus.OK);
		}
		

		
		
}
