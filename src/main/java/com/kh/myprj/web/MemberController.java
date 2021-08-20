package com.kh.myprj.web;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.kh.myprj.domain.member.svc.MemberSVC;
import com.kh.myprj.web.form.member.JoinForm;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@AllArgsConstructor
@RequestMapping("/members")
public class MemberController {

	private final MemberSVC memberSVC;
	
	/**
	 * 회원가입양식
	 * @return
	 */
	@GetMapping("/join")
	public String joinForm(Model model) {
		log.info("회원가입양식 호출됨!");
		model.addAttribute("joinForm", new JoinForm());
		return "members/joinForm";
	}
	/**
	 * 회원가입처리
	 * @return
	 */
	@PostMapping("/join")
	public String join() {
		log.info("회원가입처리 호출됨!");
		return "home";
	}
	/**
	 * 회원수정양식
	 * @return
	 */
	@GetMapping("/{id:.+}/edit")
	public String editForm(@PathVariable("id") String id) {
		log.info("회원수정양식 호출됨!");
		log.info("회원:{}",id);
		return "members/editForm";
	}
	/**
	 * 회원수정처리
	 * @return
	 */
	@PatchMapping("/{id:.+}/edit")
	public String edit(@PathVariable("id") String id) {
		log.info("회원수정처리 호출됨");
		log.info("회원:{}",id);
		return "home";
	}
	/**
	 * 회원조회
	 * @return
	 */
	@GetMapping("/{id:.+}")
	public String view(@PathVariable("id") String id) {
		log.info("회원조회 호출됨");
		log.info("회원:{}",id);
		return "members/view";
	}
	/**
	 * 회원탈퇴
	 * @return
	 */
	@DeleteMapping("/{id:.+}")
	public String out(@PathVariable("id") String id) {
		log.info("회원탈퇴");
		log.info("회원:{}",id);
		return "home";
	}
	/**
	 * 회원목록
	 * @return
	 */
	@GetMapping("")
	public String listAll() {
		log.info("회원목록");
		return "members/list";
	}
}
