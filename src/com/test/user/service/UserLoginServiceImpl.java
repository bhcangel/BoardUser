package com.test.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.user.model.UserDAO;

public class UserLoginServiceImpl implements TestService{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		
		String id = request.getParameter("id");
		String pw = request.getParameter("pw");
		
		UserDAO dao = UserDAO.getInstance();
		

		System.out.println(id);
		System.out.println(pw);
		
		result = dao.login(id, pw);
		
		return result;
	}

}
