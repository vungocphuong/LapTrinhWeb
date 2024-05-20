package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import context.JDBCUtil;
import entity.Supplier;

public class SupplierDAO {
	public ArrayList<Supplier> searchSupplier(String txtSearch){
		ArrayList<Supplier> ketQua = new ArrayList<Supplier>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM supplier WHERE name like ?";
			PreparedStatement st = con.prepareStatement(sql);
			// Bước 3: thực thi câu lệnh SQL
			st.setString(1,"%"+txtSearch+"%");
			ResultSet rs = st.executeQuery();
			while (rs.next()) {
				
				int sid = rs.getInt("sid");
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				
				Supplier supplier =  new Supplier(sid, name, address, phone);
				ketQua.add(supplier);
				
			}
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
		
	}
	public Supplier selectById(int sid) {
		Supplier ketQua = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM supplier WHERE sid=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, sid);
			
			
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				String name = rs.getString("name");
				String address = rs.getString("address");
				String phone = rs.getString("phone");
				ketQua = new Supplier(sid, name, address, phone);
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
		SupplierDAO sd = new SupplierDAO();
		/*
		 * for(Supplier i : sd.searchSupplier("n")) { System.out.println(i); }
		 */
		System.out.println(sd.selectById(1));
	}
}
