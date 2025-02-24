package org.iclass.mvcEx.controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

import org.iclass.mvcEx.dto.Gallery;
import org.iclass.mvcEx.service.GalleryUploadService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;

import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
@AllArgsConstructor
@Controller
@Slf4j
public class GalleryUploadController {
	private GalleryUploadService service;

	// 레이아웃 샘플페이지. 다운로드 테스트
	@GetMapping("/sample")
	public String sample(Model model) {
		List<String> filenames = List.of("가나다.pdf","라마바.pdf","ABCD.pdf");
		model.addAttribute("filenames", filenames);
		return "sample";
	}
	
	@GetMapping("/download")
	public void download(String filename, HttpServletResponse response) {
		String FILE_DIRECTORY = "C:/upload/";
		File file = new File(FILE_DIRECTORY + filename);
		
		response.setContentType("application/octet-stream");
		String encodedFilename = URLEncoder.encode(filename, StandardCharsets.UTF_8);
		
		response.setHeader("Content-Disposition", "attachment; filename=\"" 
												+ encodedFilename + "\"");
		// 											ㄴ 응답 헤더 이름 Content-Disposition 에 인코딩된 파일명을 전달함.
		response.setContentLengthLong(file.length());
		
		try (FileInputStream fis = new FileInputStream(file);	// 서버의 파일이 입력 스트립
			OutputStream os = response.getOutputStream()) {		// 사용자 응답이 출력 스트림 
			
			byte[] buffer = new byte[1024];
			int bytesRead;
			while((bytesRead = fis.read(buffer)) != -1) {		// 입력 스트림으로부터 데이터 읽기
				os.write(buffer, 0, bytesRead);		// 출력 스트림에 데이터 쓰기 
			}
			os.flush();				// 출력 스트림 캐시 비우기 
			
		} catch (IOException e) {
			response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
		}
		
		
	}
	
	// 테이블을 만들지 않고 컬럼을 나열해서 여러개의 사진을 나열하자.
	// Model 은 서버 측에서 데이터를 뷰로 전달하는 중요한 역할을 한다. 
	// 컨트롤러에서 데이터를 Model에 담아서 반환하면, 그 데이터가 뷰 템플릿에서 사용된다.
	// 데이터를 Model.addAttribute("key", value) 형태로 전달하고, 템플릿에서 th:text="${key}"로 출력합니다.
	@GetMapping("galleries")
	public String galleries(Model model) {
		List<Gallery> list = new ArrayList<>();
		list.add(new Gallery("침실","iclass","침실1.jpg,침실2.jpg,침실3.jpg"));
		list.add(new Gallery("거실","iclass","거실1.jpg,거실2.jpg"));
		list.add(new Gallery("키친","iclass","키친1.jpg,키친2.jpg,키친3.jpg"));
		model.addAttribute("list",list);
		return "galleries";	// galleries.html
	}
	
	// 여러개 업로드 uploadMany
	@PostMapping("galleries")
	public String galleries(Gallery dto) {
		service.uploadMany(dto);
		return "redirect:galleries";
	}
	
	@GetMapping("/gallery")
	public String gallery(Model model) {
		//db를 사용하면 select 결과로 구현함.
		Gallery dto = Gallery.builder()
				.title("테스트1")
				.writer("iclass")
				.fileNames("bird.jpg")
				.build();
		model.addAttribute("dto", dto);
		return "gallery";
	}
	
	// 하나만 업로드 uploadOne
	@PostMapping("/gallery")
	public String gallery(Gallery dto) {
	// 	service.uploadOne(dto);
		service.uploadMany(dto);  // 1개 이상의 파일을 업로드 
		return "redirect:gallery";
	}

}
