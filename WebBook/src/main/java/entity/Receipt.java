package entity;

import java.sql.Timestamp;
import java.util.List;

public class Receipt {
	private int rid;
	private Supplier supplier;
	private Timestamp date_creat;
	private List<ImportedBook> items;
	private int total;
	
	public Receipt() {
		super();
	}

	public Receipt(int rid, Supplier supplier, Timestamp date_creat, List<ImportedBook> items, int total) {
		super();
		this.rid = rid;
		this.supplier = supplier;
		this.date_creat = date_creat;
		this.items = items;
		this.total = total;
	}

	public int getRid() {
		return rid;
	}

	public void setRid(int rid) {
		this.rid = rid;
	}

	public Supplier getSupplier() {
		return supplier;
	}

	public void setSupplier(Supplier supplier) {
		this.supplier = supplier;
	}

	public Timestamp getDate_creat() {
		return date_creat;
	}

	public void setDate_creat(Timestamp date_creat) {
		this.date_creat = date_creat;
	}

	public List<ImportedBook> getItems() {
		return items;
	}

	public void setItems(List<ImportedBook> items) {
		this.items = items;
	}

	public int getTotal() {
		return total;
	}

	public void setTotal(int total) {
		this.total = total;
	}

	@Override
	public String toString() {
		return "Receipt [rid=" + rid + ", supplier=" + supplier + ", date_creat=" + date_creat + ", items=" + items
				+ ", total=" + total + "]";
	}
	
	
}
