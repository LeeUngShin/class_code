package com.example.demo;

import java.time.LocalDate;
import java.time.LocalDateTime;

import org.springframework.format.annotation.DateTimeFormat;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@ToString  // ToString 메서드 사용가능
@Getter  // Getter 메서드 사용가능
@Builder  // 빌드패턴(클래스) 자동 생성 - 빌더 클래스는 setter 메서드 제공(체이닝 방식)
		  //  Memo.builder().fiedl1(value1).field(value2).build();
@Setter
@AllArgsConstructor  // 모든 변수를 매개변수로 받는 생성자 자동생성
@NoArgsConstructor  // 매개변수가 없는 생성자 자동생성
@Table(name = "tbl_bookinfo")  // 테이블 이름 지정(이 애노테이션 안쓰면 클래스명이 테이블명)
@Entity  // 이 클래스로 DB 생성
public class BookInfo {
	
	   @Id  // 기본키 속성
	   @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
	   private Long num;
	   
	   @Column(length = 200, nullable = false, unique = true)  // 일반 속성
	   private String title;
	   
	   @Column(length = 50, nullable = false)  // 일반 속성
	   private String isbn;
	    
	   @Column(length=50, nullable=false)
	   private String author;
	    
//	   @Column(length=50, nullable=false)
//	   //@DateTimeFormat(pattern = "MM-dd-yyyy") 
//	   private LocalDate publishedDate;
	   
	   @Column(length=50, nullable=false)
	   //@DateTimeFormat(pattern = "MM-dd-yyyy") 
	   private LocalDateTime publishedDate;
	   
	   @Column(length=500, nullable=false)
	   private String info;
	   
	   @Column(length=500)
	   private String filePathStr;
	    

}
