package com.demo.microservices.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SpendingTravelVO {
	private int userId ; // 사용자 아이디
	private int trvlPayId; //여행결제내역 아이디
	private int trvlId; //내 여행 아이디
	private String trvlName; //내 여행 명칭
	private int payAmt; //결제금액
	private int balAmt; //잔액
	private String payDt; //결제일시
	private String payName; //결제명
	private int payMethod; //결제수단 카드pk or 현금(0)
	private String payMethodName; //결제수단 명칭 (카드이름 혹은 현금)
	private int payType; //결제유형 코드 (식비, 숙박비, 등)
	private String payTypeName; //결제유형 이름 (식비, 숙박비, 등)
	private int payId; //내 결제내역 아이디
	private String processYn; //진행여부
}
