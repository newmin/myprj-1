package com.kh.myprj.domain.board.svc;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.kh.myprj.domain.board.dao.BoardDAO;
import com.kh.myprj.domain.board.dto.BoardDTO;
import com.kh.myprj.domain.common.dao.UpLoadFileDAO;
import com.kh.myprj.domain.common.dto.MetaOfUploadFile;
import com.kh.myprj.domain.common.dto.UpLoadFileDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@RequiredArgsConstructor
@Transactional
public class BoardSVCImpl implements BoardSVC {

	private final BoardDAO boardDAO;
	private final UpLoadFileDAO upLoadFileDAO;

	
	//원글작성
	@Override
	public Long write(BoardDTO boardDTO) {
		//게시글작성
		Long bnum = boardDAO.write(boardDTO);
		
		//첨부파일 메타정보 저장
		upLoadFileDAO.addFiles(
				convert(bnum, boardDTO.getBcategory(), boardDTO.getFiles())
		);
		return bnum;
	}

	private List<UpLoadFileDTO> convert(
			Long bnum,String bcategory,List<UpLoadFileDTO> files) {
		for(UpLoadFileDTO ele : files) {
			ele.setRid(String.valueOf(bnum));
			ele.setCode(bcategory);
		}
		return files;
	}

	//답글작성
	@Override
	public Long reply(BoardDTO boardDTO) {
		Long bnum = boardDAO.reply(boardDTO);
		return bnum;
	}

	//게시글 목록
	@Override
	public List<BoardDTO> list() {
		List<BoardDTO> list = boardDAO.list();
		return list;
	}

	//게시글 상세
	@Override
	public BoardDTO itemDetail(Long bnum) {
		BoardDTO boardDTO = boardDAO.itemDetail(bnum);
		
		boardDTO.setFiles(
				upLoadFileDAO.getFiles(
						String.valueOf(boardDTO.getBnum()), boardDTO.getBcategory()));
		return boardDTO;
	}

	//게시글 수정
	@Override
	public Long modifyItem(Long bnum, BoardDTO boardDTO) {
		Long modifiedBnum = boardDAO.modifyItem(bnum, boardDTO);
		return modifiedBnum;
	}

	//게시글 삭제
	@Override
	public void delItem(Long bnum) {
		boardDAO.delItem(bnum);
	}
}
