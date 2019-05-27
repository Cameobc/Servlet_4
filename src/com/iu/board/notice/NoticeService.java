package com.iu.board.notice;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.Action;
import com.iu.action.ActionForward;
import com.iu.board.BoardDTO;
import com.iu.page.SearchMakePage;
import com.iu.page.SearchPager;
import com.iu.page.SearchRow;
import com.iu.upload.UploadDAO;
import com.iu.upload.UploadDTO;
import com.iu.util.DBConnector;
import com.oreilly.servlet.MultipartRequest;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;

public class NoticeService implements Action {
	private NoticeDAO noticeDAO;
	private UploadDAO uploadDAO;
	
	public NoticeService() {
		noticeDAO = new NoticeDAO();
		uploadDAO = new UploadDAO();
	}

	@Override
	public ActionForward list(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		int curPage =1;
		Connection con = null;
		try {
		curPage = Integer.parseInt(request.getParameter("curPage"));
		}catch (Exception e) {
		}		
		String kind = request.getParameter("kind");
		String search = request.getParameter("search");
		
		SearchMakePage s = new SearchMakePage(curPage, kind, search);
		
		//1.Row
		SearchRow searchRow = s.makeRow();
		List<BoardDTO> ar = null;
		try {
			con = DBConnector.getConnect();
			ar =noticeDAO.selectList(searchRow, con);
			int totalCount = noticeDAO.getTotalCount(searchRow, con);
			SearchPager searchPager =s.makePage(totalCount);
			request.setAttribute("pager", searchPager);
			request.setAttribute("list", ar);
			request.setAttribute("board", "notice");
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/views/board/boardList.jsp");
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("message", "Sever Error");
			request.setAttribute("path", "../index.do");
			actionForward.setCheck(true);
			actionForward.setPath("../WEB-INF/views/common/result.jsp");

		}finally {
			try {
				con.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		return actionForward;
	}

	@Override
	public ActionForward select(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		actionForward.setCheck(true);
		actionForward.setPath("../WEB-INF/views/index.jsp");
		return actionForward;
	}

	@Override
	public ActionForward insert(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		String method = request.getMethod(); //GET, POST
		String path ="../WEB-INF/views/board/boardWrite.jsp";
		boolean check = true;
		
		if(method.equals("POST")) {
			NoticeDTO noticeDTO = new NoticeDTO();
			//1.request를 하나로 합치기
			//파일을저장할디스크경로C
			String saveDirectory =request.getServletContext().getRealPath("upload"); // application
			System.out.println(saveDirectory);
			int maxPostSize = 1024*1024*10; //10MB//byte단위
			String encoding ="utf-8";
			MultipartRequest multi = null;
			try {
				 multi = new MultipartRequest(request, saveDirectory, maxPostSize, encoding, new DefaultFileRenamePolicy());
				 Enumeration<String> e = multi.getFileNames();  // 파라미터 이름들
				 ArrayList<UploadDTO> ar = new ArrayList<UploadDTO>();
				 while(e.hasMoreElements()) {
					 UploadDTO uploadDTO = new UploadDTO();
					 String  s = e.nextElement();
					 String fname = multi.getFilesystemName(s);
					 String oname = multi.getOriginalFileName(s);
					 uploadDTO.setfName(fname);
					 uploadDTO.setoName(oname);
					 ar.add(uploadDTO);
				 }
			} catch (IOException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			//파일저장이 된다.
			
			//hdd(server)에 저장된 이름
			UploadDTO uploadDTO = new UploadDTO();
			String fileName =multi.getFilesystemName("f1"); // 파일의 파라미터 이름
			String oName = multi.getOriginalFileName("f1"); // 파일의 파라미터 이름
			noticeDTO.setTitle(multi.getParameter("title"));
			noticeDTO.setContents(multi.getParameter("contents"));
			noticeDTO.setWriter(multi.getParameter("writer"));
			uploadDTO.setfName(fileName);
			uploadDTO.setoName(oName);

			int result =0;
			Connection con = null;
			try {
				int num = noticeDAO.getNum();
				con = DBConnector.getConnect();
				//auto commit 해제 -> 트랜잭션내에서는 모두 성공해야 커밋
				con.setAutoCommit(false);
				noticeDTO.setNo(num);
				result = noticeDAO.insert(noticeDTO, con);
				
				uploadDTO.setNo(num);
				result=uploadDAO.insert(uploadDTO, con);
				if(result<1) {
					throw new Exception();
				}
				con.commit();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				result=0;
				try {
					con.rollback();
				} catch (SQLException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				e.printStackTrace();
			}finally {
				try {
					con.setAutoCommit(true);
					con.close();
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			if(result>0) {
				check=false;
				path="./noticeList";
			}else {
				request.setAttribute("message", "Insert Fail");
				request.setAttribute("path", "./noticeList");
				check=true;
				path="../WEB-INF/views/common/result.jsp";
			}//post end
		}
		
		actionForward.setCheck(check);
		actionForward.setPath(path);
		return actionForward;
	}

	@Override
	public ActionForward update(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward delete(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

}
