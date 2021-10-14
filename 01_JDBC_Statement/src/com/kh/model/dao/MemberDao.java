package com.kh.model.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.kh.model.vo.Member;

// DAO(Data Access Object) : DB에 직접적으로 접근해서 사용자의 요청에 맞는 sql문 실행 후 결과받기
//							  결과를 Controller로 다시 리턴
public class MemberDao {
	
	/**
	 * 사용자가 입력한 정보들을 추가시켜주는 메소드
	 * @param m		: 사용자가 입력한 값들이 주섬주섬 담겨있는 Member 객체
	 * @return		: insert문 실행 후 처리된 행 수
	 */
	public int insertMember(Member m) {
		// insert문 => 처리된 행수(int) => 트랜잭션 처리
		
		// 필요한 변수들 먼저 셋팅
		int result = 0;		// 처리된 결과(처리된 행수)를 받아줄 변수
		Connection conn = null; // 연결된 DB의 연결정보를 담는 객체
		Statement stmt = null;  // "완성된 sql문(실제값이 다 채워진 상태로)" 전달해서 곧바로 실행 후 결과 받는 객체
		
		// 실행할 sql문 (완성된 형태로 만들어두기!!)
		// INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, 'XXXX', 'XXXXX', 'XXX', 'X', XX, 'XXXX', 'XXXXX','XXXXX', 'XXXXX', SYSDATE)
		String sql = "INSERT INTO MEMBER VALUES(SEQ_USERNO.NEXTVAL, " 
										+ "'" + m.getUserId() 	+ "', "  
										+ "'" + m.getUserPwd()	+ "', "
										+ "'" + m.getUserName() + "', "
										+ "'" + m.getGender()	+ "', "
											  + m.getAge()		+ ", "
										+ "'" + m.getEmail()	+ "', "
										+ "'" + m.getPhone()	+ "', "
										+ "'" + m.getAddress()	+ "', "
										+ "'" + m.getHobby()	+ "', sysdate)";
		
		//System.out.println(sql);
		
		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2) Connection 객체 생성 == DB에 연결 (url, 계정명, 비밀번호)
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			// 3) Statement 객체 생성
			stmt = conn.createStatement();
			// 4,5) sql문을 전달하면서 실행 후 결과(처리된행수)받기 
			result= stmt.executeUpdate(sql);
			
