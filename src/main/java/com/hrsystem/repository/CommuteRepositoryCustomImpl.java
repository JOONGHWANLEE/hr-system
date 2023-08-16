package com.hrsystem.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.hrsystem.constant.CommuteDivision;
import com.hrsystem.dto.CommuteSearchDto;
import com.hrsystem.entity.Commute;
import com.hrsystem.entity.QCommute;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

public class CommuteRepositoryCustomImpl implements CommuteRepositoryCustom {
	
	private JPAQueryFactory queryFactory;
	
	public CommuteRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	private BooleanExpression searchDivisionEq(CommuteDivision searchCommuteDivision) {
		return searchCommuteDivision == null ? null : QCommute.commute.commuteDivision.eq(searchCommuteDivision);
	}
	
	private BooleanExpression searchByLike(String searchBy, String searchQuery) {
		if(StringUtils.equals("user.name", searchBy)) {
			return QCommute.commute.user.name.like("%" + searchQuery + "%");
		} else if(StringUtils.equals("createBy", searchBy)) {
			return null;
		}
			
		return null;
	}
	
	
	@Override
	public Page<Commute> getCommutePage(CommuteSearchDto commuteSearchDto, Pageable pageable) {
		
		
		List<Commute> content = queryFactory
				.selectFrom(QCommute.commute)
				.where(searchDivisionEq(commuteSearchDto.getSearchCommuteDivision()),
						searchByLike(commuteSearchDto.getSearchBy(), commuteSearchDto.getSearchQuery()))
				.orderBy(QCommute.commute.no.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		long total = queryFactory.select(Wildcard.count).from(QCommute.commute)
				.where(searchDivisionEq(commuteSearchDto.getSearchCommuteDivision()),
						searchByLike(commuteSearchDto.getSearchBy(), commuteSearchDto.getSearchQuery()))
				.fetchOne();
				
		
		return new PageImpl<>(content, pageable, total);
	}
	
	private BooleanExpression commuteNmLike(String searchQuery) {
		return StringUtils.isEmpty(searchQuery) ? null : QCommute.commute.no.like("%" + searchQuery + "%");
	}
	
	
	
	
	

}
