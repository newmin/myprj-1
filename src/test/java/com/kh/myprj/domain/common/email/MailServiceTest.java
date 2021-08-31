package com.kh.myprj.domain.common.email;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.io.FileSystemResource;

import com.kh.myprj.domain.common.mail.MailService;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@SpringBootTest
public class MailServiceTest {

	@Autowired
	private MailService ms;
	
	@Test
	@DisplayName("메일전송")
	void sendMail() {
	
		ms.sendMail("sang105kr2@naver.com", "메일제목", "메일본문");
		
	}
	@Test
	@DisplayName("메일전송 with 첨부")
	void sendMailWithAttatch() {
		FileSystemResource res = new FileSystemResource(new File("d:/hello.html"));
	
		List<File> files = new ArrayList<>();
		files.add(res.getFile());
		ms.sendMailWithAttatch("sang105kr2@naver.com", "메일제목:첨부", "메일본문",files);
		
	}
	@Test
	@DisplayName("메일전송 with 인라인")
	void sendMailWithInline() {
		FileSystemResource res = new FileSystemResource(new File("d:/hello.html"));
		FileSystemResource res2 = new FileSystemResource(new File("d:/fig.jpg"));
		List<File> files = new ArrayList<>();
		files.add(res.getFile());
		files.add(res2.getFile());
		ms.sendMailWithInline("sang105kr2@naver.com", "메일제목:인라인", "메일본문",files);
		
	}
	
}
