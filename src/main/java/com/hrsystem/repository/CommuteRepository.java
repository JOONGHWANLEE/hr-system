package com.hrsystem.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.hrsystem.entity.Commute;

public interface CommuteRepository extends JpaRepository<Commute, Long>, CommuteRepositoryCustom{
	
//	@Query("select o from Commute o where ")
//	List<Commute> findCommutes(@Param("id") String id);
	
//	Commute findByStartTime(String no, LocalDateTime start);
	
//	Commute findByTime(String no, LocalDateTime time);
	
	//List<Commute> findByNo(Long no);
	
	@Query(value = "select * from commute where user_id = :userId and date_format(start, '%Y-%m-%d') = date_format(now(), '%Y-%m-%d')", 
			nativeQuery = true)
	Commute findByUserIdAndStart(@Param("userId") String userId);
	
	
	@Query(value = "select * from commute where user_id = :userId and (commute_division = 'work' or commute_division = 'late')", 
			nativeQuery = true)
	Commute findByUserIdAndCommuteDivision(@Param("userId") String userId);
	
//	@Query("select count(o) from Commute o where o.commuteId = :commuteId")
//	Long countCommute(@Param("commuteId") String commuteId);
	
}
