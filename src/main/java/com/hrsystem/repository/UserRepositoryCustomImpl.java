package com.hrsystem.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.hrsystem.dto.HrFormDto;
import com.hrsystem.dto.HrSearchDto;
import com.hrsystem.entity.QUser;
import com.hrsystem.entity.User;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

public class UserRepositoryCustomImpl implements UserRepositoryCustom{

	private JPAQueryFactory queryFactory;
	
	public UserRepositoryCustomImpl (EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	private BooleanExpression searchByLike(String searchBy, String searchQuery) {
		if(StringUtils.equals("name", searchBy)) {
			return QUser.user.name.like("%" + searchQuery + "%");
		} else if(StringUtils.equals("phone", searchBy)) {
			return QUser.user.phone.like("%" + searchQuery + "%");
		} else if(StringUtils.equals("dept", searchBy)) {
			return QUser.user.dept.like("%" + searchQuery + "%");
		}
		return null;
	}
	
	
	@Override
	public Page<User> getMainHrPage(HrSearchDto hrSearchDto, Pageable pageable) {
		
		List<User> content = queryFactory
				.selectFrom(QUser.user)
				.where(searchByLike(hrSearchDto.getSearchBy(), hrSearchDto.getSearchQuery()))
				.orderBy(QUser.user.no.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		long total = queryFactory.select(Wildcard.count).from(QUser.user)
				.where(searchByLike(hrSearchDto.getSearchBy(), hrSearchDto.getSearchQuery()))
				.fetchOne();
		
		
		return new PageImpl<>(content, pageable, total);
	}

}
