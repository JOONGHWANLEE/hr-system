package com.hrsystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hrsystem.dto.BoardSearchDto;
import com.hrsystem.entity.Board;

public interface BoardRepositoryCustom {
	Page<Board> getBoardPage(BoardSearchDto boardSearchDto, Pageable pageable);
}
