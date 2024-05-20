package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import context.JDBCUtil;
import entity.User;

public class UserDAO {
	public User selectById(int t) {
		User ketQua = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM user WHERE uid=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, t);
			
			
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int uid = t;
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				Date dob = rs.getDate("dob");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				boolean isadmin = rs.getBoolean("isadmin");
				String verificationCode = rs.getString("verificationCode");
				Date authenticationCodeLifetime = rs.getDate("authenticationCodeLifetime");
				boolean authenticationStatus = rs.getBoolean("authenticationStatus");
				String photoPath = rs.getString("photoPath");
				
				ketQua = new User(uid, username, password, email, name, gender, phone, address, verificationCode, photoPath, isadmin, authenticationStatus, dob, authenticationCodeLifetime);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	public User selectByUserName(String username) {
		User ketQua = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM user WHERE username=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, username);
			
			
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int uid = rs.getInt("uid");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				Date dob = rs.getDate("dob");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				boolean isadmin = rs.getBoolean("isadmin");
				String verificationCode = rs.getString("verificationCode");
				Date authenticationCodeLifetime = rs.getDate("authenticationCodeLifetime");
				boolean authenticationStatus = rs.getBoolean("authenticationStatus");
				String photoPath = rs.getString("photoPath");
				ketQua = new User(uid, username, password, email, name, gender, phone, address, verificationCode, photoPath, isadmin, authenticationStatus, dob, authenticationCodeLifetime);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	public User selectByUserNameAndPassWord(User t) {
		// TODO Auto-generated method stub
		User ketQua = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM user WHERE username=? and password=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getUsername());
			st.setString(2, t.getPassword());
			
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int uid = rs.getInt("uid");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				Date dob = rs.getDate("dob");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				boolean isadmin = rs.getBoolean("isadmin");
				String verificationCode = rs.getString("verificationCode");
				Date authenticationCodeLifetime = rs.getDate("authenticationCodeLifetime");
				boolean authenticationStatus = rs.getBoolean("authenticationStatus");
				String photoPath = rs.getString("photoPath");
				ketQua = new User(uid, username, password, email, name, gender, phone, address, verificationCode, photoPath, isadmin, authenticationStatus, dob, authenticationCodeLifetime);
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	
	public int insert(User t) {
		// TODO Auto-generated method stub
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "INSERT INTO user (username, password, email, name, gender, dob, phone, address) "
					+ " VALUES (?,?,?,?,?,?,?,?)";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getUsername());
			st.setString(2, t.getPassword());
			st.setString(3, t.getEmail());
			st.setString(4, t.getName());
			st.setString(5, t.getGender());
			st.setDate(6, t.getDob());
			st.setString(7, t.getPhone());
			st.setString(8, t.getAddress());
			

			// Bước 3: thực thi câu lệnh SQL
			ketQua = st.executeUpdate();

			// Bước 4:
			System.out.println("Ban da thuc thi: " + sql);
			System.out.println("Co " + ketQua + " dong bi thay doi");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	
	public boolean checkUsername(String username) {
		// TODO Auto-generated method stub
		boolean ketQua = false;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM user WHERE username=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, username);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				ketQua= true;
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	
	public boolean checkUserName(String username) {
		boolean ketQua = false;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM user WHERE username=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, username);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				ketQua = true;
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	public int updateVerifyInformation(User t) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE user " + " SET " + " verificationCode=?" + ", authenticationCodeLifetime=?" + ", authenticationStatus=?" +  " WHERE username=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getVerificationCode());
			st.setDate(2, t.getAuthenticationCodeLifetime());
			st.setBoolean(3, t.isAuthenticationStatus());
			st.setString(4, t.getUsername());
			// Bước 3: thực thi câu lệnh SQL

			System.out.println(sql);
			ketQua = st.executeUpdate();

			// Bước 4:
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketQua + " dòng bị thay đổi!");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	public int update(User u) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE user " + " SET " + " name=?" + ", gender=?" + ", dob=?" + ", address=?"
					+ ", phone=?" + ", photoPath=?" + " WHERE uid=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, u.getName());
			st.setString(2, u.getGender());
			st.setDate(3, u.getDob());
			st.setString(4, u.getAddress());
			st.setString(5, u.getPhone());
			st.setString(6, u.getPhotoPath());
			st.setInt(7, u.getUid());
			
			// Bước 3: thực thi câu lệnh SQL

			System.out.println(sql);
			ketQua = st.executeUpdate();

			// Bước 4:
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketQua + " dòng bị thay đổi!");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	public int changePass(User u) {
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE user " + " SET " + " password=?" + " WHERE uid=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, u.getPassword());
			st.setInt(2, u.getUid());
			
			// Bước 3: thực thi câu lệnh SQL

			System.out.println(sql);
			ketQua = st.executeUpdate();

			// Bước 4:
			System.out.println("Bạn đã thực thi: " + sql);
			System.out.println("Có " + ketQua + " dòng bị thay đổi!");

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	public ArrayList<User> selectAllAccountOfClient(){
		ArrayList<User> ketQua = new ArrayList<User>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM user WHERE isadmin=0";
			PreparedStatement st = con.prepareStatement(sql);
			
			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int uid = rs.getInt("uid");
				String username = rs.getString("username");
				String password = rs.getString("password");
				String email = rs.getString("email");
				String name = rs.getString("name");
				String gender = rs.getString("gender");
				Date dob = rs.getDate("dob");
				String phone = rs.getString("phone");
				String address = rs.getString("address");
				boolean isadmin = rs.getBoolean("isadmin");
				String verificationCode = rs.getString("verificationCode");
				Date authenticationCodeLifetime = rs.getDate("authenticationCodeLifetime");
				boolean authenticationStatus = rs.getBoolean("authenticationStatus");
				String photoPath = rs.getString("photoPath");
				ketQua.add(new User(uid, username, password, email, name, gender, phone, address, verificationCode, photoPath, isadmin, authenticationStatus, dob, authenticationCodeLifetime));
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
		UserDAO ud = new UserDAO();
//		User u = new User();
//		u.setUsername("vnpk3");
//		u.setPassword("123");
//		u.setEmail("vnp@gmail.com");
//		u.setName("Vu Dai Gia");
//		u.setGender("Nam");
//		u.setDob(Date.valueOf("2002-01-02"));
//		u.setPhone("099988888");
//		u.setAddress("Hoa Binh");
//		
//		ud.insert(u);
//	    System.out.println(ud.selectByUserName("phuong"));
//		User u = new User();
//		u.setUid(1);
//		u.setName("vu ngoc phuong");
//		u.setGender("Nam");
//		u.setDob(Date.valueOf("2002-01-02"));
//		u.setAddress("Hoa Binh");
//		u.setPhone("08923459387");
//		u.setPhotoPath("1.jgp");
//		ud.update(u);
		for (User i: ud.selectAllAccountOfClient()) {
			System.out.println(i);
		}
	}
}
