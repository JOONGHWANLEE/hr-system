package com.hrsystem.service;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.hrsystem.dto.HrFormDto;
import com.hrsystem.dto.HrSearchDto;
import com.hrsystem.entity.User;
import com.hrsystem.repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class UserService implements UserDetailsService{
	
	private final UserRepository userRepository;
	
	public User saveUser(User user) {
		validateDuplicateUser(user);
		User savedUser = userRepository.save(user);
		return savedUser;
	}
	
	private void validateDuplicateUser(User user) {
		User findUser = userRepository.findByNo(user.getNo());
		if(findUser != null) {
			throw new IllegalStateException("이미 가입된 직원입니다");
		}
	}
	
	public User findUserNo(String user) {
		return userRepository.findByNo(user);
	}

	@Override
	public UserDetails loadUserByUsername(String no) throws UsernameNotFoundException {
		User user = userRepository.findByNo(no);
		
		if(user == null) {
			throw new UsernameNotFoundException(no);
		}
		
		return org.springframework.security.core.userdetails.User.builder()
				.username(user.getNo())
				.password(user.getPassword())
				.roles(user.getRole().toString())
				.build();
	}
	
	@Transactional(readOnly = true)
	public Page<User> getMainUserPage(HrSearchDto hrSearchDto, Pageable pageable){
		Page<User> userPage = userRepository.getMainHrPage(hrSearchDto, pageable);
		return userPage;
	}
	
	public void deleteUser(String userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(EntityNotFoundException::new);
		
		userRepository.delete(user);
				
	}
	
	
	@Transactional(readOnly = true)
	public HrFormDto getUserDtl(String userId) {
		User user = userRepository.findById(userId)
				.orElseThrow(EntityNotFoundException::new);
		
		HrFormDto hrFormDto = HrFormDto.of(user);
		
		return hrFormDto;
		
		
	}
	
	//직원 정보 수정하기
	public String updateUser(HrFormDto hrFormDto) {
		User user = userRepository.findById(hrFormDto.getNo())
								  .orElseThrow(EntityNotFoundException::new);
		
		user.updateUser(hrFormDto);
		
		return user.getNo();
		
		
	}
	
	
	
}
