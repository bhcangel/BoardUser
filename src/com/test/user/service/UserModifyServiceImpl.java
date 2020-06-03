package com.test.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.user.model.UserDAO;
import com.test.user.model.UserVO;

public class UserModifyServiceImpl implements TestService{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		
		UserDAO dao = UserDAO.getInstance();
		UserVO vo = dao.getInfo((String)request.getSession().getAttribute("id"));
		
		request.getSession().setAttribute("vo", vo);
		return result;
		
		
	}

}
