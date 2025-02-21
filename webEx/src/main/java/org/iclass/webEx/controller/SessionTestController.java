package org.iclass.webEx.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.SessionAttribute;
import org.springframework.web.bind.annotation.SessionAttributes;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpSession;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@SessionAttributes(names= {"userid"})
public class SessionTestController {
	
	@GetMapping("setSession")
	public String setSession(HttpServletRequest request, Model model) {  // 지금 들어온 요청에 대한 세션을 전달해준다.
		HttpSession session = request.getSession();
		session.setAttribute("username", "gilDD");	// 현재 세션이 유지되는 동안 저장한 값
		model.addAttribute("username","아이브");	// session.html 에만 전달하도록 저장한 값
		return "session";	// session.html
	}
	
	@GetMapping("getSession")
	public String getSession(HttpServletRequest request) {
		HttpSession session = request.getSession();
		String username = (String)session.getAttribute("username");
		log.info("session username : {}", username);
		return "session";
	}
	
	@GetMapping("logout")		// 방법 1 : 로그인 (사용자인증할 때) 사용한다. 
	public String logout(HttpServletRequest request) {
		HttpSession session = request.getSession();
		session.invalidate();
		return "redirect:/";
	}
	
	@GetMapping("testSession")
	public String test(			// required(필수적인) = false 는 애트리뷰트 값이 없을 수도 있을 때.
			@SessionAttribute(name="userid",required=false) String userid) {  //@SessionAttribute 는 userid 값을 가져올 때 사용
		log.info("testsession userid : {}", userid);
		return "session";
	}
	

}
