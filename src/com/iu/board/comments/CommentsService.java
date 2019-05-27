package com.iu.board.comments;

import java.sql.Connection;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.iu.action.Action;
import com.iu.action.ActionForward;
import com.iu.util.DBConnector;

public class CommentsService implements Action {

	private CommentsDAO commentsDAO;
	
	public CommentsService() {
		commentsDAO = new CommentsDAO();
		
	}
	
	@Override
	public ActionForward list(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		//commentsDAO.selectList(searchRow, con);
		return actionForward;
	}

	@Override
	public ActionForward select(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public ActionForward insert(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		actionForward.setCheck(true);
		return actionForward;
	}

	@Override
	public ActionForward update(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		actionForward.setCheck(true);
		return actionForward;
	}

	@Override
	public ActionForward delete(HttpServletRequest request, HttpServletResponse response) {
		ActionForward actionForward = new ActionForward();
		return actionForward;
	}

}
