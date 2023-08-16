package com.hrsystem.entity;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import com.hrsystem.dto.BoardDto;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.Lob;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Entity
@Table(name="board")
@Getter
@Setter
@ToString
public class Board {
	
	@Id
	@Column(name = "board_id")
	@GeneratedValue(strategy = GenerationType.AUTO)
	private Long no;
	
	@Column(nullable = false)
	private String title;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	@OnDelete(action = OnDeleteAction.CASCADE)
	private User user; //작성자
	
	@Lob //clob과 같은 큰타입의 문자타입으로 컬럼을 만든다
	@Column(nullable = false)
	private String content;
	
	@Column(nullable = false)
	private LocalDateTime regdate;
	
	
	public void updateBoard(BoardDto boardDto, LocalDateTime now) {
		this.title = boardDto.getTitle();
		this.content = boardDto.getContent();
		this.regdate = now;
	}
	
//	public Board(LocalDateTime regdate, User user, BoardDto boardDto) {
//		this.user = user;
//		this.regdate = regdate;
//		this.title = boardDto.getTitle();
//		this.content = boardDto.getContent();
//	}
	
}
