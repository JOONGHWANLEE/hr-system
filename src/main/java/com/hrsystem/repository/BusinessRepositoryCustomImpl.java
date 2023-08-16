package com.hrsystem.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.thymeleaf.util.StringUtils;

import com.hrsystem.constant.Approval;
import com.hrsystem.dto.BusinessSearchDto;
import com.hrsystem.entity.BusinessManageMent;
import com.hrsystem.entity.QBusinessManageMent;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.core.types.dsl.Wildcard;
import com.querydsl.jpa.impl.JPAQueryFactory;

import jakarta.persistence.EntityManager;

public class BusinessRepositoryCustomImpl implements BusinessRepositoryCustom{

	private JPAQueryFactory queryFactory;
	
	public BusinessRepositoryCustomImpl(EntityManager em) {
		this.queryFactory = new JPAQueryFactory(em);
	}
	
	private BooleanExpression searchApprovalEq(Approval searchApproval) {
		return searchApproval == null ? null : QBusinessManageMent.businessManageMent.approval.eq(searchApproval);
	}
	
	private BooleanExpression searchByLike(String searchBy, String searchQuery) {
		if(StringUtils.equals("user.name", searchBy)) {
			return QBusinessManageMent.businessManageMent.user.name.like("%" + searchQuery + "%");
		} else if(StringUtils.equals("createBy", searchBy)) {
			return null;
		}
		return null;
	}
	
	
	private BooleanExpression searchUserEq(String no) {
		return StringUtils.equals(no, "ceo1") ? null : QBusinessManageMent.businessManageMent.user.no.eq(no);
	}
	
	@Override
	public Page<BusinessManageMent> getBusinessPage(BusinessSearchDto businessSearchDto, String no, Pageable pageable) {
		
		
		List<BusinessManageMent> content = queryFactory
				.selectFrom(QBusinessManageMent.businessManageMent)
				.where(searchApprovalEq(businessSearchDto.getSearchApproval()),
						searchByLike(businessSearchDto.getSearchBy(), businessSearchDto.getSearchQuery()),
						searchUserEq(no))
				//.where(QBusinessManageMent.businessManageMent.user.no.eq(no))
				.orderBy(QBusinessManageMent.businessManageMent.no.desc())
				.offset(pageable.getOffset())
				.limit(pageable.getPageSize())
				.fetch();
		
		long total = queryFactory.select(Wildcard.count).from(QBusinessManageMent.businessManageMent)
				.where(searchApprovalEq(businessSearchDto.getSearchApproval()),
						searchByLike(businessSearchDto.getSearchBy(), businessSearchDto.getSearchQuery()))
				.fetchOne();
				
		
		
		return new PageImpl<>(content, pageable, total);
	}
	

}
