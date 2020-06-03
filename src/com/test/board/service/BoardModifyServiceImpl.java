package com.test.board.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.board.model.BoardDAO;
import com.test.board.model.BoardVO;

public class BoardModifyServiceImpl implements BoardService{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		BoardDAO dao = BoardDAO.getInstance();
		
		BoardVO vo = new BoardVO();
		String bno = request.getParameter("bno");
		String writer = request.getParameter("writer");
		String title = request.getParameter("title");
		String content = request.getParameter("content");
		String regdate = request.getParameter("regdate");
		
		System.out.println("bno : " + bno);
		System.out.println("writer : " + writer);
		System.out.println("title : " + title);
		
		vo.setBno(bno);
		vo.setWriter(writer);
		vo.setTitle(title);
		vo.setContent(content);
		vo.setRegdate(regdate);
		
		System.out.println("vo.getBno() " + vo.getBno());
		System.out.println(vo.getWriter());
		System.out.println(vo.getTitle());
		
		request.setAttribute("vo", vo);
		
		
		int result = dao.modify(vo);
		
		return result;
		
		
	}

}
