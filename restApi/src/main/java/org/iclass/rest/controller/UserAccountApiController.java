package org.iclass.rest.controller;

import org.iclass.rest.dto.UserAccount;
import org.iclass.rest.service.UserAccountService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@RestController
@Slf4j
public class UserAccountApiController {
	private final UserAccountService service;
	
	// 사용자 계정 정보 PK 로 가져오기 
	@GetMapping("/account/{userid}")
	public ResponseEntity<UserAccount> getOne(@PathVariable String userid){
		UserAccount account = service.userInfo(userid);
		return ResponseEntity.ok().body(account);  
		// body(account) 가 보내고 싶은 본문이다. account 를 json 문자열로 변환하여 전송
	}
	
	// 사용자 계정 등록하기 - insert
	@PostMapping("/account")	// 무결성 위반이면 badRequest 응답 보내기
	public ResponseEntity<?> post(@RequestBody UserAccount dto){
		try {
			int result = service.regist(dto);
			return ResponseEntity.ok().body(result);
			
		} catch (Exception e) {
			log.info("insert sql 예외 : {}", e.getMessage());
			return ResponseEntity.badRequest().build();
		}
	}
	
	 // 사용자 계정 이메일 수정하기 - update
	 @PatchMapping("/account") 
	 public ResponseEntity<?> update(@RequestBody UserAccount dto){ 
		int result = service.updateEmail(dto);
		return ResponseEntity.ok().body(result); 
	}
	 
	// 사용자 계정 삭제 - delete
	 @DeleteMapping("/account/{userid}") 
	 public ResponseEntity<?> delete(@PathVariable String userid){
		 int result = service.deactive(userid);
		 return ResponseEntity.ok().body(result); 
	}
	 
}
