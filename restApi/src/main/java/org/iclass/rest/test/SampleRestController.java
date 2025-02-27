package org.iclass.rest.test;

import java.util.ArrayList;
import java.util.List;

import org.iclass.rest.dto.SampleDTO;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import lombok.extern.slf4j.Slf4j;

@RestController
@Slf4j
public class SampleRestController {
	// db 역할을 하는 list (테스트용)
	private List<SampleDTO> list;
	
	// 생성자에서 db 역할 데이터 추가 (테스트용)
	public SampleRestController() {
		list = new ArrayList<>();
		list.add(new SampleDTO("twice","김땡땡","1234"));
		list.add(new SampleDTO("wonder","최원더","2345"));
		list.add(new SampleDTO("hongGD","홍길동","3456"));
		list.add(new SampleDTO("KGC","강감찬","4567"));
	}
	
// url 이름은 명사로 합니다. path 변수는 유일하게 구별할 수 있는 값(예 : PK)으로 한다.
// 검색을 구현할 때는 기존 방식과 같이 파라미터를 사용하기도 합니다. 
	
	@GetMapping("/samples")
	public ResponseEntity<List<SampleDTO>> list(String keyword){
								// URL 에 포함된 파라미터 keyword
		// db 구현 : keyword 값이 있을 때는 검색
		log.info("검색 키워드 : {}", keyword);
		return ResponseEntity.ok().body(list); 
	}
	
	// ResponseEntity : 응답으로 보내준다.
	@GetMapping("/samples/one")
	public ResponseEntity<SampleDTO> one(){
		return ResponseEntity.ok().body(list.get(2)); 
		// .ok() 상대코드 겸 메소드, body 데이터는 응답 본문 겸 자바 객체 입니다 -> 스트링부트가 json 문자열로 변환하여 전송
	}
	
	// 어노테이션 @PathVariable ⇒ URL 경로에서 값을 추출할 때 사용
	@GetMapping("/samples/{index}")		// {index} 기호는 변수로처리되는 경로 표시 
	public ResponseEntity<?> one(@PathVariable int index){		
		// 응답 본문의 타입이 미결정이면 ?
		try {
			return ResponseEntity.ok().body(list.get(index)); 	// 정상 처리 
			
		} catch (Exception e) {
			return  ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("서버 오류"); 	// index 값이 잘못되면 500 서버 오류 
		}
	}
	
	// 어노테이션 @RequestBody ⇒ 요청으로 전달된 json 문자열을 DTO 타입으로 변환. 위치는 dto 앞에 넣기.
	@PutMapping("/samples/{index}")
	public ResponseEntity<?> update(@PathVariable int index, @RequestBody SampleDTO dto){
		log.info("update 할 dto : {}, {}, {}", dto.getUserid(), dto.getUsername(), dto.getPassword());	
		// update 역할
		list.add(index,dto);	// 0번 인덱스 값을 수정
		return ResponseEntity.ok().body(1);
	}
	
	@DeleteMapping("/samples/{index}")
	public ResponseEntity<?> delete(@PathVariable int index){
		// delete 역할
		list.remove(index);
		return ResponseEntity.ok().body(1);
		
	}
	
	
	@PostMapping("/samples")
	public ResponseEntity<?> post(@RequestBody SampleDTO dto){
		log.info("insert 할 dto : {}, {}, {}", dto.getUserid(), dto.getUsername(), dto.getPassword());
		try {
			// insert 역할
			list.add(dto);
			log.info("list 결과 : {}", list);
			return ResponseEntity.ok().body(1);		// 정상 처리 되면 응답 코드 200
			
		} catch (Exception e) {
			return ResponseEntity.badRequest().build();		// 처리 중 오류 발생하면 응답 코드 400 , 500
			
		}
		
	}
	
}
