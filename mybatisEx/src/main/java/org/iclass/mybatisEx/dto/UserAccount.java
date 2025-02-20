package org.iclass.mybatisEx.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Builder
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UserAccount {
	private String userid;
	private String username;
	private String password;
	private String birth;
	private String gender;
	private String email;

}
