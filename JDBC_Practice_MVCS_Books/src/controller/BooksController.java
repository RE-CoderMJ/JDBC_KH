package controller;

import java.util.ArrayList;

import model.vo.Books;
import view.BooksMenu;
import service.BooksService;

public class BooksController {

	public void listOfBooks(){
		
		ArrayList<Books> list = new BooksService().listOfBooks();
		
		if(list.isEmpty()) {
			new BooksMenu().displayNoData("There is no data.");
		}else {
			new BooksMenu().displayListofBooks(list);
		}
		
	}
	
	public void bookSearch(int searchBy, String inputValue) {
		
		ArrayList<Books> list = new BooksService().bookSearch(searchBy, inputValue);
		
		if(list.isEmpty()) {
			new BooksMenu().displayNoData("No data found for" + inputValue);
		}else {
			new BooksMenu().displayListofBooks(list);
		}
		
	}
	
	public void addaBook(String bTitle, String bAuthor, String bPrice, String bCate, String bPub) {
		
		Books b = new Books(bTitle, bAuthor, Integer.parseInt(bPrice), bCate, bPub);
		int result = new BooksService().addaBook(b);
		
		if(result > 0) {
			new BooksMenu().displaySuccess("Congrats! The new Book has been successfully added!");
		}else {
			new BooksMenu().displayFail("Please Check Again.");
		}
	}
	
	public void editBookInfo(String bTitle, String bAuthor, String bPrice, String bCate, String bPub) {
		
		Books b = new Books(bTitle, bAuthor, Integer.parseInt(bPrice), bCate, bPub);
		
		int result = new BooksService().editBookInfo(b);
		
		if(result > 0) {
			new BooksMenu().displaySuccess("Congrats! The new Book has been successfully edited!");
		}else {
			new BooksMenu().displayFail("Please Check Again.");
		}
	}
	
	public void deleteBook(String bTitle) {
		
		int result = new BooksService().deleteBook(bTitle);
		
		if(result > 0) {
			new BooksMenu().displaySuccess(bTitle + " has been successfully deleted!");
		}else {
			new BooksMenu().displayFail("Please Check Again.");
		}
	}
}
