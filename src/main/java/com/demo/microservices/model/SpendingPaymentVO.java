package com.demo.microservices.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SpendingPaymentVO {
	private int userId ; // 사용자 아이디
	private int payAmt; //결제금액
	private int balAmt; //잔액
	private String payDt; //결제일시
	private String payName; //결제명
	private int payMethod; //결제수단 카드pk or 현금(0)
	private String payMethodName; //결제수단 명칭 (카드이름 혹은 현금)
	private int payId; //내 결제내역 아이디
	private String processYn; //진행여부
}
