package com.test.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.board.model.BoardDAO;
import com.test.board.model.BoardVO;

public class BoardGetContentInfoImpl implements BoardService{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = BoardDAO.getInstance();
		
		String bno = request.getParameter("bno");
		
		BoardVO vo = dao.Content(bno);
		
		request.setAttribute("vo", vo);
		
		return 0;
	}

}
