package entity;

public class ImportedBook {
	private int ipid;
	private int rid;
	private Book book;
	private int quantity;
	private int price;
	
	public ImportedBook() {
		super();
	}

	public ImportedBook(int ipid, int rid, Book book, int quantity, int price) {
		super();
		this.ipid = ipid;
		this.rid = rid;
		this.book = book;
		this.quantity = quantity;
		this.price = price;
	}

	public int getIpid() {
		return ipid;
	}

	public void setIpid(int ipid) {
		this.ipid = ipid;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
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
		return "ImportedBook [ipid=" + ipid + ", rid=" + rid + ", book=" + book + ", quantity=" + quantity + ", price="
				+ price + "]";
	}
	
	
}
