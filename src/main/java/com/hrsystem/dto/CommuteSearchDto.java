package com.hrsystem.dto;

import com.hrsystem.constant.CommuteDivision;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommuteSearchDto {
	private String searchDateType;
	private CommuteDivision searchCommuteDivision;
	private String searchBy;
	private String searchQuery = "";
}
