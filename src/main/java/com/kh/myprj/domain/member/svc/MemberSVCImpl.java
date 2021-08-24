package com.kh.myprj.domain.member.svc;

import java.util.List;

import org.springframework.stereotype.Service;

import com.kh.myprj.domain.member.dao.MemberDAO;
import com.kh.myprj.domain.member.dto.MemberDTO;

import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@AllArgsConstructor
public class MemberSVCImpl implements MemberSVC{

	private final MemberDAO memberDAO;
	
	@Override
	public void join(MemberDTO memberDTO) {
		long id = memberDAO.insert(memberDTO);

		List<String> hobby = memberDTO.getHobby();
		if( hobby != null && hobby.size() > 0) {
			memberDAO.addHobby(id,memberDTO.getHobby());
		}		
	}

	@Override
	public void update(long id, MemberDTO memberDTO) {
		memberDAO.update(id, memberDTO);
	}

}
