package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import context.JDBCUtil;
import entity.Book;
import entity.ExportedBook;

public class ExportedBookDAO {
	public int insert(ExportedBook t) {
		// TODO Auto-generated method stub
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "INSERT INTO exportedbook (oid, bid, quantity, price) "
					+ " VALUES (?,?,?,?)";

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, t.getOid());
			st.setInt(2, t.getBook().getBid());
			st.setInt(3, t.getQuantity());
			st.setInt(4, t.getPrice());
			

			// Bước 3: thực thi câu lệnh SQL
			ketQua = st.executeUpdate();
			
			// Bước 4:
			//System.out.println("Ban da thuc thi: " + sql);
			//System.out.println("Co " + ketQua + " dong bi thay doi");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	public int delete(int bid) {
		// TODO Auto-generated method stub
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from exportedbook " + " WHERE bid=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, bid);

			// Bước 3: thực thi câu lệnh SQL
			//System.out.println(sql);
			ketQua = st.executeUpdate();

			// Bước 4:
			//System.out.println("Bạn đã thực thi: " + sql);
			//System.out.println("Có " + ketQua + " dòng bị thay đổi!");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	public List<ExportedBook> selectByIdOfOrder(int oid) {
		// TODO Auto-generated method stub
		List<ExportedBook> ketQua = new ArrayList<ExportedBook>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM exportedbook WHERE oid=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, oid);

			// Bước 3: thực thi câu lệnh SQL
			//System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int iid = rs.getInt("iid");
				int bid = rs.getInt("bid");
				int quantity = rs.getInt("quantity");
				int price = rs.getInt("price");
				
				BookDAO bd = new BookDAO();
				Book book = bd.selectById(bid);
				ExportedBook item = new ExportedBook(iid, oid, book, quantity, price);
				ketQua.add(item);
			}
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	public List<ExportedBook> selectByIdOfOrderAndBook(int oid,int bid) {
		// TODO Auto-generated method stub
		List<ExportedBook> ketQua = new ArrayList<ExportedBook>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM exportedbook WHERE oid=? AND bid=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, oid);
			st.setInt(2, bid);
			// Bước 3: thực thi câu lệnh SQL
			//System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int iid = rs.getInt("iid");
				int quantity = rs.getInt("quantity");
				int price = rs.getInt("price");
				
				BookDAO bd = new BookDAO();
				Book book = bd.selectById(bid);
				ExportedBook item = new ExportedBook(iid, oid, book, quantity, price);
				ketQua.add(item);
			}
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	public static void main(String[] args) {
//		Book b = new Book(2, null, null, null, null, 0, 0, 0, null, null);
//		ExportedBook i = new ExportedBook();
//		i.setBook(b);
//		i.setPrice(100);
//		i.setQuantity(1);
		ExportedBookDAO id = new ExportedBookDAO();
		List<ExportedBook> l = id.selectByIdOfOrderAndBook(3, 3);
		
		for(ExportedBook i:l) {
			System.out.println(i);
		}
	}
}
