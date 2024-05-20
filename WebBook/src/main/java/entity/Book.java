package entity;

import java.sql.Date;

import entity.Category;

public class Book {
	private int bid;
	private String title, author;
	private Category category;
	private Date rlsdate;
	private int page,sold,price,revenue;
	private String descr,coverurl;
	public Book() {
	}
	
	public Book(int bid, String title, String author, Category category, Date rlsdate, int page, int sold, int price,
			String descr, String coverurl) {
		super();
		this.bid = bid;
		this.title = title;
		this.author = author;
		this.category = category;
		this.rlsdate = rlsdate;
		this.page = page;
		this.sold = sold;
		this.price = price;
		this.descr = descr;
		this.coverurl = coverurl;
	}

	public int getBid() {
		return bid;
	}

	public void setBid(int bid) {
		this.bid = bid;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}

	public Date getRlsdate() {
		return rlsdate;
	}

	public void setRlsdate(Date rlsdate) {
		this.rlsdate = rlsdate;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getSold() {
		return sold;
	}

	public void setSold(int sold) {
		this.sold = sold;
	}

	public int getPrice() {
		return price;
	}

	public void setPrice(int price) {
		this.price = price;
	}

	public String getDescr() {
		return descr;
	}

	public void setDescr(String descr) {
		this.descr = descr;
	}

	public String getCoverurl() {
		return coverurl;
	}

	public void setCoverurl(String coverurl) {
		this.coverurl = coverurl;
	}
	
	public int getRevenue() {
		return revenue;
	}

	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}

	@Override
	public String toString() {
		return "Book [bid=" + bid + ", title=" + title + ", author=" + author + ", category=" + category + ", rlsdate="
				+ rlsdate + ", page=" + page + ", sold=" + sold + ", price=" + price + ", descr=" + descr
				+ ", coverurl=" + coverurl + "]";
	}
}
