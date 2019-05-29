package com.iu.upload;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Enumeration;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.Action;
import com.iu.action.ActionForward;
import com.iu.util.DBConnector;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

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
		//스마트에디터 파일 업로드
		ActionForward actionForward = new ActionForward();
		int maxPostSize = 1024*1024*50;
		String saveDirectory = request.getServletContext().getRealPath("upload_se2");
		//System.out.println(saveDirectory);
		File file = new File(saveDirectory);
		if(!file.exists()) {
			file.mkdirs();
		}
		
		try {
			MultipartRequest multi = new MultipartRequest(request, saveDirectory, maxPostSize, "utf-8", new DefaultFileRenamePolicy());
			String callback = multi.getParameter("callback");
			String callback_func =multi.getParameter("callback_func");
			String fname = multi.getFilesystemName("Filedata");
			
			//1.절대경로
			String path = request.getContextPath();
			
			//2.최종결과물
			String result = "&bNewLine=true&sFileURL="+path+"/upload_se2/"+fname;
			
			//3.
			result=callback+"?callback_func="+callback_func+result;
			actionForward.setCheck(false);
			actionForward.setPath(result);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return actionForward;
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
