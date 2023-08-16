package com.hrsystem.service;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrsystem.constant.Approval;
import com.hrsystem.dto.BusinessFormDto;
import com.hrsystem.dto.BusinessSearchDto;
import com.hrsystem.entity.BusinessManageMent;
import com.hrsystem.entity.User;
import com.hrsystem.repository.BusinessRepository;
import com.hrsystem.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
@Transactional
public class BusinessService {
	private final BusinessRepository businessRepository;
	private final UserRepository userRepository;
	
	
	public Long saveBusiness(BusinessFormDto businessFormDto, String no) throws Exception {
	    User user = userRepository.findByNo(no);
	    LocalDateTime now = LocalDateTime.now();

	    BusinessManageMent businessManageMent = businessFormDto.createBusiness(user, now);
	    

	    LocalDate startDate = LocalDate.parse(businessFormDto.getStart());
	    LocalDate endDate = LocalDate.parse(businessFormDto.getEnd());

	    LocalDateTime start = startDate.atStartOfDay();
	    LocalDateTime end = endDate.atStartOfDay().plusDays(1).minusSeconds(1);

	    businessManageMent.setStart(start);
	    businessManageMent.setEnd(end);
	    businessManageMent.setApproval(Approval.STAND_BY);

	    businessRepository.save(businessManageMent);

	    return businessManageMent.getNo();
	}
	
	@Transactional(readOnly = true)
	public BusinessFormDto getBusinessDtl(Long manageNo) {
		BusinessManageMent business = businessRepository.findById(manageNo)
				.orElseThrow(EntityNotFoundException::new);
		
		BusinessFormDto businessFormDto = BusinessFormDto.of(business);
		
		return businessFormDto;
	}
	
	//결재 승인
	public Long approveRecognition(String user, Long no) {
				
		BusinessManageMent business = businessRepository.findById(no)
				.orElseThrow(EntityNotFoundException::new);
		
		LocalDateTime now = LocalDateTime.now();
		
		business.updateBusiness(user, now, Approval.RECOGNITION);
		
		return business.getNo();
	}
	
	//결재 반려
	public Long approveRejected(String user, Long no) {
		
		BusinessManageMent business = businessRepository.findById(no)
				.orElseThrow(EntityNotFoundException::new);
		
		LocalDateTime now = LocalDateTime.now();
		
		business.updateBusiness(user, now, Approval.RETURN);
		
		return business.getNo();
	}
	
//	public Page<BusinessFormDto> getBusinessList(String no, Pageable pageable){
//		List<BusinessManageMent> business = businessRepository.findById(no);
//		
//		
//	}
	
	public Page<BusinessManageMent> getBusinessPage(BusinessSearchDto businessSearchDto, String no, Pageable pageable){
		Page<BusinessManageMent> businessPage = businessRepository.getBusinessPage(businessSearchDto, no, pageable);
		return businessPage;
	}
	
}
