package com.demo.microservices.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SpendingAccountVO {
	private int accountId ; //계좌 pk
	private String bankCd; //은행코드
	private String bankName; //은행이름
	private String acntNo; //계좌번호
	private String acntName; //계좌 별칭
	private int balAmt; //계좌 잔액
	private String useYn; //계좌 토글여부
	private int userId; //유저pk
}
