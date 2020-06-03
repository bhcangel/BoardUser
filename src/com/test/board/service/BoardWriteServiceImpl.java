package com.test.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.board.model.BoardDAO;
import com.test.board.model.BoardVO;

public class BoardWriteServiceImpl implements BoardService{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		
		System.out.println(writer);
		System.out.println(title);
		System.out.println(content);
		
		BoardDAO dao = BoardDAO.getInstance();
		BoardVO vo = new BoardVO(writer, title, content, null, null);
		dao.Write(vo);
		
		return result;
	}

}
