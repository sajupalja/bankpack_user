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

import com.demo.microservices.dao.SpendingPaymentDao;
import com.demo.microservices.model.SpendingPaymentVO;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;


@Slf4j
@RestController
@RequestMapping("/spending")
public class SpendingPaymentController {
	
	@Autowired
	SpendingPaymentDao spendingPaymentDao;
	

	
	@ApiOperation(value="나의 결제내역 전체 조회 pay_info")
	@GetMapping(value="/payment/list")
//	public ResponseEntity <List<SpendingVO>> selectSpendingPaymentAll(@PathVariable int userId){
	public ResponseEntity <List<SpendingPaymentVO>> selectSpendingPaymentAll(@RequestParam("userId") int userId){

		List<SpendingPaymentVO> list = null;
		try {
			log.info("나의 결제내역 전체 조회 :: userId => "+userId);
			list = spendingPaymentDao.selectSpendingPaymentAll(userId);
			
		} catch (Exception e) {
			
			log.error("Error",e);
			throw new RuntimeException(e);
		}
		
		return new ResponseEntity<List<SpendingPaymentVO>> (list, HttpStatus.OK);
	}
	
	
	
	
	@ApiOperation(value="나의 결제내역 중 한개 상세조회 pay_info")
	@GetMapping(value="/payment/{payId}")
	public ResponseEntity <SpendingPaymentVO> selectSpendingPayment(@PathVariable int payId){
		SpendingPaymentVO spend = null;
		try {
			log.info("나의 결제내역 중 한개 상세조회 :: payId => "+payId);
			spend = spendingPaymentDao.selectSpendingPayment(payId);
		}catch(Exception e) {
			log.error("ERROR",e);
			throw new RuntimeException(e);
		}
		
		return new ResponseEntity<SpendingPaymentVO> (spend, HttpStatus.OK);

	}
	
	
	
	@ApiOperation(value="결제내역 등록 pay_info")
	@PostMapping(value="/payment")
	public ResponseEntity <String> insertSpendingPayment(@RequestBody SpendingPaymentVO spending){
		int rc = 0;
		String msg = null;
		
		try {
			log.info("결제내역 등록 pay_info");
			spendingPaymentDao.insertSpendingPayment(spending);
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
	
	@ApiOperation(value="결제내역 수정 pay_info")
	@PutMapping(value="/payment")
	public ResponseEntity <String> updateSpendingPayment(@RequestBody SpendingPaymentVO spending){
		int rc = 0;
		String msg = null;
		
		try {
			log.info("결제내역 수정 pay_info");
			rc = spendingPaymentDao.updateSpendingPayment(spending);
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
	
	@ApiOperation(value="결제내역 삭제 pay_info")
	@DeleteMapping(value="/payment/{payId}")
	public ResponseEntity <String> deleteSpendingPayment(@PathVariable int payId){
		int rc = 0;
		String msg = null;
		
		try {
			log.info("결제내역 삭제 pay_info");
			rc = spendingPaymentDao.deleteSpendingPayment(payId);
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
	
	
