package com.test.user.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.user.model.UserDAO;
import com.test.user.model.UserVO;

public class UserModifyService2Impl implements TestService{
	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		int result = 0;
		
		UserDAO dao = UserDAO.getInstance();
		
		String id = (String)request.getSession().getAttribute("id");
		System.out.println("mod2 id : " + id);
		String pw = request.getParameter("pw");
		String name = request.getParameter("name");
		String email1 = request.getParameter("email1");
		String email2 = request.getParameter("email2");
		String phone1 = request.getParameter("phone1");
		String phone2 = request.getParameter("phone2");
		String phone3 = request.getParameter("phone3");
		String address1 = request.getParameter("address1");
		String address2 = request.getParameter("address2");
		

		System.out.println("mod2 name : " + name);
		System.out.println("mod2 pw : " + pw);
		System.out.println("mod2 address1 : " + address1);
		
		UserVO vo = new UserVO();
		vo.setId(id);
		vo.setPw(pw);
		vo.setName(name);
		vo.setEmail1(email1);
		vo.setEmail2(email2);
		vo.setPhone1(phone1);
		vo.setPhone2(phone2);
		vo.setPhone3(phone3);
		vo.setAddress(address1);
		vo.setAddress2(address2);
		
		result = dao.modify(vo);
		
		return result;
		
		
	}
}
