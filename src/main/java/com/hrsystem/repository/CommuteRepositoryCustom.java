package com.hrsystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hrsystem.dto.CommuteSearchDto;
import com.hrsystem.entity.Commute;

public interface CommuteRepositoryCustom {
	Page<Commute> getCommutePage(CommuteSearchDto commuteSearchDto, Pageable pageable);
}
