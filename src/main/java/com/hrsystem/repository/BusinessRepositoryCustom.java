package com.hrsystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hrsystem.dto.BusinessSearchDto;
import com.hrsystem.entity.BusinessManageMent;

public interface BusinessRepositoryCustom {
	Page<BusinessManageMent> getBusinessPage(BusinessSearchDto businessSearchDto, String no, Pageable pageable);
}
