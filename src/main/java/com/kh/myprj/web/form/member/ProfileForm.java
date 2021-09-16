package com.kh.myprj.web.form.member;

import org.springframework.web.multipart.MultipartFile;

import com.kh.myprj.domain.common.dto.UpLoadFileDTO;

import lombok.Data;

@Data
public class ProfileForm {
	private String nickname;								//별칭
	private UpLoadFileDTO savedImgFile; 		//기존파일
}


