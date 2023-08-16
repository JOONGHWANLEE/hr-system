package com.hrsystem.entity;

import java.time.LocalDateTime;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="records")
@Getter
@Setter
@ToString
public class Records {
	
	@Id
	@Column(name = "records_no")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long no;
	
	private LocalDateTime request;
	
	private LocalDateTime start;
	
	private String startTime;
	
	
	private LocalDateTime end;
	
	private String endTime;
	
	private String approvalNo;
	
	private LocalDateTime approvalDate;
}
