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

import com.demo.microservices.dao.SpendingAccountDao;
import com.demo.microservices.model.SpendingAccountVO;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/")
public class SpendingAccountController {
	
	@Autowired
	SpendingAccountDao spendingAccountDao;
	
	
	@ApiOperation(value="나의 계좌내역 전체 조회 acnt_info")
	@GetMapping(value="/account/list")
//	public ResponseEntity <List<SpendingVO>> selectSpendingAccountAll(@PathVariable int userId){
	public ResponseEntity <List<SpendingAccountVO>> selectSpendingAccountAll(@RequestParam("userId") int userId){

		List<SpendingAccountVO> list = null;
		try {
			log.info("나의 계좌내역 전체 조회 :: userId => "+userId);
			list = spendingAccountDao.selectSpendingAccountAll(userId);
			
		} catch (Exception e) {
			
			log.error("Error",e);
			throw new RuntimeException(e);
		}
		
		return new ResponseEntity<List<SpendingAccountVO>> (list, HttpStatus.OK);
	}
	
	
	@ApiOperation(value="나의 전체 예산 불러오기")
	@GetMapping(value="/account/budget")
	public ResponseEntity <Integer> selectSpendingAccountBudget(@RequestParam("userId") int userId){
		
		int totalBudget = 0;
		
		try {
			log.info("나의 전체 예산 불러오기 :: userId => "+userId);
			totalBudget = spendingAccountDao.selectSpendingAccountBudget(userId);
			
		} catch (Exception e) {
			
			log.error("Error",e);
			throw new RuntimeException(e);
		}
		
		return new ResponseEntity<Integer> (totalBudget, HttpStatus.OK);
	}
	
	
	
	
	@ApiOperation(value="나의 계좌내역 중 한개 상세조회 acnt_info")
	@GetMapping(value="/account/{accountId}")
	public ResponseEntity <SpendingAccountVO> selectSpendingAccount(@PathVariable int accountId){
		SpendingAccountVO spend = null;
		try {
			log.info("나의 계좌내역 중 한개 상세조회 :: accountId => "+accountId);
			spend = spendingAccountDao.selectSpendingAccount(accountId);
		}catch(Exception e) {
			log.error("ERROR",e);
			throw new RuntimeException(e);
		}
		
		return new ResponseEntity<SpendingAccountVO> (spend, HttpStatus.OK);

	}
	
	
	
	@ApiOperation(value="계좌내역 등록 acnt_info")
	@PostMapping(value="/account")
	public ResponseEntity <String> insertSpendingAccount(@RequestBody SpendingAccountVO spending){
		int rc = 0;
		String msg = null;
		
		try {
			log.info("계좌내역 등록 acnt_info");
			spendingAccountDao.insertSpendingAccount(spending);
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
	
	@ApiOperation(value="계좌내역 수정 acnt_info")
	@PutMapping(value="/account")
	public ResponseEntity <String> updateSpendingAccount(@RequestBody SpendingAccountVO spending){
		int rc = 0;
		String msg = null;
		
		try {
			log.info("계좌내역 수정 acnt_info");
			rc = spendingAccountDao.updateSpendingAccount(spending);
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
	
	@ApiOperation(value="계좌내역 삭제 acnt_info")
	@DeleteMapping(value="/account/{accountId}")
	public ResponseEntity <String> deleteSpendingAccount(@PathVariable int accountId){
		int rc = 0;
		String msg = null;
		
		try {
			log.info("계좌내역 삭제 acnt_info");
			rc = spendingAccountDao.deleteSpendingAccount(accountId);
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
	
	
