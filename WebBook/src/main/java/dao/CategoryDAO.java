package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import context.JDBCUtil;
import entity.Category;

public class CategoryDAO {
	public ArrayList<Category> selectAll(){
		ArrayList<Category> ketQua = new ArrayList<Category>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM category";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int catid = rs.getInt("catid");
				String catname = rs.getString("catname");
				String catdescribe = rs.getString("catdescribe");
				
				Category category = new Category(catid, catname, catdescribe);
				ketQua.add(category);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	public Category selectById(Category t) {
		// TODO Auto-generated method stub
		Category ketQua = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM category WHERE catid=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, t.getCatid());

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int catid = rs.getInt("catid");
				String catname = rs.getString("catname");
				String catdescribe = rs.getString("catdescribe");

				ketQua = new Category(catid, catname, catdescribe);
				break;
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
		CategoryDAO cd = new CategoryDAO();
		for(Category c: cd.selectAll()) {
			System.out.println(c);
		}
		
	}
}
