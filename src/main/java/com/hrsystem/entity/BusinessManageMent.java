package com.hrsystem.entity;

import java.time.LocalDateTime;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.hrsystem.constant.Approval;
import com.hrsystem.constant.Division;

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
@Table(name="businessmanagement")
@Getter
@Setter
@ToString
public class BusinessManageMent {
	
	@Id
	@Column(name = "manage_no")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long no;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user;
	
	@Column(nullable = false)
	private String businessTitle;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Division division;
	
	@Enumerated(EnumType.STRING)
	@Column(nullable = false)
	private Approval approval;
	
	@Column(nullable = false)
	private LocalDateTime start;
	
	@Column(nullable = false)
	private LocalDateTime end;
	
	@Column(nullable = false)
	private LocalDateTime reportingDate; //신청일
	
	private String businessArea;
	
	private String businessReason; //출장사유
	
	private String approvalNo; //승인자
	
	private LocalDateTime approvalDate; //승인일
	
	
	public static BusinessManageMent createBusiness(User user) {
		BusinessManageMent businessManageMent = new BusinessManageMent();
		businessManageMent.setUser(user);
		
		
		return businessManageMent;
	}
	
	public void updateBusiness(String no, LocalDateTime now, Approval approval) {
		this.approvalNo = no;
		this.approvalDate = now;
		this.approval = approval;
	}
	
//	public void updateReturnBusiness(String no, LocalDateTime now, Approval approval, String returnReason) {
//		this.approvalNo = no;
//		this.approvalDate = now;
//		this.approval = approval;
//		this.returnReason = returnReason;
//	}
	
}
