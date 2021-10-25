package com.demo.microservices.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.demo.microservices.model.SpendingCardVO;

@Mapper
public interface SpendingCardDao {
	
	List<SpendingCardVO> selectSpendingCardAll(int userId); //나의 카드내역 전체 조회 pay_info

	SpendingCardVO selectSpendingCard(int payId); //나의 카드내역 중 한개 상세조회 pay_info

	int insertSpendingCard(SpendingCardVO spending); //카드내역 등록

	int updateSpendingCard(SpendingCardVO spending); //카드내역 수정

	int deleteSpendingCard(int payId); //카드내역 삭제

}