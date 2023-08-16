package com.hrsystem.dto;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;

import com.hrsystem.entity.Board;
import com.hrsystem.entity.User;
import com.hrsystem.repository.BoardRepository;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BoardDto {
	
	private Long no;
	
	private String title;
	
	private User user;
	
	private String content;
	
	private LocalDateTime regdate;
	
//	public BoardDto(Board board) {
//		this.title = board.getTitle();
//		this.user = board.getUser();
//		this.content = board.getContent();
//		this.regdate = board.getRegdate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
//	}
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public Board createBoard(LocalDateTime regdate, User user) {
		this.regdate = regdate;
		this.user = user;
		return modelMapper.map(this, Board.class);
	}
	
	public static BoardDto of(Board board) {
		return modelMapper.map(board, BoardDto.class);
	}
	
	
	
	
	
	
}
