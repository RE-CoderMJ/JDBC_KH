package service;

import static template.BooksCommons.*;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import model.dao.BooksDao;
import model.vo.Books;

public class BooksService {

	public ArrayList<Books> listOfBooks() {
		
		Connection conn = getConnection();
		ArrayList<Books> list = new BooksDao().listOfBooks(conn);
		
		close(conn);
		return list;
	}
	
	public ArrayList<Books> bookSearch(int searchBy, String inputValue) {
		
		Connection conn = getConnection();
		ArrayList<Books> list = new BooksDao().bookSearch(conn, searchBy, inputValue);
		
		close(conn);
		return list;
	}
	
	public int addaBook(Books b) {
		
		Connection conn = getConnection();
		int result = new BooksDao().addaBook(conn, b);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		return result;
	}
	
	public int editBookInfo(Books b) {
		
		Connection conn = getConnection();
		int result = new BooksDao().editBookInfo(conn, b);
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
	
	public int deleteBook(String bTitle) {
		
		Connection conn = getConnection();
		
		int result = new BooksDao().deleteBook(conn, bTitle);
		
		
		if(result > 0) {
			commit(conn);
		}else {
			rollback(conn);
		}
		
		close(conn);
		
		return result;
	}
}
