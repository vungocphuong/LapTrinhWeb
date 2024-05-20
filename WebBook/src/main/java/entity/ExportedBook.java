package entity;

import entity.Book;

public class ExportedBook {
	private int iid;
	private int oid;
	private Book book;
	private int quantity;
	private int price;
	
	public ExportedBook() {
		super();
	}

	public ExportedBook(int iid, Book book, int quantity, int price) {
		super();
		this.iid = iid;
		this.book = book;
		this.quantity = quantity;
		this.price = price;
	}
	
	

	public ExportedBook(int iid, int oid, Book book, int quantity, int price) {
		super();
		this.iid = iid;
		this.oid = oid;
		this.book = book;
		this.quantity = quantity;
		this.price = price;
	}

	public int getIid() {
		return iid;
	}

	public void setIid(int iid) {
		this.iid = iid;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public Book getBook() {
		return book;
	}

	public void setBook(Book book) {
		this.book = book;
	}

	public int getQuantity() {
		return quantity;
	}

	public void setQuantity(int quantity) {
		this.quantity = quantity;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	@Override
	public String toString() {
		return "ExportedBook [iid=" + iid + ", oid=" + oid + ", book=" + book + ", quantity=" + quantity + ", price="
				+ price + "]";
	}

	
	
	
	
}
