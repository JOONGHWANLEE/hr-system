package com.hrsystem.dto;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.modelmapper.ModelMapper;

import com.hrsystem.constant.Approval;
import com.hrsystem.constant.Division;
import com.hrsystem.entity.BusinessManageMent;
import com.hrsystem.entity.User;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessFormDto {
	
	private Long no;
	
	private String businessTitle;
	
	private User user;
	
	private Division division;
	
	private Approval approval;
	
	private String start;
	
	private String end;
	
	private String businessArea;
	
	private String businessReason;
	
	private LocalDateTime reportingDate;
	
	private String approvalNo;
	
	private String approvalDate;
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public BusinessManageMent createBusiness(User user, LocalDateTime regdate) {
		this.user = user;
		this.reportingDate = regdate;
		return modelMapper.map(this, BusinessManageMent.class);
	}
	
	public static BusinessFormDto of(BusinessManageMent business) {
		return modelMapper.map(business, BusinessFormDto.class);
	}
	
//	public void setData(BusinessManageMent business) {
//		this.no = business.getNo();
//		this.user = business.getUser();
//		this.businessTitle = business.getBusinessTitle();
//		this.division = business.getDivision();
//		this.start = business.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//		this.end = business.getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//		this.businessArea = business.getBusinessArea();
//		this.businessReason = business.getBusinessReason();
//		this.approvalNo = business.getApprovalNo();
//		this.approvalDate = business.getApprovalDate().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
//	}
	
}
