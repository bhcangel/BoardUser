package com.test.user.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.board.model.BoardDAO;
import com.test.board.model.BoardVO;

public class UserMyTitleService implements TestService{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		String id = (String)request.getSession().getAttribute("id");
		BoardDAO dao = BoardDAO.getInstance();
		ArrayList<BoardVO> list = dao.search(id);
		System.out.println("mytitle id: " + id);
		request.setAttribute("list", list);
		System.out.println("list : " + list.toString());
		return 0;
	}

}
