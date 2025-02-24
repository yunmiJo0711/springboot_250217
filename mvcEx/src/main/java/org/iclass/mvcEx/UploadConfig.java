package org.iclass.mvcEx;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class UploadConfig implements WebMvcConfigurer{
	
	@Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        // c:/upload 파일시스템 경로를 url 로 매핑하기 위함.
        // 개발자가 url을 정해줍니다.
        final String URL_PATH = "/upload/**";
        // 파일시스템 위치 
        final String LOCATION ="file:///C:/upload/";

        //위의 2개 값을 매핑
        registry.addResourceHandler(URL_PATH)
                .addResourceLocations(LOCATION);

    }

}
// 코드 분석:
// @Configuration: 이 클래스가 Spring의 설정 클래스임을 나타냅니다. Spring Boot에서 이 클래스를 통해 설정을 관리합니다.
// WebMvcConfigurer 인터페이스를 구현하여 Spring MVC의 리소스 핸들러를 설정합니다.
// addResourceHandlers() 메소드에서 URL 경로(/upload/**)와 파일 시스템 경로(file:///C:/upload/)를 매핑합니다.