package com.kh.myprj.web;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.kh.myprj.domain.member.svc.MemberSVC;
import com.kh.myprj.web.api.FindEmailReq;
import com.kh.myprj.web.api.FindPwReq;
import com.kh.myprj.web.api.JsonResult;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController  //@Controller + @ResponseBody
@RequestMapping("/api/members")
@AllArgsConstructor
public class APIMemberController {

	private final MemberSVC memberSVC;
	
	//이메일 찾기
	@PostMapping("/email")
	public JsonResult<String> findEamil(
			@RequestBody FindEmailReq findEmailReq,
			BindingResult bindingResult) {

		log.info("findEmailReq:{}",findEmailReq);
		if(bindingResult.hasErrors()) {
			return null;
		}
		
		String findedEmail = 
				memberSVC.findEmail(findEmailReq.getTel(),findEmailReq.getBirth());
		
		return new JsonResult<String>("00","ok",findedEmail);
	}
	
	//비밀번호 찾기
	@PostMapping("/pw")
	public Object findPw(
			@RequestBody FindPwReq findPwReq,
			BindingResult bindingResult) {
		
		log.info("findPwReq:{}",findPwReq);
		if(bindingResult.hasErrors()) {
			return null;
		}
		
		String findedPw = 
				memberSVC.findPw(findPwReq.getEmail(),findPwReq.getTel(),findPwReq.getBirth());
		return new JsonResult<String>("00","ok",findedPw);
	}
}







