package com.kh.myprj.web.form;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class LoginMember {
	private String id;
	private String nickname;
	private boolean admin;
}
