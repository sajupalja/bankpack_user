package com.demo.microservices.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.demo.microservices.model.SpendingTravelVO;

@Mapper
public interface SpendingTravelDao {
	
	List<SpendingTravelVO> selectSpendingTravelAll(int trvlId, String searchPayType); //여행별 결제내역 조회 trvl_pay_info

	SpendingTravelVO selectSpendingTravel(int trvlPayId); //여행별 결제내역 조회 trvl_pay_info
	
	int insertSpendingTravel(SpendingTravelVO spending); //여행별 결제내역 등록
	
	int updateSpendingTravel(SpendingTravelVO spending); //여행별 결제내역 수정
	
	int deleteSpendingTravel(int trvlPayId); //여행별 결제내역 삭제

	
	
	int updateSpendingTravelRate(int trvlId); //여행비 사용 금액 비율 갱신하기
	
	int getTrvlIdWithTrvlPayId(int trvlPayId); //여행결제내역 id로 여행 id 가져오기

}