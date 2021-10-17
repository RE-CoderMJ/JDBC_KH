package model.vo;

import java.sql.Date;

public class Books {

	private int bookNo;
	private String bTitle;
	private String bAuthor;
	private int bPrice;
	private String bCate;
	private String bPub;
	private Date wDate;
	
	public Books () {}

	public Books(int bookNo, String bTitle, String bAuthor, int bPrice, String bCate, String bPub, Date wDate) {
		super();
		this.bookNo = bookNo;
		this.bTitle = bTitle;
		this.bAuthor = bAuthor;
		this.bPrice = bPrice;
		this.bCate = bCate;
		this.bPub = bPub;
		this.wDate = wDate;
	}
	
	public Books(String bTitle, String bAuthor, int bPrice, String bCate, String bPub) {
		super();
		this.bTitle = bTitle;
		this.bAuthor = bAuthor;
		this.bPrice = bPrice;
		this.bCate = bCate;
		this.bPub = bPub;
	}
	

	public int getBookNo() {
		return bookNo;
	}

	public void setBookNo(int bookNo) {
		this.bookNo = bookNo;
	}

	public String getbTitle() {
		return bTitle;
	}

	public void setbName(String bTitle) {
		this.bTitle = bTitle;
	}

	public String getbAuthor() {
		return bAuthor;
	}

	public void setbAuthor(String bAuthor) {
		this.bAuthor = bAuthor;
	}

	public int getbPrice() {
		return bPrice;
	}

	public void setbPrice(int bPrice) {
		this.bPrice = bPrice;
	}

	public String getbCate() {
		return bCate;
	}

	public void setbCate(String bCate) {
		this.bCate = bCate;
	}

	public String getbPub() {
		return bPub;
	}

	public void setbPub(String bPub) {
		this.bPub = bPub;
	}

	public Date getwDate() {
		return wDate;
	}

	public void setwDate(Date wDate) {
		this.wDate = wDate;
	}

	@Override
	public String toString() {
		return "Books [bookNo=" + bookNo + ", bTitle=" + bTitle + ", bAuthor=" + bAuthor + ", bPrice=" + bPrice
				+ ", bCate=" + bCate + ", bPub=" + bPub + ", wDate=" + wDate + "]";
	}
	
	
	
	
}
