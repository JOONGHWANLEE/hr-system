package com.hrsystem.entity;

import org.springframework.security.crypto.password.PasswordEncoder;

import com.hrsystem.constant.Role;
import com.hrsystem.dto.HrFormDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="user")
@Getter
@Setter
@ToString
public class User {

	@Id
	@Column(name="user_id")
	private String no;
	
	@Column(nullable = false)
	private String name;
	
	@Column(nullable = false)
	private String dept;
	
	@Column(nullable = false)
	private String position;
	
	@Column(nullable = false)
	private String password;
	
	private String phone;
	
	@Enumerated(EnumType.STRING)
	private Role role;
	
	public static User createUser(HrFormDto hrFormDto, PasswordEncoder passwordEncoder) {
		//패스워드 암호화
		String password = passwordEncoder.encode(hrFormDto.getPassword());
		
		//MemberFormDto를 -> Member 엔티티 객체로 변환
		User user = new User();
		user.setNo(hrFormDto.getNo());
		user.setName(hrFormDto.getName());
		user.setPassword(password);
		user.setPhone(hrFormDto.getPhone());
		user.setPosition(hrFormDto.getPosition());
		user.setDept(hrFormDto.getDept());
//		user.setRole(Role.ADMIN); // 관리자로 가입할때
		user.setRole(Role.USER); // 일반 사용자로 가입할때
		
		return user;
		
	}
	
	public void updateUser(HrFormDto hrFormDto) {
		this.name = hrFormDto.getName();
		this.dept = hrFormDto.getDept();
		this.position = hrFormDto.getPosition();
		this.phone = hrFormDto.getPhone();
	}
	
	
}
