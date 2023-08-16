package com.hrsystem.dto;

import org.modelmapper.ModelMapper;

import com.hrsystem.entity.User;
import com.querydsl.core.annotations.QueryProjection;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HrFormDto {
	
	@NotBlank(message = "이름은 필수입력 값입니다.")
	private String name;

	@NotEmpty(message = "사번은 필수입력 값입니다.")
	private String no;
	
	@NotEmpty(message = "비밀번호는 필수입력 값입니다.")
	private String password;
	
	private String phone;
	
	private String position;
	
	private String dept;
	
	
//	@QueryProjection
//	public HrFormDto(String name, String no, String password, String phone, String position, String dept) {
//		this.name = name;
//		this.no = no;
//		this.password = password;
//		this.phone = phone;
//		this.position = position;
//		this.dept = dept;
//	}
	
	private static ModelMapper modelMapper = new ModelMapper();
	
	public User createUser() {
		return modelMapper.map(this, User.class);
	}
	
	public static HrFormDto of(User user) {
		return modelMapper.map(user, HrFormDto.class);
	}
	
	
}
