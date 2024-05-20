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
import entity.ImportedBook;

public class ImportedBookDAO {
	public int insert(ImportedBook t) {
		// TODO Auto-generated method stub
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "INSERT INTO importedbook (rid, bid, quantity, price) "
					+ " VALUES (?,?,?,?)";

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, t.getRid());
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
			String sql = "DELETE from importedbook " + " WHERE bid=?";

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
	public List<ImportedBook> selectByIdOfReceipt(int rid) {
		// TODO Auto-generated method stub
		List<ImportedBook> ketQua = new ArrayList<ImportedBook>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM importedbook WHERE rid=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, rid);

			// Bước 3: thực thi câu lệnh SQL
			//System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int ipid = rs.getInt("ipid");
				int bid = rs.getInt("bid");
				int quantity = rs.getInt("quantity");
				int price = rs.getInt("price");
				
				BookDAO bd = new BookDAO();
				Book book = bd.selectById(bid);
				ImportedBook item = new ImportedBook(ipid, rid, book, quantity, price);
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
}
