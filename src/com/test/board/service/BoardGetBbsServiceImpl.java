package com.test.board.service;

import java.util.ArrayList;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.test.util.PageVO;
import com.test.board.model.BoardDAO;
import com.test.board.model.BoardVO;

public class BoardGetBbsServiceImpl implements BoardService{

	@Override
	public int execute(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		
		int pageNum = 1; //현재 페이지번호
		int amount = 10; //몇개씩 데이터를 출력할건지
		
		//pageNum가 넘어오는 경우에만, pageNum을 변경해서 전달
		if( request.getParameter("pageNum") != null | request.getParameter("amount") != null ) {
			pageNum = Integer.parseInt(request.getParameter("pageNum") );
			amount = Integer.parseInt( request.getParameter("amount") );
		}
		
		
		
		ArrayList<BoardVO> list = new ArrayList<>();
		BoardDAO dao = BoardDAO.getInstance();
		list = dao.getBbs(pageNum, amount);

		
		int total = dao.getTotal();
		/*
		 * for(BoardVO vo : list) { System.out.println("vo.getBno() : " + vo.getBno());
		 * System.out.println("vo.getTitle() : " + vo.getTitle()); }
		 */

		
		//3.PageVO세팅
		PageVO pageVO = new PageVO(pageNum, total, amount);

		//페이지 계산결과를 request에 저장하고 화면에 전달
		request.setAttribute("pageVO", pageVO);
		
		//화면으로 가져가기 위해서 request에 저장
		request.setAttribute("list", list);
		
		return 0;
		
		
	}

}
