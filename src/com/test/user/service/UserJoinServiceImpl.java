package com.test.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.user.model.UserDAO;
import com.test.user.model.UserVO;

public class UserJoinServiceImpl implements TestService {

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		int result = 0;

		String id = "";
		String pw = "";
		String name = "";
		String phone1 = "";
		String phone2 = "";
		String phone3 = "";
		String email1 = "";
		String email2 = "";
		String address1 = "";
		String address2 = "";

		id = request.getParameter("id");
		pw = request.getParameter("password");
		name = request.getParameter("name");
		phone1 = request.getParameter("phone1");
		phone2 = request.getParameter("phone2");
		phone3 = request.getParameter("phone3");
		email1 = request.getParameter("email1");
		email2 = request.getParameter("email2");
		address1 = request.getParameter("addr-basic");
		address2 = request.getParameter("addr-detail");

		System.out.println(id);
		System.out.println(pw);
		System.out.println(name);
		System.out.println(phone1);
		System.out.println(phone2);

		UserVO vo = new UserVO(id, pw, name, phone1, phone2, phone3, email1, email2, address1, address2);
		UserDAO dao = UserDAO.getInstance();
		result = dao.join(vo);

		System.out.println(result);

		return result;
	}

}
