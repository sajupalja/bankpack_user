package com.demo.microservices.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.demo.microservices.dao.UserDao;
import com.demo.microservices.model.UserVO;

import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@CrossOrigin(origins = "*")
public class UserController {
	
	@Autowired
	UserDao userDao;
	
	@ApiOperation(value="사용자 목록 정보 가져오기")
	@GetMapping(value="/users")
	public ResponseEntity <List<UserVO>> getUserAll(){
		
		List<UserVO> list = null;
		try {
			log.info("Start db select");
			list = userDao.selectUserAll();
		} catch (Exception e) {
			log.error("Error",e);
			throw new RuntimeException(e);
		}
		log.info("user counts : " + list.size());
		
		return new ResponseEntity<List<UserVO>> (list, HttpStatus.OK);
	}
	
	@ApiOperation(value="사용자 정보 가져오기")
	@GetMapping(value="/users/{userId}")
	public ResponseEntity <UserVO> getUsrId(@PathVariable int userId){
		UserVO user = null;
		try {
			log.info("Start db select");
			user = userDao.selectUser(userId);
		}catch(Exception e) {
			log.error("ERROR",e);
			throw new RuntimeException(e);
		}
		
		log.info("user counts : "+user);
		return new ResponseEntity<UserVO> (user, HttpStatus.OK);

	}
	
	@ApiOperation(value="사용자 등록")
	@PostMapping(value="/users")
	public ResponseEntity <String> insertUser(@RequestBody UserVO user){
		int rc = 0;
		String msg = null;
		
		try {
			log.info("Start insert DB");
			rc = userDao.insertUser(user);
		} catch (Exception e) {
			log.error("ERROR",e);
			throw new RuntimeException(e);
		}
		log.info("add user rc:"+rc);
			
		if (rc>0) {
			msg = "등록 성공";
		}
		return new ResponseEntity<String> (msg, HttpStatus.OK);
	}
	
	@ApiOperation(value="사용자 수정")
	@PutMapping(value="/users/{userId}")
	public ResponseEntity <String> updateUser(@PathVariable int userId, @RequestBody UserVO user){
		int rc = 0;
		String msg = null;
		
		try {
			log.info("Start update DB");
			rc = userDao.updateUser(user);
		} catch (Exception e) {
			log.error("ERROR",e);
			throw new RuntimeException(e);
		}
		log.info("update user rc:"+rc);
			
		if (rc>0) {
			msg = "등록 성공";
		}
		return new ResponseEntity<String> (msg, HttpStatus.OK);
	}
	
	@ApiOperation(value="사용자 삭제")
	@DeleteMapping(value="/users/{userID}")
	public ResponseEntity <String> deleteUser(@PathVariable int userId){
		int rc = 0;
		String msg = null;
		
		try {
			log.info("Start update DB");
			rc = userDao.deleteUser(userId);
		} catch (Exception e) {
			log.error("ERROR",e);
			throw new RuntimeException(e);
		}
		log.info("delete user rc:"+rc);
			
		if (rc>0) {
			msg = "삭제 성공";
		}
		return new ResponseEntity<String> (msg, HttpStatus.OK);
	}
}
	
	
