package com.example.demo;

import org.hibernate.validator.constraints.UniqueElements;

import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.Builder;

@ToString  // ToString 메서드 사용가능
@Getter  // Getter 메서드 사용가능
@Builder  // 빌드패턴(클래스) 자동 생성 - 빌더 클래스는 setter 메서드 제공(체이닝 방식)
		  //  Memo.builder().fiedl1(value1).field(value2).build();
@Setter
@AllArgsConstructor  // 모든 변수를 매개변수로 받는 생성자 자동생성
@NoArgsConstructor  // 매개변수가 없는 생성자 자동생성
@Table(name = "tbl_memo")  // 테이블 이름 지정(이 애노테이션 안쓰면 클래스명이 테이블명)
@Entity  // 이 클래스로 DB 생성
public class Memo {
	
    @Id  // 기본키 속성
    @GeneratedValue(strategy = GenerationType.IDENTITY) //MySQL의 AUTO_INCREMENT를 사용
    private Long num;
    
    @Column(length = 200, nullable = false, unique = true)  // 일반 속성
    @NotBlank(message = "password cannot be blank")
    private String id;
    
    @Column(length = 200, nullable = false)  // 일반 속성
    @NotBlank(message = "password cannot be blank")
    private String pw;
    
    @Column(length=40, nullable=false)
    @NotBlank(message = "name cannot be blank")
    private String name;
    
    @Column(length=40, nullable=false)
    @NotBlank(message = "address cannot be blank")
    private String addr;
    
    @Column(nullable = false)
    // @NotBlank(message = "age cannot be blank")  int는 이 애노테이션 불가
    private int age;
    
    @Column
    @Email
    private String email;

    

}
