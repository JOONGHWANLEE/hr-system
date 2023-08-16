package com.hrsystem.repository;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrsystem.entity.BusinessManageMent;

public interface BusinessRepository extends JpaRepository<BusinessManageMent, Long>, BusinessRepositoryCustom{
	
	BusinessManageMent findByNo(Long no);
//	@Query("select o from Businessmanagement o where o.user.no = :userId")
//	List<BusinessManageMent> findBusinessManageMents(@Param("userId") String no, Pageable pageable);
}
