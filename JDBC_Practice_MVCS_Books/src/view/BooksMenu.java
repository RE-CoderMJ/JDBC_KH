package view;

import java.util.ArrayList;
import java.util.Scanner;

import controller.BooksController;
import model.vo.Books;

public class BooksMenu {

	private Scanner sc = new Scanner(System.in);
	
	private BooksController bc = new BooksController();
	
	public void mainMenu() {
		
		while(true) {
			System.out.println("\n***** Welcome to Book Management System *****");
			System.out.println("\n1. List of books");
			System.out.println("2. Book Search"); // name, category, author, publisher
			System.out.println("3. Add a Book");
			System.out.println("4. Edit a Book Info");
			System.out.println("5. Delete a Book");
			System.out.println("0. Exit");
			
			System.out.print("\nChoose the Menu(Put the Number) : ");
			int menu = sc.nextInt();
			sc.nextLine();
			
			switch(menu) {
			case 1: bc.listOfBooks(); break;
			case 2: bookSearch(); break;
			case 3: addaBook(); break;
			case 4: editBookInfo();break;
			case 5: bc.deleteBook(inputBookTitle()); break;
			case 0: System.out.println("Thanks for using our system. ^__^");return;
			default: System.out.println("You put the wrong number. Try it again"); break;
			}
			
		}
	}
	
	/**
	 * bookSearch display
	 * Choose the way to search a book
	 * and put the information
	 */
	public void bookSearch() {
		System.out.println("\nChoose the way to search the book\n");
		System.out.println("1. Title(keyword), 2. Category, 3.Author(keyword), 4. Publisher(keyword)");
		System.out.print("Put the Number here >> ");
		int searchBy = sc.nextInt();
		sc.nextLine();
		
		String inputValue = " ";
		
		//Books b = new Books();
		
		/*
		switch(searchBy) {
		case 1: System.out.print("Title(keyword) : ");
				b.setbName(sc.nextLine());
				break;
		case 2: System.out.print("Category(ROMANCE, HISTORY, SCIENCE, FICTION, PHILOSOPHY) : ");
				b.setbCate(sc.nextLine().toUpperCase());
				break;
		case 3: System.out.print("Author : ");
				b.setbAuthor(sc.nextLine());
				break;
		case 4: System.out.print("Publisher : ");
				b.setbPub(sc.nextLine());
				break;
		}
		*/
		

		switch(searchBy) {
		case 1: bc.bookSearch(searchBy, inputBookTitle());
				break;
		case 2: System.out.print("Category(ROMANCE, HISTORY, SCIENCE, FICTION, PHILOSOPHY) : ");
				bc.bookSearch(searchBy, sc.nextLine().toUpperCase());	
				break;
		case 3: System.out.print("Author : ");
				bc.bookSearch(searchBy, sc.nextLine().toLowerCase());
				break;
		case 4: System.out.print("Publisher : ");
				bc.bookSearch(searchBy, sc.nextLine().toLowerCase());
				break;
		default: System.out.println("You put the wrong value. Try it again.");
		// q를 잘못 입력하였는데 왜 출력되지 않을까? 문자는 안됌!
		}
		
	}
	
	/**
	 * put the information for a new book you want to add to the list
	 */
	public void addaBook() {
		String bTitle = inputBookTitle();
		
		System.out.print("Author : ");
		String bAuthor = sc.nextLine().toLowerCase();
		
		System.out.print("Pirce($) : ");
		String bPrice = sc.nextLine();
		
		System.out.print("Category(ROMANCE, HISTORY, SCIENCE, FICTION, PHILOSOPHY) : ");
		String bCate = sc.nextLine().toUpperCase();
		
		System.out.print("Publisher : ");
		String bPub = sc.nextLine().toLowerCase();
		
		bc.addaBook(bTitle, bAuthor, bPrice, bCate, bPub);
	}
	
	/**
	 * edit a book's information
	 */
	public void editBookInfo() {
		String bTitle = inputBookTitle();
		
		System.out.print("New Author : ");
		String bAuthor = sc.nextLine().toLowerCase();
		
		System.out.print("New Price : ");
		String bPrice = sc.nextLine();
		
		System.out.print("New Category(ROMANCE, HISTORY, SCIENCE, FICTION, PHILOSOPHY) : ");
		String bCate = sc.nextLine().toUpperCase();
		
		System.out.print("New Publisher : ");
		String bPub = sc.nextLine().toLowerCase();
		
		bc.editBookInfo(bTitle, bAuthor, bPrice, bCate, bPub);
	}
	
	/**
	 * get book's name from the user
	 * @return return the value user put
	 */
	public String inputBookTitle() {
		System.out.print("Title : ");
		String bTitle = sc.nextLine().toLowerCase();
		
		return bTitle;
	}
	
	
	// Result Output--------------------------------------------------------------
	
	
	/**
	 * display user will see when the request has been successfully completed
	 * @param message details
	 */
	public void displaySuccess(String message) {
		System.out.println("\nYour request has been completed!\n" + message);
	}
	
	/**
	 * display user will see when the request has been failed
	 * @param message details
	 */
	public void displayFail(String message) {
		System.out.println("\nYour request has been failed.\n" + message);
	}
	
	/**
	 * display user will see when there is no data.
	 * @param message details
	 */
	public void displayNoData(String message) {
		System.out.println("\n" + message);
	}
	
	/**
	 * print out the list for the query
	 * @param list
	 */
	public void displayListofBooks(ArrayList<Books> list) {
		
		for(Books b : list) {
			System.out.println("\n" + b);
		}
	}
}
