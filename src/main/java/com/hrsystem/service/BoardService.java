package com.hrsystem.service;

import java.time.LocalDateTime;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrsystem.dto.BoardDto;
import com.hrsystem.dto.BoardSearchDto;
import com.hrsystem.entity.Board;
import com.hrsystem.entity.User;
import com.hrsystem.repository.BoardRepository;
import com.hrsystem.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class BoardService {
	
	private final BoardRepository boardRepository;
	private final UserRepository userRepository;
	
	public Long saveBoard(BoardDto boardDto, String no) throws Exception{
		
		
		User user = userRepository.findByNo(no);
		LocalDateTime now = LocalDateTime.now();
		Board board = boardDto.createBoard(now, user);
		boardRepository.save(board);
		
		return board.getNo();
	}
	
	
	
	@Transactional(readOnly = true)
	public Page<Board> getBoardPage(BoardSearchDto boardSearchDto, Pageable pageable){
		Page<Board> boardPage = boardRepository.getBoardPage(boardSearchDto, pageable);
		return boardPage;
	}
	
	public BoardDto getBoardDtl(Long boardId) {
		Board board = boardRepository.findById(boardId)
				.orElseThrow(EntityNotFoundException::new);
		
		BoardDto boardDto = BoardDto.of(board);
		
		return boardDto;
	}
	
	public Long updateBoard(BoardDto boardDto) {
		Board board = boardRepository.findById(boardDto.getNo())
				.orElseThrow(EntityNotFoundException::new);
		
		board.updateBoard(boardDto);
		
		return board.getNo();
	}
	
	
	public void deleteBoard(Long boardId) {
		Board board = boardRepository.findById(boardId)
				.orElseThrow(EntityNotFoundException::new);
				
		boardRepository.delete(board);
	}
	
}
