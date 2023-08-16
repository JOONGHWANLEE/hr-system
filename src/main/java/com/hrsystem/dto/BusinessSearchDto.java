package com.hrsystem.dto;

import com.hrsystem.constant.Approval;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BusinessSearchDto {
	private String searchDateType;
	private Approval searchApproval;
	private String searchBy;
	private String searchQuery = "";
}
