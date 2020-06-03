package com.test.user.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.naming.InitialContext;
import javax.sql.DataSource;

import com.test.util.JdbcUtil;

public class UserDAO {

	private static UserDAO instance = new UserDAO();
	private DataSource ds;
	private Connection conn = null;
	private PreparedStatement pstmt = null;
	private ResultSet rs = null;

	UserVO vo = null;

	//2. 직접 생성할 수 없도록 생성자에 private을 붙임
	private UserDAO() {
		//객체가 생성될때 JDBC드라이버 로딩
		try {

			InitialContext ct = new InitialContext(); //초기설정값이 저장됨
			ds = (DataSource)ct.lookup("java:comp/env/jdbc/oracle"); //연결풀을 찾아서 DS에 저장

		} catch (Exception e) {
			System.out.println("클래스 로딩중 에러");
		}

	}
	//3. 외부에서 객체생성을 요구할 때 getter메서드를 통해 반환함
	public static UserDAO getInstance() {
		return instance;
	}

	public int login(String id, String pw) {
		int result = 0;

		String sql = "select * from users where id = ? and pw = ?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);
			pstmt.setString(2, pw);

			System.out.println("sql id : " + id);
			System.out.println("sql pw : " + pw);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				result = 1;
			} else{
				result = 0;
			}
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return result;
	}
	
	public int modify(UserVO vo) {
		int result = 0;
		
		String sql = "update users set pw = ?, name = ?, phone1 = ?, phone2 = ?, phone3 = ?, email1 = ?, email2 = ?, address1 = ?, address2 = ? where id = ?";
		
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getPw());
			pstmt.setString(2, vo.getName());
			pstmt.setString(3, vo.getPhone1());
			pstmt.setString(4, vo.getPhone2());
			pstmt.setString(5, vo.getPhone3());
			pstmt.setString(6, vo.getEmail1());
			pstmt.setString(7, vo.getEmail2());
			pstmt.setString(8, vo.getAddress());
			pstmt.setString(9, vo.getAddress2());
			pstmt.setString(10, vo.getId());
			
			System.out.println(vo.getId());
			System.out.println(vo.getName());
			System.out.println(vo.getPhone1());
			System.out.println(vo.getAddress());

			result = pstmt.executeUpdate();
			System.out.println("mod2 result : " + result);
		} catch(Exception e) {
			e.printStackTrace();
		}  finally {
			JdbcUtil.close(conn, pstmt, rs);
		} return result;
	}


	//회원가입 메서드
	public int join(UserVO vo) {
		int result = 0;

		String sql = "insert into users(id, pw, name, phone1, phone2, phone3, email1, email2, address1, address2) values(?,?,?,?,?,?,?,?,?,? )";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, vo.getId());
			pstmt.setString(2, vo.getPw());
			pstmt.setString(3, vo.getName());
			pstmt.setString(4, vo.getPhone1());
			pstmt.setString(5, vo.getPhone2());
			pstmt.setString(6, vo.getPhone3());
			pstmt.setString(7, vo.getEmail1());
			pstmt.setString(8, vo.getEmail2());
			pstmt.setString(9, vo.getAddress());
			pstmt.setString(10, vo.getAddress2());

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			e.printStackTrace();
		}  finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return result;
	}

	public UserVO getInfo(String id) {

		String sql = "select *  from users where id = ?";
		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			rs = pstmt.executeQuery();

			if(rs.next()) {
				vo = new UserVO();
				vo.setId(rs.getString("id"));
				vo.setName(rs.getString("name"));
				vo.setEmail1(rs.getString("email1"));
				vo.setEmail2(rs.getString("email2"));
				vo.setPhone1(rs.getString("phone1"));
				vo.setPhone2(rs.getString("phone2"));
				vo.setPhone3(rs.getString("phone3"));
				vo.setAddress(rs.getString("address1"));
				vo.setAddress2(rs.getString("address2"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}  finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return vo;
	}

	public int delete(String id) {
		int result = 0;

		String sql = "delete from users where id = ?";

		try {
			conn = ds.getConnection();
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, id);

			result = pstmt.executeUpdate();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}  finally {
			JdbcUtil.close(conn, pstmt, rs);
		}

		return result;
	}
}
