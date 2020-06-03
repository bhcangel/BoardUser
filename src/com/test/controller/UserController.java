package com.test.controller;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.websocket.SendResult;

import com.test.user.service.TestService;
import com.test.user.service.UserDeleteServiceImpl;
import com.test.user.service.UserJoinServiceImpl;
import com.test.user.service.UserLoginServiceImpl;
import com.test.user.service.UserModifyService2Impl;
import com.test.user.service.UserModifyServiceImpl;
import com.test.user.service.UserMyTitleService;

@WebServlet("*.user")
public class UserController extends HttpServlet {
	private static final long serialVersionUID = 1L;
     
    public UserController() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doAction(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doAction(request, response);
	}
	
	protected void doAction(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI(); //URI정보
		String path = request.getContextPath(); //컨택스트 패스 정보
		String command = uri.substring(path.length());
		
		System.out.println(request.getRemoteAddr());
		
		System.out.println(uri);
		System.out.println(path);
		System.out.println(command);
		
		TestService service = null;
		
		if(command.equals("/join.user")) {
			response.sendRedirect("user_join.jsp");
		}
		else if(command.equals("/joinForm.user")) {
			service = new UserJoinServiceImpl();
			int result = service.execute(request, response);
			
			
			if(result == 1) {
				response.sendRedirect("user_login.jsp");
			} else {
				
			}
		}else if(command.equals("/login.user")) {
			response.sendRedirect("user_login.jsp");
		} else if(command.equals("/loginForm.user")) {
			service = new UserLoginServiceImpl();
			int result = service.execute(request, response);
			
			System.out.println("로그인");
			
			if(result == 1) {
				System.out.println("로그인성공");
				request.getSession().setAttribute("id", request.getParameter("id"));
				response.sendRedirect("mypage.user");
			} else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('회원정보 비밀번호를 확인하세요')");
				out.println("location.href='login.user' ");
				out.println("</script>");
			}
		}else if(command.equals("/modify.user")) {
			System.out.println("modify.user con");
			service = new UserModifyServiceImpl();
			service.execute(request, response);
			response.sendRedirect("user_mypageinfo.jsp");
			
		} else if(command.equals("/modifyForm.user")) {
			
			service = new UserModifyService2Impl();
			service.execute(request, response);
			
			request.getRequestDispatcher("mypage.user").forward(request, response);
			
		} else if(command.equals("/mypage.user")) {
			service = new UserMyTitleService();
			service.execute(request, response);
			request.getRequestDispatcher("user_mypage.jsp").forward(request, response);
		}
		
		else if(command.equals("/delete.user")) {
			
			System.out.println("delete control");
			service = new UserDeleteServiceImpl();
			int result = service.execute(request, response);
			
			System.out.println("del con result : " + result);
			
			if(result == 1) {
				request.getSession().invalidate();
				response.sendRedirect("login.user");
			} else {
				response.setContentType("text/html; charset=utf-8");
				PrintWriter out = response.getWriter();
				out.println("<script>");
				out.println("alert('회원정보 비밀번호를 확인하세요')");
				out.println("location.href='mypage.user' ");
				out.println("</script>");
			}
			
		} else if(command.equals("/logout.user")) {
			request.getSession().invalidate();
			System.out.println(request.getSession().getAttribute("id"));
			response.sendRedirect("login.user");
		}
	}

}
