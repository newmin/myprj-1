package com.kh.myprj.web.form;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class LoginMember {
	private Long id;				//서버내부적으로 관리하는 아이디
	private String email;		//회원 아이디
	private String nickname;
	private String role;
}
