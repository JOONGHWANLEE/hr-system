package com.hrsystem.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.hrsystem.dto.HrFormDto;
import com.hrsystem.dto.HrSearchDto;
import com.hrsystem.entity.User;

public interface UserRepositoryCustom {
	
	
	Page<User> getMainHrPage(HrSearchDto hrSearchDto, Pageable pageable);
}
