package com.demo.microservices.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.demo.microservices.model.SpendingPaymentVO;

@Mapper
public interface SpendingPaymentDao {
	
	List<SpendingPaymentVO> selectSpendingPaymentAll(int userId); //나의 결제내역 전체 조회 pay_info

	SpendingPaymentVO selectSpendingPayment(int payId); //나의 결제내역 중 한개 상세조회 pay_info

	int insertSpendingPayment(SpendingPaymentVO spending); //결제내역 등록

	int updateSpendingPayment(SpendingPaymentVO spending); //결제내역 수정

	int deleteSpendingPayment(int payId); //결제내역 삭제

}