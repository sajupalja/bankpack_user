package com.demo.microservices.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SpendingCardVO {
	private int cardId ; //카드 pk
	private String cardCmpyCd; //카드사코드
	private String cardCmpyName; //카드사이름
	private String cardNo; //카드번호
	private String cardName; //카드 별칭
	private String useYn; //카드 토글여부
	private int userId; //유저pk
}
