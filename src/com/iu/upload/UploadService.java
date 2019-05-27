package com.iu.upload;

import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.Action;
import com.iu.action.ActionForward;
import com.iu.util.DBConnector;

public class UploadService implements Action {
	
	private UploadDAO uploadDAO;
	
	public UploadService() {
		uploadDAO = new UploadDAO();
		// TODO Auto-generated constructor stub
	}

	@Override
	public ActionForward list(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward select(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward insert(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward delete(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		int pnum = Integer.parseInt(request.getParameter("pnum"));
		Connection con = null;
		int num = 0;
		
		try {
			con = DBConnector.getConnect();
			num = uploadDAO.delete(pnum, con);
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
		request.setAttribute("result", num);
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/views/common/result2.jsp");
		return actionForward;
	}

}
