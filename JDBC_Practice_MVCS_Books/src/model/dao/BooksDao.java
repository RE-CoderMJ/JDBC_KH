package model.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import static template.BooksCommons.*;

import model.vo.Books;

public class BooksDao {

	public ArrayList<Books> listOfBooks(Connection conn){
		ArrayList<Books> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = "SELECT * FROM BOOKS";
		
		try {
			pstmt = conn.prepareStatement(sql);
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Books(rset.getInt("bookno"),
								   rset.getString("btitle"),
								   rset.getString("bauthor"),
								   rset.getInt("bprice"),
								   rset.getString("bcate"),
								   rset.getString("bpub"),
								   rset.getDate("wdate")
								   ));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	public ArrayList<Books> bookSearch(Connection conn, int searchBy, String inputValue){
		ArrayList<Books> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = " ";
		switch(searchBy) {
		case 1: sql = "SELECT * FROM BOOKS WHERE BTITLE LIKE ?"; break;
		case 2: sql = "SELECT * FROM BOOKS WHERE BCATE LIKE ?"; break;
		case 3: sql = "SELECT * FROM BOOKS WHERE BAUTHOR LIKE ?"; break;
		case 4: sql = "SELECT * FROM BOOKS WHERE BPUB LIKE ?"; break;
		}
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, "%" + inputValue + "%");
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				list.add(new Books(rset.getInt("bookno"),
								   rset.getString("btitle"),
								   rset.getString("bauthor"),
								   rset.getInt("bprice"),
								   rset.getString("bcate"),
								   rset.getString("bpub"),
								   rset.getDate("wdate")));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	
	public int addaBook(Connection conn, Books b) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		
		String sql = "INSERT INTO BOOKS VALUES(SEQ_BOOKNO.NEXTVAL, ?, ?, ?, ?, ?, SYSDATE)";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getbTitle());
			pstmt.setString(2, b.getbAuthor());
			pstmt.setInt(3, b.getbPrice());
			pstmt.setString(4, b.getbCate());
			pstmt.setString(5, b.getbPub());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		
		return result;
	}
	
	public int editBookInfo(Connection conn, Books b) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "UPDATE BOOKS SET BAUTHOR = ?, BPRICE = ?, BCATE = ?, BPUB = ? WHERE BTITLE = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, b.getbAuthor());
			pstmt.setInt(2, b.getbPrice());
			pstmt.setString(3, b.getbCate());
			pstmt.setString(4, b.getbPub());
			pstmt.setString(5, b.getbTitle());
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
	
	public int deleteBook(Connection conn, String bTitle) {
		
		PreparedStatement pstmt = null;
		int result = 0;
		String sql = "DELETE FROM BOOKS WHERE BTITLE = ?";
		
		try {
			pstmt = conn.prepareStatement(sql);
			pstmt.setString(1, bTitle);
			
			result = pstmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(pstmt);
		}
		
		return result;
	}
}
