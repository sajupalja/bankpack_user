package com.demo.microservices.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.demo.microservices.model.SpendingAccountVO;

@Mapper
public interface SpendingAccountDao {
	
	List<SpendingAccountVO> selectSpendingAccountAll(int userId); //나의 계좌내역 전체 조회 

	int selectSpendingAccountBudget(int userId); //나의 전체 예산 불러오기

	SpendingAccountVO selectSpendingAccount(int payId); //나의 계좌내역 중 한개 상세조회 

	int insertSpendingAccount(SpendingAccountVO spending); //계좌내역 등록

	int updateSpendingAccount(SpendingAccountVO spending); //계좌내역 수정

	int deleteSpendingAccount(int payId); //계좌내역 삭제

}