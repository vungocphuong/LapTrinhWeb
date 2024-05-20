package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import context.JDBCUtil;
import entity.Comment;

public class CommentDAO {
	public int insert(Comment t) {
		// TODO Auto-generated method stub
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "INSERT INTO comment (fromuser, tobook, numstar, content,time) "
					+ " VALUES (?,?,?,?,?)";

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, t.getFromuser());
			st.setInt(2, t.getTobook());
			st.setInt(3, t.getNumstar());
			st.setString(4, t.getContent());
			st.setTimestamp(5, new Timestamp(System.currentTimeMillis()));
			

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
	public ArrayList<Comment> selectByIdBook(int bid) {
		// TODO Auto-generated method stub
		ArrayList<Comment> ketQua = new ArrayList<Comment>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM comment WHERE tobook=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, bid);

			// Bước 3: thực thi câu lệnh SQL
			//	System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int cmtid = rs.getInt("cmtid");
				int fromuser = rs.getInt("fromuser");
				int tobook = bid;
				int numstar = rs.getInt("numstar");
				String content = rs.getString("content");
				Timestamp time = rs.getTimestamp("time");
				
				UserDAO ud = new UserDAO();
				String uname = ud.selectById(fromuser).getUsername();
				
				Comment cmt = new Comment(cmtid, fromuser, tobook, numstar, content, time);
				cmt.setUname(uname);
				ketQua.add(cmt);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	public int delete(Comment t) {
		// TODO Auto-generated method stub
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from comment " + " WHERE tobook=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, t.getTobook());

			// Bước 3: thực thi câu lệnh SQL
//			System.out.println(sql);
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
	
	public static void main(String[] args) {
		CommentDAO cmtd = new CommentDAO();
//		Comment cmt = new Comment(0, 1, 2, 4, "rất hay nhé", null);
//		cmtd.insert(cmt);
//		ArrayList<Comment> list = new ArrayList<Comment>();
//		list = cmtd.selectByIdBook(3);
//		for (Comment c : list) { 
//			System.out.println(c);
//		}
	}
}
