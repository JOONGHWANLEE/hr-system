package com.hrsystem.controller;

import java.security.Principal;
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
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.hrsystem.dto.BoardDto;
import com.hrsystem.dto.BoardSearchDto;
import com.hrsystem.dto.HrFormDto;
import com.hrsystem.entity.Board;
import com.hrsystem.entity.User;
import com.hrsystem.service.BoardService;
import com.hrsystem.service.UserService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;

@Controller
@RequiredArgsConstructor
public class BoardController {
	private final BoardService boardService;
	private final UserService userService;

	@GetMapping(value = {"/boards/main", "/boards/main/{page}"})
	public String viewBoard(Model model, @PathVariable("page") Optional<Integer> page, 
			Board board, BoardSearchDto boardSearchDto) {
		
		
		Pageable pageable = PageRequest.of(page.isPresent() ? page.get() : 0, 5);
		Page<Board> boards = boardService.getBoardPage(boardSearchDto, pageable);
		
		
		model.addAttribute("boards", boards);
		model.addAttribute("boardSearchDto", boardSearchDto);
		model.addAttribute("maxPage", 5);
		
		
		return "board/boardMain";
	}
	
	//공지사항 작성 페이지
	@GetMapping(value = "/boards/write")
	public String boardForm(Model model) {
		model.addAttribute("boardDto", new BoardDto());
		return "board/boardForm";
	}
	
	//공지사항 작성
	@PostMapping(value = "/boards/write")
	public String boardForm(@Valid BoardDto boardDto, BindingResult bindingResult, 
			Model model, Principal principal) {
		if(bindingResult.hasErrors()) {
			return "board/boardForm";
		}
		
		try {
			String no = principal.getName();
			System.out.println(boardDto.getContent() + "boardDto::");
			boardService.saveBoard(boardDto, no);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "공지사항 작성 중 에러가 발생");
			return "board/boardForm";
		}
		return "redirect:/";
		
	}
	
	//공지사항 상세 페이지
	@GetMapping(value = "/boards/boardInfo/{boardId}")
	public String boardInfo(Model model, @PathVariable("boardId") Long boardId) {
		BoardDto boardDto = boardService.getBoardDtl(boardId);
		model.addAttribute("board", boardDto);
		return "board/boardInfo";
	}
	
	//공지사항 수정 페이지
	@GetMapping(value = "/boards/boardInfoForm/{boardId}")
	public String boardDtl(@PathVariable("boardId") Long boardId, Model model) {
		try {
			BoardDto boardDto = boardService.getBoardDtl(boardId);
			model.addAttribute("boardDto", boardDto);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "공지사항 정보를 가져오는 과정에서 에러");
			model.addAttribute("boardDto", new BoardDto());
			return "board/boardInfo";
		}
		
		return "board/boardInfoForm";
	}
	
	//공지사항 수정
	@PostMapping(value = "/boards/boardInfoForm/{boardId}")
	public String boardUpdate(@Valid BoardDto boardDto, Model model, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "board/boardInfo";
		}
		
		try {
			boardService.updateBoard(boardDto);
		} catch (Exception e) {
			e.printStackTrace();
			model.addAttribute("errorMessage", "공지사항 수정 중 에러 발생");
			return "board/boardInfo";
		}
		
		return "redirect:/";
	}
	
	@DeleteMapping("/boards/boardInfo/{boardId}/delete")
	public @ResponseBody ResponseEntity deleteBoard(@PathVariable("boardId") Long boardId) {
		boardService.deleteBoard(boardId);
		return new ResponseEntity<Long>(boardId, HttpStatus.OK);
	}
	
	
	
}