			// 6) 트랜잭션 처리
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 7) 다 쓴 JDBC용 객체 반납
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			
		}
		
		return result; // 1 아니면 0
	}
	
	/**
	 * 사용자가 요청한 회원 전체 조회를 처리해주는 메소드
	 * @return 조회된 결과가 있었으면 조회된 결과들이 담겨있는 list | 조회된 결과가 없었으면 텅 빈 list
	 */
	public ArrayList<Member> selectList() {
		// select문 (여러행조회) => ResultSet객체 => ArrayList에 차곡차곡 담기
		
		// 필요한 변수들 셋팅
		ArrayList<Member> list = new ArrayList<>(); // 현재 상태는 텅 비어있는 상태
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null; // select문 실행시 조회된 결과값들이 최초로 담기는 객체
		
		// 실행할 sql문
		String sql = "SELECT * FROM MEMBER";
		
		try {
			// 1) jdbc driver 등록
			Class.forName("oracle.jdbc.driver.OracleDriver");
			// 2) Connection 생성
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			// 3) Statement 생성
			stmt = conn.createStatement();
			// 4, 5) sql문 실행 및 결과받기
			rset = stmt.executeQuery(sql);
			
			// 6) ResultSet으로부터 데이터 하나씩 뽑아서 vo 객체에 주섬주섬 담고 + list에 vo객체 추가
			while(rset.next()) {
				// 현재 rset의 커서가 가리키고 있는 한 행의 데이터들을 싹 다 뽑아서 Member객체 주섬주섬 담기
				Member m = new Member();
				
				m.setUserNo(rset.getInt("USERNO"));
				m.setUserId(rset.getString("USERID"));
				m.setUserPwd(rset.getString("userpwd"));
				m.setUserName(rset.getString("username"));
				m.setGender(rset.getString("gender"));
				m.setAge(rset.getInt("age"));
				m.setEmail(rset.getString("email"));
				m.setPhone(rset.getString("phone"));
				m.setAddress(rset.getString("address"));
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				// 현재 참조하고 있는 행에 대한 모든 컬럼에 대한 데이터들을 한 Member객체에 담기 끝!
				
				list.add(m); // 리스트에 해당 회원 객체 차곡차곡담기				
			}
			
			// 반복문이 다 끝난 시점에
			// 만약에 조회된 데이터가 없었다면 list가 텅 빈 상태일거임!!
			// 만약에 조회된 데이터가 있었다면 list에 뭐라도 담겨있을 것!!
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				// 7) 다쓴자원반납
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list; // 텅빈리스트 | 뭐라도 담겨있는 리스트
		
	}
	
	
	/**
	 * 사용자의 아이디로 회원 검색 요청 처리해주는 메소드
	 * @param userId	사용자가 입력한 검색하고자하는 회원 아이디값
	 * @return  검색된 결과가 있었으면 생성된 Member객체 | 검색된 결과가 없었으면 null
	 */
	public Member selectByUserId(String userId) {
		// select문(한행) => ResultSet객체 => Member객체
		
		Member m = null;
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;
		
		// 실행할 sql문 (완성된 형태로)
		// SELECT * FROM MEMBER WHERE USERID = 'XXX'
		String sql = "SELECT * FROM MEMBER WHERE USERID = '" + userId + "'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC","JDBC");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			if(rset.next()) {
				// 조회됐다면 해당 조회된 컬럼 값들을 뽑아서 한 Member 객체의 각 필드에 담기
				m = new Member(rset.getInt("userno"),
							   rset.getString("userid"),
							   rset.getString("userpwd"),
							   rset.getString("username"),
							   rset.getString("gender"),
							   rset.getInt("age"),
							   rset.getString("email"),
							   rset.getString("phone"),
							   rset.getString("address"),
							   rset.getString("hobby"),
							   rset.getDate("enrolldate"));
			}
			
			// 위의조건문 다 끝난 시점에
			// 만약에 조회된 데이터가 없었을 경우 => m은 null 상태
			// 만약에 조회된 데이터가 있었을 경우 => m은 생성된 객체
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return m;
	}
	
	public ArrayList<Member> selectByUserName(String keyword){
		// select문(여러행) => ResultSet객체  => ArrayList
		ArrayList<Member> list = new ArrayList<>(); // 텅 빈 리스트
		
		Connection conn = null;
		Statement stmt = null;
		ResultSet rset = null;		
		
		String sql = "SELECT * FROM MEMBER WHERE USERNAME LIKE '%" + keyword + "%'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.createStatement();
			rset = stmt.executeQuery(sql);
			
			while(rset.next()) {
				list.add(new Member(rset.getInt("userno"),
									rset.getString("userid"),
									rset.getString("userpwd"),
									rset.getString("username"),
									rset.getString("gender"),
									rset.getInt("age"),
									rset.getString("email"),
									rset.getString("phone"),
									rset.getString("address"),
									rset.getString("hobby"),
									rset.getDate("enrolldate")));
			}
			
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				rset.close();
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return list;
	}
	
	public int updateMember(Member m) {
		
		Connection conn = null;
		Statement stmt = null;
		int result = 0;
		
		String sql = "UPDATE MEMBER "
					+   "SET USERPWD = '" + m.getUserPwd() + "' "
					+ 	  ", EMAIL = '"	  + m.getEmail()   + "' " 
					+ 	  ", PHONE = '"	  + m.getPhone()   + "' " 
					+     ", ADDRESS = '" + m.getAddress() + "' " 
					+ "WHERE USERID = '"  + m.getUserId()  + "'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
			
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
	}
	
	/**
	 * 사용자가 입력한 아이디값 전달 바다서 회원 탈퇴 시켜주는 메소드
	 * @param userId  사용자가 입력한 아이디값
	 * @return  처리된 행 수
	 */
	public int deleteMember(String userId) {
		/* DELETE FROM MEMBER WHERE USERID = '사용자가입력한아이디값' */
		// delete문 => 처리된 행수=> 트랜잭션 처리
		int result = 0;
		
		Connection conn = null;
		Statement stmt = null;
		
		String sql = "DELETE FROM MEMBER WHERE USERID = '" + userId + "'";
		
		try {
			Class.forName("oracle.jdbc.driver.OracleDriver");
			conn = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:xe", "JDBC", "JDBC");
			stmt = conn.createStatement();
			result = stmt.executeUpdate(sql);
			
			if(result > 0) {
				conn.commit();
			}else {
				conn.rollback();
			}
	
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			try {
				stmt.close();
				conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return result;
		
	}
	
	
}
