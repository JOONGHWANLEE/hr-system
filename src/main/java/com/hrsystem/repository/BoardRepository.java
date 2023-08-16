package com.hrsystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.hrsystem.entity.Board;

public interface BoardRepository extends JpaRepository<Board, Long>, BoardRepositoryCustom{
	
	
}
