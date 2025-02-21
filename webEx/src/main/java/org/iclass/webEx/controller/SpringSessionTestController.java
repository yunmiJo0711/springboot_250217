package org.iclass.webEx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;
import org.springframework.web.bind.support.SessionStatus;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@SessionAttributes(names= {"userid"})	// @SessionAttributes 애트리뷰트 이름 userid 는 세션 scope 으로 저장
public class SpringSessionTestController {
	
	@GetMapping("setSessionAttribute")
	public String setSession(Model model) {  
		model.addAttribute("userid","에스파");	
		return "session";	
	}
	
	@GetMapping("getSessionAttribute")
	public String getSession(			// required(필수적인) = false 는 애트리뷰트 값이 없을 수도 있을 때.
			@SessionAttribute(name="userid",required=false) String userid) {  //@SessionAttribute 는 값을 가져올 때 사용
		log.info("session userid : {}", userid);
		return "session";
	}
	
	@PostMapping("logout")		// 방법 2 : 여러 단계로 form 데이터 입력 받을 때 사용. 예시: (카드발급 받을 때)인증하고 넘어가고 또 인증하고 이런 데이터가 쌓이는 것
	public String logout(SessionStatus sessionStatus) {
		// model 애트리뷰트 중에서 session  스콥에 저장된 것들을 모두 삭제
		sessionStatus.setComplete();
	
		return "redirect:/";
	}
}
