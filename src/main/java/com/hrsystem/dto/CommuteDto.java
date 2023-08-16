package com.hrsystem.dto;






import com.hrsystem.constant.CommuteDivision;

import com.hrsystem.entity.User;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CommuteDto {	
	private Long commute_id;
	
	
	private User user;
	
	private CommuteDivision commuteDivision;
	
	private String start;
	
	private String end;
	
	private String time;
	
	private String endtime;
	
	/*
	public void setData(Commute commute) {
		this.commute_id = commute.getNo();
		this.user = commute.getUser();
		this.commuteDivision = commute.getCommuteDivision();
		this.start = commute.getStart().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		this.end = commute.getEnd().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		this.time = commute.getTime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		this.endtime = commute.getEndtime().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
	}
	*/
	
	

	
	
}
