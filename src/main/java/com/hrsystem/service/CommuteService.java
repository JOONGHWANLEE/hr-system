package com.hrsystem.service;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrsystem.constant.CommuteDivision;
import com.hrsystem.dto.CommuteDto;
import com.hrsystem.dto.CommuteSearchDto;
import com.hrsystem.entity.Commute;
import com.hrsystem.entity.User;
import com.hrsystem.repository.CommuteRepository;
import com.hrsystem.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class CommuteService {
	private final UserRepository userRepository;
	private final CommuteRepository commuteRepository;
	
	
	
	//출근하기
	public Long startCommute(String no) {
		
		User user = userRepository.findByNo(no);
		Commute commuteCheck = commuteRepository.findByUserIdAndStart(no); 
		
		if(commuteCheck != null) {
			throw new IllegalStateException("이미 출근 완료했습니다.");
		}
		
		LocalDateTime now = LocalDateTime.now();
	    LocalDateTime commuteTime = now.withHour(10).withMinute(30);
	    LocalDateTime endTime = now.withHour(15).withMinute(30);
	    
//	    if(commuteCheck == null) {}
	    	Commute commute = new Commute();
	    	commute.setUser(user); //이메일 
	    	commute.setStart(now); //현재시간(시작)
	    	commute.setEnd(now); //현재시간 (끝난)
	    	commute.setTime(commuteTime); //(출근 지정 시간)
	    	commute.setEndtime(endTime); //(퇴근 지정 시간)
	    	commute.setCommuteDivision(lateCheck(now, commuteTime)); //if문으로 (지각 시간체크)
	    	Commute commuteResult = commuteRepository.save(commute);  // 값 저장
	    	
	    	return commuteResult.getNo();

	}
	
	//퇴근하기
	public Long endCommute(String no) {

		LocalDateTime now = LocalDateTime.now();
		LocalDateTime endTime = now.withHour(18).withMinute(30);
	//	DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		
		Commute commute = commuteRepository.findByUserIdAndStart(no);


		
		commute.updateCommute(now, leaveCheck(now, endTime));

		
		return commute.getNo();
	}
	
	
	
	//지각인지 처리
	private CommuteDivision lateCheck(LocalDateTime currentTime, LocalDateTime commuteTime) {
        if (currentTime.isAfter(commuteTime)) {
            return CommuteDivision.LATE;
        } else {
            return CommuteDivision.WORK; 
        }
    }
	
	//퇴근 처리
	private CommuteDivision leaveCheck(LocalDateTime currentTime, LocalDateTime commuteTime) {
        if (currentTime.isAfter(commuteTime)) {
            return CommuteDivision.LEAVE;
        } else {
            return CommuteDivision.WORK; 
        }
    }
	
	@Transactional(readOnly = true)
	public Page<Commute> getCommutePage(CommuteSearchDto commuteSearchDto, Pageable pageable){
		Page<Commute> commutePage = commuteRepository.getCommutePage(commuteSearchDto, pageable);
		return commutePage;
	}
	

}
