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
		
		// 1) jdbc driver 등록
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
		
		return result; // 1 아니면 0 //다시듣기
	}
	
	public void selectList() {
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
				m.setHobby(rset.getString("hobby"));
				m.setEnrollDate(rset.getDate("enrolldate"));
				// 현재 참조하고 있는 행에 대한 모든 컬럼에 대한 데이터들을 한 Member객체에 담기 끝!
				
				list.add(m); // 리스트에 해당 회원 객체 차곡차곡담기				
			}
					
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		
		
	}
	
}
