package com.test.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.user.model.UserDAO;

public class UserDeleteServiceImpl implements TestService{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {

		int result2 = 0;

		UserDAO dao = UserDAO.getInstance();

		String id = (String)request.getSession().getAttribute("id");
		String pw = request.getParameter("pw");

		int result1 = dao.login(id, pw);

		System.out.println(result1);

		if(result1 == 1) {

			result2 = dao.delete((String)request.getSession().getAttribute("id"));

			System.out.println("result2 : " + result2);
		} else {
			
		}

		return result2;



	}

}
