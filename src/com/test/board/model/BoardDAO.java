package com.test.board.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.test.util.JdbcUtil;


public class BoardDAO {
	
	private static BoardDAO instance = new BoardDAO();
	private DataSource ds;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;
	
	//2. 직접 생성할 수 없도록 생성자에 private을 붙임
		private BoardDAO() {
			//객체가 생성될때 JDBC드라이버 로딩
			try {
				
				InitialContext ct = new InitialContext(); //초기설정값이 저장됨
				ds = (DataSource)ct.lookup("java:comp/env/jdbc/oracle"); //연결풀을 찾아서 DS에 저장
				
			} catch (Exception e) {
				System.out.println("클래스 로딩중 에러");
			}
			
		}
		
		//3. 외부에서 객체생성을 요구할 때 getter메서드를 통해 반환함
		public static BoardDAO getInstance() {
			return instance;
		}
		
		public ArrayList<BoardVO> search(String id){
			String sql = "select * from board where writer = ?";
			ArrayList<BoardVO> list = new ArrayList<>();
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, id);
				rs = pstmt.executeQuery();
				
				while(rs.next()) {
					BoardVO vo = new BoardVO();
					vo.setBno(rs.getString("bno"));
					vo.setWriter(rs.getString("writer"));
					vo.setTitle(rs.getString("title"));
					vo.setContent(rs.getString("content"));
					vo.setRegdate(rs.getString("regdate"));
					
					list.add(vo);
					
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				JdbcUtil.close(conn, pstmt, rs);
			} return list;
		}
		
		public int delete(String bno) {
			String sql = "delete from board where bno = ?";
			int result = 0;
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bno);
				result = pstmt.executeUpdate();
				
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				JdbcUtil.close(conn, pstmt, rs);
			} return result;
		}
		
		public BoardVO Content(String bno) {		//게시물 상세보기
			
			String sql = "select * from board where bno = ?";
			BoardVO vo = new BoardVO();
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, bno);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					
					vo.setBno(rs.getString("bno"));
					vo.setWriter(rs.getString("writer"));
					vo.setTitle(rs.getString("title"));
					vo.setContent(rs.getString("content"));
					vo.setRegdate(rs.getString("regdate"));
				} else {
					
				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				JdbcUtil.close(conn, pstmt, rs);
			}
			
			return vo;
		}
		
		
		public int modify(BoardVO vo) {
			int result = 0;
			
			String sql = "update board set writer = ?, title = ?, content = ? where bno = ?";
			
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, vo.getWriter());
				pstmt.setString(2, vo.getTitle());
				pstmt.setString(3, vo.getContent());
				pstmt.setString(4, vo.getBno());
				
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
			} finally {
				JdbcUtil.close(conn, pstmt, rs);
			} return result;
		}
		
		//전체 게시글 수를 구하는 메서드
		public int getTotal() {
			
			int total = 0;
			
			String sql = "select count(*) as total from board";
			
			try {
				
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				rs = pstmt.executeQuery();
				
				if(rs.next()) {
					total = rs.getInt("total");
				}
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				JdbcUtil.close(conn, pstmt, rs);
			}
			return total;
		}
		
		public ArrayList<BoardVO> getBbs(int pageNum, int amount) {
			
			ArrayList<BoardVO> list = new ArrayList<>();
			
			String sql = "select *\r\n" + 
					"from(\r\n" + 
					"select rownum rn,\r\n" + 
					"           bno,\r\n" + 
					"           writer,\r\n" + 
					"           title,\r\n" + 
					"           content,\r\n" + 
					"           regdate\r\n" + 
					"    from(\r\n" + 
					"        select *\r\n" + 
					"        from board order by bno desc\r\n" + 
					"        )\r\n" + 
					")\r\n" + 
					"where rn > ? and rn <= ?";
			
			try {
				conn = ds.getConnection();
				pstmt = conn.prepareStatement(sql);
				pstmt.setInt( 1, (pageNum - 1) * amount );
				pstmt.setInt( 2, pageNum * amount );
				rs = pstmt.executeQuery();
				while(rs.next()) {
					BoardVO vo = new BoardVO();
					
					vo.setBno(rs.getString("bno"));
					vo.setWriter(rs.getString("writer"));
					vo.setTitle(rs.getString("title"));
					vo.setContent(rs.getString("content"));
					vo.setRegdate(rs.getString("regdate"));
					
					list.add(vo);
					//BoardVO vo = new BoardVO(bno, writer, title, content, regdate, hit);
					
					//list.add(vo);

				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				JdbcUtil.close(conn, pstmt, rs);
			} return list;
		}
		
		
		public int Write(BoardVO vo) {
			int result = 0;
			
			String sql ="insert into board(bno, writer, title, content) values(board_seq.nextval, ?, ?, ?)";
			
			try {
				conn = ds.getConnection();
				
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, vo.getWriter());
				pstmt.setString(2, vo.getTitle());
				pstmt.setString(3, vo.getContent());
				result = pstmt.executeUpdate();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			} finally {
				JdbcUtil.close(conn, pstmt, rs);
			}
			
			return result;
			
		}
}
