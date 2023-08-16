package com.hrsystem.entity;


import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.hrsystem.constant.CommuteDivision;
import com.hrsystem.dto.CommuteDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="commute")
@Getter
@Setter
@ToString
public class Commute{

	@Id
	@Column(name = "commute_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long no;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@Enumerated(EnumType.STRING)
	private CommuteDivision commuteDivision;
	
	@Column(nullable = false)
	private LocalDateTime start;
	
	@Column(nullable = false)
	private LocalDateTime end;
	
	@Column(nullable = false)
	private LocalDateTime time;
	
	@Column(nullable = false)
	private LocalDateTime endtime;
	
	
	//commute 엔티티 수정
	public void updateCommute(LocalDateTime now, CommuteDivision commuteDivi) {
		//String endString = commuteDto.getEnd();
		//System.out.println("endString:" + endString);
		
		//DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
	    //LocalDateTime endDateTime = LocalDateTime.parse(now, formatter);
	    this.end = now;
	    this.commuteDivision = commuteDivi;
	}
	
	
	
	
}
