package com.test.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.board.service.BoardContentServiceImpl;
import com.test.board.service.BoardDeleteServiceImpl;
import com.test.board.service.BoardGetBbsServiceImpl;
import com.test.board.service.BoardModifyServiceImpl;
import com.test.board.service.BoardService;
import com.test.board.service.BoardWriteServiceImpl;
import com.test.user.service.TestService;
import com.test.user.service.UserJoinServiceImpl;


@WebServlet("*.board")
public class BoardController extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
    public BoardController() {
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
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		
		String uri = request.getRequestURI(); //URI정보
		String path = request.getContextPath(); //컨택스트 패스 정보
		String command = uri.substring(path.length());
		
		System.out.println(request.getRemoteAddr());
		System.out.println("command : " + command);
		
		BoardService service = null;
		
		
		if(command.equals("/index.board")) {
			service = new BoardGetBbsServiceImpl();
			service.execute(request, response);
			request.getRequestDispatcher("index.jsp").forward(request, response);
			
		} else if(command.equals("/bbs.board")) {
			service = new BoardGetBbsServiceImpl();
			service.execute(request, response);
			request.getRequestDispatcher("bbs.jsp").forward(request, response);
			
		} else if(command.equals("/write.board")) {
			response.sendRedirect("bbs_write.jsp");
			
		} else if(command.equals("/writeForm.board")) {
			System.out.println("writeForm");
			service = new BoardWriteServiceImpl();
			int result = service.execute(request, response);
			System.out.println(result);
			
			response.sendRedirect("bbs.board");
		} else if(command.equals("/content.board")) {
			service = new BoardContentServiceImpl();
			service.execute(request, response);
			
			request.getRequestDispatcher("bbs_content.jsp").forward(request, response);
			
			
		} else if(command.equals("/modify.board")) {
			service = new BoardContentServiceImpl();
			service.execute(request, response);
			request.getRequestDispatcher("bbs_modify.jsp").forward(request, response);
			
			
		} else if(command.equals("/modifyForm.board")) {
			System.out.println("con modifyForm");
			service = new BoardModifyServiceImpl();
			int result = service.execute(request, response);
			System.out.println("ModifyForm result : " + result);
			response.sendRedirect("bbs.board");
		} else if(command.equals("/delete.board")) {
			service = new BoardDeleteServiceImpl();
			service.execute(request, response);
			response.sendRedirect("bbs.board");
		}
	}

}
