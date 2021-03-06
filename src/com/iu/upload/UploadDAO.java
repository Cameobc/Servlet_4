package com.iu.upload;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.iu.util.DBConnector;

public class UploadDAO {
	
	//select
	public UploadDTO selectOne(int num) throws Exception{
		UploadDTO uploadDTO = null;
		Connection con = DBConnector.getConnect();
		String sql = "select * from upload where no=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		ResultSet rs = st.executeQuery();
		if(rs.next()) {
			uploadDTO = new UploadDTO();
			uploadDTO.setPnum(rs.getInt("pnum"));
			uploadDTO.setNo(rs.getInt("no"));
			uploadDTO.setoName(rs.getString("oname"));
			uploadDTO.setfName(rs.getString("fname"));
		}
		rs.close();
		st.close();
		return uploadDTO;
	}
	
	//selectList
	public List<UploadDTO> selectList(int num, Connection con) throws Exception{
		ArrayList<UploadDTO> ar = new ArrayList<UploadDTO>();
		String sql ="select * from upload where no=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, num);
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			UploadDTO uploadDTO = new UploadDTO();
			uploadDTO.setPnum(rs.getInt("pnum"));
			uploadDTO.setNo(rs.getInt("no"));
			uploadDTO.setoName(rs.getString("oname"));
			uploadDTO.setfName(rs.getString("fname"));
			ar.add(uploadDTO);
		}
		rs.close();
		st.close();
		return ar;
	}
	
	//update
	
	//delete
	public int delete(int pnum, Connection con) throws Exception {
		int result=0;
		String sql ="delete upload where pnum=?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, pnum);
		result = st.executeUpdate();
		st.close();
		return result;
	}
	
	//insert
	public int insert(UploadDTO uploadDTO, Connection con) throws Exception{
		int result=0;
		String sql = "insert into upload values(num_seq.nextval, ?, ?, ?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, uploadDTO.getNo());
		st.setString(2, uploadDTO.getoName());
		st.setString(3, uploadDTO.getfName());
		result = st.executeUpdate();
		st.close();
		return result;
	}
	
}
