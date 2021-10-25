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

import com.demo.microservices.dao.SpendingCardDao;
import com.demo.microservices.model.SpendingCardVO;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/spending")
public class SpendingCardController {
	
	@Autowired
	SpendingCardDao spendingCardDao;
	
	
	@ApiOperation(value="나의 카드내역 전체 조회 card_info")
	@GetMapping(value="/card/list")
	public ResponseEntity <List<SpendingCardVO>> selectSpendingCardAll(@RequestParam("userId") int userId){

		List<SpendingCardVO> list = null;
		try {
			log.info("나의 카드내역 전체 조회 :: userId => "+userId);
			list = spendingCardDao.selectSpendingCardAll(userId);
			
		} catch (Exception e) {
			
			log.error("Error",e);
			throw new RuntimeException(e);
		}
		
		return new ResponseEntity<List<SpendingCardVO>> (list, HttpStatus.OK);
	}
	
	
	
	
	@ApiOperation(value="나의 카드내역 중 한개 상세조회 card_info")
	@GetMapping(value="/card/{cardId}")
	public ResponseEntity <SpendingCardVO> selectSpendingCard(@PathVariable int cardId){
		SpendingCardVO spend = null;
		try {
			log.info("나의 카드내역 중 한개 상세조회 :: cardId => "+cardId);
			spend = spendingCardDao.selectSpendingCard(cardId);
		}catch(Exception e) {
			log.error("ERROR",e);
			throw new RuntimeException(e);
		}
		
		return new ResponseEntity<SpendingCardVO> (spend, HttpStatus.OK);

	}
	
	
	
	@ApiOperation(value="카드내역 등록 card_info")
	@PostMapping(value="/card")
	public ResponseEntity <String> insertSpendingCard(@RequestBody SpendingCardVO spending){
		int rc = 0;
		String msg = null;
		
		try {
			log.info("카드내역 등록 card_info");
			spendingCardDao.insertSpendingCard(spending);
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
	
	@ApiOperation(value="카드내역 수정 card_info")
	@PutMapping(value="/card")
	public ResponseEntity <String> updateSpendingCard(@RequestBody SpendingCardVO spending){
		int rc = 0;
		String msg = null;
		
		try {
			log.info("카드내역 수정 card_info");
			rc = spendingCardDao.updateSpendingCard(spending);
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
	
	@ApiOperation(value="카드내역 삭제 card_info")
	@DeleteMapping(value="/card/{cardId}")
	public ResponseEntity <String> deleteSpendingCard(@PathVariable int cardId){
		int rc = 0;
		String msg = null;
		
		try {
			log.info("카드내역 삭제 card_info");
			rc = spendingCardDao.deleteSpendingCard(cardId);
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
	
	
