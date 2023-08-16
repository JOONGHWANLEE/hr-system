package com.hrsystem.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HrSearchDto {
	private String searchName;
	
	private String searchBy;
	
	private String searchQuery = "";
}
