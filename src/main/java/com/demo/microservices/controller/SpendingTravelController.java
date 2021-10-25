package com.demo.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.demo.microservices.dao.SpendingTravelDao;
import com.demo.microservices.model.SpendingTravelVO;

import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/spending")
public class SpendingTravelController {
	
	@Autowired
	SpendingTravelDao spendingTravelDao;
	

	
	

	@ApiOperation(value="나의 여행별 결제내역 전체 조회 pay_trvl_info")
	@GetMapping(value="/travel/list")
	public ResponseEntity <List<SpendingTravelVO>> selectSpendingTravelAll(
//			@ApiParam(value = "결제내역 조회할 여행 pk", required = true)@RequestParam("trvlId") int trvlId, 
			@RequestParam("trvlId") int trvlId, 
			@ApiParam(value = "지출내역 탭 : 결제유형(0.전체(혹은 값안넘겨도됨)/1.숙박/2.식비/3.교통/4.활동/5.기타)", required = false)@RequestParam(required = false) String searchPayType){
		
		List<SpendingTravelVO> list = null;
		try {
			log.info("나의 여행별 결제내역 전체 조회 :: trvlId => "+trvlId);
			log.info("나의 여행별 결제내역 전체 조회 :: searchPayType => "+searchPayType);
			
			if("0".equals(searchPayType)) {
				searchPayType = null;
			}
			
			list = spendingTravelDao.selectSpendingTravelAll(trvlId, searchPayType);
			
		} catch (Exception e) {
			
			log.error("Error",e);
			throw new RuntimeException(e);
		}
		
		return new ResponseEntity<List<SpendingTravelVO>> (list, HttpStatus.OK);
	}
	
	

	@ApiOperation(value="여행별 결제내역 중 한개 상세조회 trvl_pay_info")
	@GetMapping(value="/travel/{trvlPayId}")
	public ResponseEntity <SpendingTravelVO> selectSpendingTravel(@PathVariable int trvlPayId){
		SpendingTravelVO spend = null;
		try {
			log.info("여행별 결제내역 중 한개 상세조회 :: trvlPayId => "+trvlPayId);
			spend = spendingTravelDao.selectSpendingTravel(trvlPayId);
		}catch(Exception e) {
			log.error("ERROR",e);
			throw new RuntimeException(e);
		}
		
		return new ResponseEntity<SpendingTravelVO> (spend, HttpStatus.OK);
		
	}
	
	

	@ApiOperation(value="여행별 결제내역 등록 pay_trvl_info")
	@PostMapping(value="/travel")
	public ResponseEntity <String> insertSpendingTravel(@RequestBody SpendingTravelVO spending){
		int rc = 0;
		String msg = null;
		
		try {
			log.info("여행별 결제내역 등록 pay_trvl_info");
			spendingTravelDao.insertSpendingTravel(spending);
			
			int trvlId = spending.getTrvlId();
			spendingTravelDao.updateSpendingTravelRate(trvlId); //여행비 사용 금액 비율 갱신하기
			
		} catch (Exception e) {
			log.error("ERROR",e);
			throw new RuntimeException(e);
		}
		log.info("add payment rc:"+rc);
			
		if (rc>0) {
			msg = "등록 성공";
		}
		return new ResponseEntity<String> (msg, HttpStatus.OK);
	}
	
	@ApiOperation(value="여행별 결제내역 수정 pay_trvl_info")
	@PutMapping(value="/travel")
	public ResponseEntity <String> updateSpendingTravel(@RequestBody SpendingTravelVO spending){
		int rc = 0;
		String msg = null;
		
		try {
			log.info("여행별 결제내역 수정 pay_trvl_info");
			rc = spendingTravelDao.updateSpendingTravel(spending);
			
			int trvlId = spending.getTrvlId();
			spendingTravelDao.updateSpendingTravelRate(trvlId); //여행비 사용 금액 비율 갱신하기
		} catch (Exception e) {
			log.error("ERROR",e);
			throw new RuntimeException(e);
		}
		log.info("update spending rc:"+rc);
			
		if (rc>0) {
			msg = "등록 성공";
		}
		return new ResponseEntity<String> (msg, HttpStatus.OK);
	}
	
	@ApiOperation(value="여행별 결제내역 삭제 pay_trvl_info")
	@DeleteMapping(value="/travel/{trvlPayId}")
	public ResponseEntity <String> deleteSpendingTravel(@PathVariable int trvlPayId){
		int rc = 0;
		String msg = null;
		
		try {
			
			int trvlId = spendingTravelDao.getTrvlIdWithTrvlPayId(trvlPayId);
			log.info("여행별 결제내역 삭제 pay_trvl_info");
			rc = spendingTravelDao.deleteSpendingTravel(trvlPayId);
			
			spendingTravelDao.updateSpendingTravelRate(trvlId); //여행비 사용 금액 비율 갱신하기
		} catch (Exception e) {
			log.error("ERROR",e);
			throw new RuntimeException(e);
		}
		log.info("delete spending rc:"+rc);
			
		if (rc>0) {
			msg = "삭제 성공";
		}
		return new ResponseEntity<String> (msg, HttpStatus.OK);
	}
	
	

	
	
}
	
	
