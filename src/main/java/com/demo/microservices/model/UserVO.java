package com.demo.microservices.model;

import java.util.Date;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class UserVO {
	private int userId ; // 사용자 pk
	private String loginId; // 로그인 아이디
	private String loginPw; // 로그인 패스워드 
	private String userName; // 사용자 이름
	private String phNo; // 핸드폰 번호
	private int inputId; // 최초 작성자
	private Date inputDt; // 최초 작성일자 
	private int modifyId; // 수정자
	private Date modifyDt; // 수정일자 
}
