package com.hrsystem.controller;

import java.security.Principal;

import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import com.hrsystem.service.CommuteService;
import com.hrsystem.service.UserService;

import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class CalendarController {
 private CommuteService commuteService;
	//로그인 화면
			@GetMapping(value = "/calendar/main")
			public String loginUser() {
				return "calendar/calendarmain";
			}
}
