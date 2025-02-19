package Dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@NoArgsConstructor
@Getter
@Setter
@ToString
public class TestDto {
	private String name;	// 객체 기본값 String 은 null
	private int age;		// double, int 수치형식은 0
	private String address;
	private String gender;

}
