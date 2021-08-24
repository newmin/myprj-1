package com.kh.myprj.domain.member.dao;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import com.kh.myprj.domain.member.dto.MemberDTO;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Repository
@RequiredArgsConstructor
public class MemberDAOImpl implements MemberDAO {
	
	private final JdbcTemplate jt;
	
	//가입
	@Override
	public long insert(MemberDTO memberDTO) {
		
		StringBuffer sql = new StringBuffer();
		sql.append("insert into member( ");
		sql.append("  id, ");
		sql.append("  email, ");
		sql.append("  pw, ");
		sql.append("  tel, ");
		sql.append("  nickname, ");
		sql.append("  gender, ");
		sql.append("  region, ");
		sql.append("  birth, ");
		sql.append("  letter) ");
		sql.append("values ( ");
		sql.append("  member_id_seq.nextval, ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?, ");
		sql.append("  ?) ");		
		
		KeyHolder keyHolder = new GeneratedKeyHolder();
		jt.update(new PreparedStatementCreator() {
			
			@Override
			public PreparedStatement createPreparedStatement(Connection con) throws SQLException {
				PreparedStatement pstmt = con.prepareStatement(sql.toString(), new String[] {"id"} );
				pstmt.setString(1, memberDTO.getEmail());
				pstmt.setString(2, memberDTO.getPw());
				pstmt.setString(3, memberDTO.getTel());
				pstmt.setString(4, memberDTO.getNickname());
				pstmt.setString(5, memberDTO.getGender());
				pstmt.setString(6, memberDTO.getRegion());
				pstmt.setDate(7,memberDTO.getBirth());
				pstmt.setString(8, memberDTO.getLetter());
				
				return pstmt;
			}
		},keyHolder);
		
		return  keyHolder.getKeyAs(BigDecimal.class).longValue();
	}
	
	//취미
	@Override
	public void addHobby(long id,List<String> hobbies) {
		StringBuffer sql = new StringBuffer();
		sql.append("insert into hobby (member_id, code_code) values ( ? , ? )");
		
		jt.batchUpdate(sql.toString(), new BatchPreparedStatementSetter() {
			
			@Override
			public void setValues(PreparedStatement ps, int i) throws SQLException {
				ps.setLong(1, id);
				ps.setString(2, hobbies.get(i));				
			}
			
			@Override
			public int getBatchSize() {
				return hobbies.size();
			}
		});
	}
	//취미 삭제
	@Override
	public void delHobby(long id) {
		String sql = "delete from hobby where id = ? ";
		jt.update(sql, id);
	}
	
	//회원조회 by id
	@Override
	public MemberDTO findByID(long id) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("select  ");
		sql.append("    id, ");
		sql.append("    email,  ");
		sql.append("    pw, ");
		sql.append("    tel,  ");
		sql.append("    nickname, ");
		sql.append("    gender, ");
		sql.append("    region, ");
		sql.append("    birth,  ");
		sql.append("    letter, ");
		sql.append("    fid,  ");
		sql.append("    cdate,  ");
		sql.append("    udate ");
		sql.append("  from member ");
		sql.append(" where id = ?  ");
		
		MemberDTO mdto = jt.queryForObject(
				sql.toString(), new BeanPropertyRowMapper<>(MemberDTO.class),id);
		return mdto;
	}

	//회원조회 by email
	@Override
	public MemberDTO findByEmail(String email) {
		StringBuffer sql = new StringBuffer();
		
		sql.append("select  ");
		sql.append("    id, ");
		sql.append("    email,  ");
		sql.append("    pw, ");
		sql.append("    tel,  ");
		sql.append("    nickname, ");
		sql.append("    gender, ");
		sql.append("    region, ");
		sql.append("    birth,  ");
		sql.append("    letter, ");
		sql.append("    fid,  ");
		sql.append("    cdate,  ");
		sql.append("    udate ");
		sql.append("  from member ");
		sql.append(" where email = ?  ");
		
		MemberDTO mdto = jt.queryForObject(
				sql.toString(), new BeanPropertyRowMapper<>(MemberDTO.class),email);
		return mdto;
	}

	@Override
	public List<MemberDTO> selectAll() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public void update(long id, MemberDTO memberDTO) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void delete(long id) {
		// TODO Auto-generated method stub
		
	}
}


