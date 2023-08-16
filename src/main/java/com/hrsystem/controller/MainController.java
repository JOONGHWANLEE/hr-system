package com.hrsystem.controller;

import java.security.Principal;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import com.hrsystem.dto.BoardSearchDto;
import com.hrsystem.dto.CommuteDto;
import com.hrsystem.dto.HrFormDto;
import com.hrsystem.entity.Board;
import com.hrsystem.entity.User;
import com.hrsystem.service.BoardService;
import com.hrsystem.service.UserService;

import lombok.RequiredArgsConstructor;

import org.springframework.ui.Model;

@Controller
@RequiredArgsConstructor
public class MainController {
	private final UserService userService;
	private final BoardService boardService;
		
	@GetMapping(value = "/")
	public String main(Model model,	BoardSearchDto boardSearchDto, Optional<Integer> page,
			Principal principal) {
		
		
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
		Page<Board> boards = boardService.getBoardPage(boardSearchDto, pageable);
		
		User user = userService.findUserNo(principal.getName());
		
		HrFormDto hrFormDto = userService.getUserDtl(user.getNo());
		model.addAttribute("user", hrFormDto);
		model.addAttribute("boards", boards);
		model.addAttribute("boardSearchDto", boardSearchDto);
		
		return "main";
	}
	
	
	
}
