package com.hrsystem.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.hrsystem.dto.BoardSearchDto;
import com.hrsystem.entity.Board;
import com.hrsystem.entity.QBoard;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

public class BoardRepositoryCustomImpl implements BoardRepositoryCustom{

	private JPAQueryFactory queryFactory;
	
	public BoardRepositoryCustomImpl (EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	private BooleanExpression searchByLike(String searchBy, String searchQuery) {
		if(StringUtils.equals("user.name", searchBy)) {
			return QBoard.board.user.name.like("%" + searchQuery + "%");
		}else if(StringUtils.equals("createBy", searchBy)) {
			return null;
		}
		
		return null;
	}
	
	
	@Override
	public Page<Board> getBoardPage(BoardSearchDto boardSearchDto, Pageable pageable) {
		List<Board> content = queryFactory
				.selectFrom(QBoard.board)
				.where(searchByLike(boardSearchDto.getSearchBy(), boardSearchDto.getSearchQuery()))
				.orderBy(QBoard.board.no.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		long total = queryFactory.select(Wildcard.count).from(QBoard.board)
				.where(searchByLike(boardSearchDto.getSearchBy(), boardSearchDto.getSearchQuery()))
				.fetchOne();
		
		
		
		return new PageImpl<>(content, pageable, total);
	}
	
	
	private BooleanExpression boardNmLike(String searchQuery) {
		return StringUtils.isEmpty(searchQuery)? null : QBoard.board.no.like("%" + searchQuery + "%");
	}
	
}
