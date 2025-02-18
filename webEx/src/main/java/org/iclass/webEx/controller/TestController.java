package org.iclass.webEx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


// web mvc 에서는 controller 가 url 요청을 받아서 
// 해당되는 view 를 지정합니다. Controller 와 View 의 매핑.
@Slf4j
@Controller
public class TestController {
	// Get 요청 처리. URL(servlet path) 은 join
	@GetMapping("join")
	public String join() {
		return "join";	// join.html 파일 지정 (View)
	}
	
	@GetMapping("login")
	public String login() {
		return "login";	// login.html 파일 지정
	}
	
	@GetMapping("write")
	public String write() {
		return "write";	// write.html 파일 지정
	}
	
	@PostMapping("save")
	public String save(String title, String content) {
		log.info("post 요청을 처리하고 context path(/) 로 리다이렉트합니다."); // 리다이렉트 : 홈으로 돌아간다
		log.info("title : {}", title);  // requst.get파라미터 
		log.info("content : {}", content);
		return "redirect:login";	// -> 이러면 LOGIN 화면으로 감. 형식은 "redirect:URL"
	}

}
