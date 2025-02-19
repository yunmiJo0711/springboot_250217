package org.iclass.webEx.controller;

import java.lang.ProcessHandle.Info;
import java.util.ArrayList;
import java.util.List;

import org.iclass.webEx.dto.TestDto;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class AttributeTestController {
	//화면(View) 에 전달할 데이터를 저장하는 방법
	//   View 는 서버에서 처리하는 템플릿엔진(타임리프) html 파일이어야 함
	
	
	//★요청 URL 에 경로(path) 를 추가 : 파일관리, URL 관리를 위함
	@GetMapping("/community/list")
	public String list(Model model) {
		List<String> list = new ArrayList<>();
		list.add("아이브");
		list.add("빅뱅");
		list.add("BTS");
		list.add("에스파");
		
		//View community/list.html 에 전달되는 데이터
		model.addAttribute("page", 3);
		model.addAttribute("testDto", new TestDto());
		model.addAttribute("list", list);
		return "community/list";  //★추가된 경로 이름으로 폴더만들어서 지정
	}
	
	@GetMapping("/community/read")
	public String read(Model model) {
		TestDto dto = new TestDto("홍길순",27,"인천","여자");
		
		model.addAttribute("dto",dto);
		return "community/read";
	}
	
	@GetMapping("exercise")
	public String exercise(Model model) {
		List<TestDto> list = new ArrayList<>();
		list.add(new TestDto("홍길동",22,"서울","남자"));
		list.add(new TestDto("홍길순",20,"인천","여자"));
		list.add(new TestDto("김다현",27,"서울","여자"));
		list.add(new TestDto("이나연",25,"부산","여자"));
		
		model.addAttribute("list",list);
		return "exercise";
	}
	// http://localhost:8085/board/write?code=XYZ
	// 				ㄴ 예시 : 파라미터로 받은 code 값을 view 한테 전달해준다. 
	@GetMapping("/board/write")
	public String write(String code, Model model) {
		log.info("GET 요청 write 파라미터 : {}", code);
		model.addAttribute("code", code);
		return "board/write";		// board/write.html 을 view 로 설정 
	}
	
	@PostMapping("/board/write")
	public String save(String title, String content) {
		log.info("POST 요청 파라미터 : {}, {}", title, content);
		return "redirect:/";
	}

}