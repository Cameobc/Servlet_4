package com.iu.board.notice;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import com.iu.board.BoardDAO;
import com.iu.board.BoardDTO;
import com.iu.page.SearchRow;
import com.iu.util.DBConnector;

public class NoticeDAO implements BoardDAO {

	@Override
	public int getNum() throws Exception {
		int result =0;
		Connection con = DBConnector.getConnect();
		String sql ="select notice_seq.nextval from dual";
		PreparedStatement st = con.prepareStatement(sql);
		ResultSet rs= st.executeQuery();
		rs.next();
		result = rs.getInt(1);
		DBConnector.disConnect(con, st, rs);
		return result;
	}

	@Override
	public int getTotalCount(SearchRow searchRow, Connection con) throws Exception {
		String sql="select count(no) from notice where "+searchRow.getSearch().getKind()+" like ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+searchRow.getSearch().getSearch()+"%");
		ResultSet rs = st.executeQuery();
		rs.next();
		int result = Integer.parseInt(rs.getString(1));
		DBConnector.disConnect(con, st, rs);
		return result;
	}

	@Override
	public List<BoardDTO> selectList(SearchRow searchRow, Connection con) throws Exception {
		ArrayList<BoardDTO> ar = new ArrayList<BoardDTO>();
		String sql = " select * from "
				+ "(select rownum r, n.* from "
				+ "(select no, title, writer, reg_date, hit from notice where "+ searchRow.getSearch().getKind() +" like ? order by no desc) n) "
				+ "where r between ? and ?";
		PreparedStatement st = con.prepareStatement(sql);
		st.setString(1, "%"+searchRow.getSearch().getSearch()+"%");
		st.setInt(2, searchRow.getStartRow());
		st.setInt(3, searchRow.getLastRow());
		ResultSet rs = st.executeQuery();
		while(rs.next()) {
			NoticeDTO noticeDTO = new NoticeDTO();
			noticeDTO.setNo(rs.getInt("no"));
			noticeDTO.setTitle(rs.getString("title"));
			noticeDTO.setWriter(rs.getString("writer"));
			noticeDTO.setReg_date(rs.getString("reg_date"));
			noticeDTO.setHit(rs.getInt("hit"));
			ar.add(noticeDTO);
		}
		rs.close();
		st.close();
		return ar;
	}

	@Override
	public BoardDTO selectOne(int num, Connection con) throws Exception {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int insert(BoardDTO boardDTO, Connection con) throws Exception {
		String sql = "insert into notice (no, title, contents, writer) values(?, ?, ?, ?)";
		PreparedStatement st = con.prepareStatement(sql);
		st.setInt(1, boardDTO.getNo());
		st.setString(2, boardDTO.getTitle());
		st.setString(3, boardDTO.getContents());
		st.setString(4, boardDTO.getWriter());
		int result = st.executeUpdate();
		st.close();
		return result;
	}

	@Override
	public int update(BoardDTO boardDTO, Connection con) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public int delete(int num, Connection con) throws Exception {
		// TODO Auto-generated method stub
		return 0;
	}

	
}
