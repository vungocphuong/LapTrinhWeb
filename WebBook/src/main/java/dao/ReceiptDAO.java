package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;

import context.JDBCUtil;
import entity.Receipt;

public class ReceiptDAO {
	public int insert(Receipt t) {
		// TODO Auto-generated method stub
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "INSERT INTO receipt(sid, datecreat, total)"
					+ " VALUES (?,?,?)";

			PreparedStatement st = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, t.getSupplier().getSid());
			st.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
			st.setInt(3, t.getTotal());
			

			// Bước 3: thực thi câu lệnh SQL
			st.executeUpdate();
			
			//lấy ra id order vừa tạo
			ResultSet rs = st.getGeneratedKeys();
			
			if (rs.next()) {
			   return rs.getInt(1); // để lấy được id này thì ở trên nhớ phải thêm 1 đoạn "Statement.RETURN_GENERATED_KEYS"
			}
			rs.close();
			
			// Bước 4:
			//System.out.println("Ban da thuc thi: " + sql);
			//System.out.println("Co " + ketQua + " dong bi thay doi");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			return 0;
		}
		return 0;
	}
	
}
