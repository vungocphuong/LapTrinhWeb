package entity;

import java.sql.Timestamp;
import java.util.List;

import entity.ExportedBook;
import entity.User;

public class Order {
	private int oid;
	private User user;
	private String deliveryaddress;
	private Timestamp date_creat;
	private List<ExportedBook> items;
	private int total;
	private int status;
	
	public Order() {
		this.status = 0;
	}

	public int getOid() {
		return oid;
	}

	public void setOid(int oid) {
		this.oid = oid;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public String getDeliveryaddress() {
		return deliveryaddress;
	}

	public void setDeliveryaddress(String deliveryaddress) {
		this.deliveryaddress = deliveryaddress;
	}

	public Timestamp getDate_creat() {
		return date_creat;
	}

	public void setDate_creat(Timestamp date_creat) {
		this.date_creat = date_creat;
	}

	public List<ExportedBook> getItems() {
		return items;
	}

	public void setItems(List<ExportedBook> items) {
		this.items = items;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Order [oid=" + oid + ", user=" + user + ", deliveryaddress=" + deliveryaddress + ", date_creat="
				+ date_creat + ", items=" + items + ", total=" + total + ", status=" + status + "]";
	}
	
	
}
