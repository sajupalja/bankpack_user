package com.demo.microservices.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.demo.microservices.model.UserVO;

@Mapper
public interface UserDao {
	
	List<UserVO> selectUserAll() throws Exception; // 전체 유저 
	
	UserVO selectUser(int userId); // id로 정보 불러오기 

	int insertUser(UserVO user); // user 추가

	int updateUser(UserVO user); // user 수정

	int deleteUser(int userId); // user 삭제
	
	UserVO login(UserVO user); // 사용자 로그인
}


