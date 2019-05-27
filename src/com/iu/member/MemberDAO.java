package com.iu.member;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import com.iu.util.DBConnector;

public class MemberDAO {
	
	public int idCheck(String id, Connection con) throws Exception {
		String sql = "select id from member where id=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, id);
		ResultSet rs = st.executeQuery();
		int check  = 1;
		if(rs.next()) {
			check = 0;
		}
		rs.close();
		st.close();
		return check;
	}
	
	public MemberDTO login(MemberDTO memberDTO) throws Exception {
		Connection con= DBConnector.getConnect();
		MemberDTO m = null;
		String sql ="select * from member where id=? and pw=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, memberDTO.getId());
		st.setString(2,  memberDTO.getPw());
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			m = new MemberDTO();
			m.setId(rs.getString("id"));
		}
		DBConnector.disConnect(con, st, rs);
		return m;
	}
	


}
