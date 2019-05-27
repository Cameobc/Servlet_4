package com.iu.board.comments;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.iu.page.SearchRow;

public class CommentsDAO {
	
	//insert
	public int insert(CommentsDTO commentsDTO, Connection con) throws Exception{
		String sql ="insert into comments values (qna_seq.nextval, ?, ?, ?, sysdate)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, commentsDTO.getNo());
		st.setString(2, commentsDTO.getWriter());
		st.setString(3, commentsDTO.getContents());
		int result = st.executeUpdate();
		st.close();
		return result;
	}
	
	//selectList
	public List<CommentsDTO> selectList(SearchRow searchRow, Connection con) throws Exception{
		List<CommentsDTO> ar = new ArrayList<CommentsDTO>();
		String sql ="select * from "
				+ " (select rownum r, c.* from "
				+ " (select * from comments order by cnum desc) c) "
				+ " where r between ? and ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, searchRow.getStartRow());
		st.setInt(2, searchRow.getLastRow());
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			CommentsDTO commentsDTO = new CommentsDTO();
			commentsDTO.setCnum(rs.getInt("cnum"));
			commentsDTO.setNo(rs.getInt("no"));
			commentsDTO.setWriter(rs.getString("writer"));
			commentsDTO.setContents(rs.getString("contents"));
			commentsDTO.setReg_date(rs.getString("reg_date"));
			ar.add(commentsDTO);
		}
		rs.close();
		st.close();
		return ar;
	}
	
	//delete
	public int delete(Connection con, int cnum) throws Exception {
		String sql = "delete comments where cnum=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, cnum);
		int result = st.executeUpdate();
		st.close();
		return result;
	}
	
	//update
	public int update(Connection con, CommentsDTO commentsDTO) throws Exception {
		String sql = "update comments set contents=? where cnum =?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, commentsDTO.getContents());
		st.setInt(2, commentsDTO.getCnum());
		int result = st.executeUpdate();
		st.close();
		return result;
	}
	
	

}
