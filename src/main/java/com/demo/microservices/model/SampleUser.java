package com.demo.microservices.model;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class SampleUser {
 private String userId ; // 사용자 아이디
 private String userNm;
 private String addr;
 private String cellPhone;
 private String agreeInform;
 private String birthDt;
}
