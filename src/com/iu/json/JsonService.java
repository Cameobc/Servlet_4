package com.iu.json;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;
import java.util.Random;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.iu.board.BoardDAO;
import com.iu.board.BoardDTO;
import com.iu.board.comments.CommentsDAO;
import com.iu.board.comments.CommentsDTO;
import com.iu.board.qna.QnaDAO;
import com.iu.page.SearchMakePage;
import com.iu.page.SearchRow;
import com.iu.util.DBConnector;

public class JsonService {
	
	public void test2(HttpServletRequest request, HttpServletResponse response){
		CommentsDAO commentsDAO = new CommentsDAO();
		int num= Integer.parseInt(request.getParameter("num"));
		SearchMakePage s = new SearchMakePage(1, "", "");
		SearchRow searchRow = s.makeRow();
		Connection con = null;
		List<CommentsDTO> ar = null;
		try {
			con = DBConnector.getConnect();
			ar = commentsDAO.selectList(searchRow, num, con);
			JSONArray jsonar = new JSONArray(); //[]
			for(CommentsDTO dto : ar) {
				JSONObject jsonObject = new JSONObject();//{}
				jsonObject.put("writer", dto.getWriter());
				jsonObject.put("contents", dto.getContents());
				jsonar.add(jsonObject);
			}
			JSONObject js = new JSONObject();
			js.put("ar", jsonar);
			PrintWriter out = response.getWriter();
			out.println(js.toJSONString());  //jsonar.toJSONString()
			out.close();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

	public void test1(HttpServletRequest request, HttpServletResponse response) {
		//this.test2(request, response);
		int num = Integer.parseInt(request.getParameter("num"));
		QnaDAO qnaDAO = new QnaDAO();
		Connection con=null;
		try {
			con = DBConnector.getConnect();
			BoardDTO boardDTO = qnaDAO.selectOne(num, con);
			JSONObject jsonObject = new JSONObject();  //=={}
			jsonObject.put("writer", boardDTO.getWriter());  // {"writer":"test"}
			jsonObject.put("contents", boardDTO.getContents());// {"writer":"test", "contents":"test"}
			jsonObject.put("title", boardDTO.getTitle());// {"writer":"test", "contents":"test", "title":"test"}
			PrintWriter out = response.getWriter();
			out.println(jsonObject.toJSONString());
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		/*Random random = new Random();
		int result = random.nextInt(num);
		PrintWriter out;
		try {
			out = response.getWriter();
			//json{"키":"밸류"}
			String j = "{\"number\":\""+result+"\"}";
			System.out.println(j);
			out.println(j);
			out.close();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}*/
	}
}
