package com.test.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.board.model.BoardDAO;

public class BoardDeleteServiceImpl implements BoardService{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		
		BoardDAO dao = BoardDAO.getInstance();
		String bno = request.getParameter("bno");
		
		int result = dao.delete(bno);
		return result;
	}

}
