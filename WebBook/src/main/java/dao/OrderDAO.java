package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import context.JDBCUtil;
import entity.Book;
import entity.ExportedBook;
import entity.Order;
import entity.User;

public class OrderDAO {
	public int insert(Order t) {
		// TODO Auto-generated method stub
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "INSERT INTO sorder(uid, deliveryaddress, datecreat, total)"
					+ " VALUES (?,?,?,?)";

			PreparedStatement st = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			st.setInt(1, t.getUser().getUid());
			st.setString(2, t.getDeliveryaddress());
			st.setTimestamp(3, new Timestamp(System.currentTimeMillis()));
			st.setInt(4, t.getTotal());
			

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
	public ArrayList<Order> selectByStatusOfOrder(int status,int uid) {
		// TODO Auto-generated method stub
		ArrayList<Order> ketQua = new ArrayList<Order>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM sorder WHERE status=? and uid=? ORDER BY datecreat DESC, oid ASC";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, status);
			st.setInt(2, uid);
			// Bước 3: thực thi câu lệnh SQL
			//System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int oid = rs.getInt("oid");
//				int uid = rs.getInt("uid");
				String deliveryaddress = rs.getString("deliveryaddress");
				Timestamp datecreat = rs.getTimestamp("datecreat");
				int total = rs.getInt("total");
				
				UserDAO ud = new UserDAO();
				User user = ud.selectById(uid);
				ExportedBookDAO itd = new ExportedBookDAO();
				List<ExportedBook> listItem = itd.selectByIdOfOrder(oid);
				
				Order order = new Order();
				order.setOid(oid);
				order.setUser(user);
				order.setDeliveryaddress(deliveryaddress);
				order.setDate_creat(datecreat);
				order.setItems(listItem);
				order.setTotal(total);
				order.setStatus(status);
				ketQua.add(order);
			}
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	public ArrayList<Order> selectByStatusOfOrderAllUser(int status) {
		// TODO Auto-generated method stub
		ArrayList<Order> ketQua = new ArrayList<Order>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM sorder WHERE status=? ORDER BY datecreat DESC, oid ASC";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, status);
			// Bước 3: thực thi câu lệnh SQL
			//System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int oid = rs.getInt("oid");
				int uid = rs.getInt("uid");
				String deliveryaddress = rs.getString("deliveryaddress");
				Timestamp datecreat = rs.getTimestamp("datecreat");
				int total = rs.getInt("total");
				
				UserDAO ud = new UserDAO();
				User user = ud.selectById(uid);
				ExportedBookDAO itd = new ExportedBookDAO();
				List<ExportedBook> listItem = itd.selectByIdOfOrder(oid);
				
				Order order = new Order();
				order.setOid(oid);
				order.setUser(user);
				order.setDeliveryaddress(deliveryaddress);
				order.setDate_creat(datecreat);
				order.setItems(listItem);
				order.setTotal(total);
				order.setStatus(status);
				ketQua.add(order);
			}
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	public int update(Order t) {
		// TODO Auto-generated method stub
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE sorder " + " SET " + " uid=?" + ", deliveryaddress=?" + ", datecreat=?" + ", total=?"
					+ ", status=?" + " WHERE oid=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, t.getUser().getUid());
			st.setString(2, t.getDeliveryaddress());
			st.setTimestamp(3, t.getDate_creat());
			st.setInt(4, t.getTotal());
			st.setInt(5, t.getStatus());
			st.setInt(6, t.getOid());
			// Bước 3: thực thi câu lệnh SQL

			System.out.println(sql);
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
	
	public ArrayList<Order> selectAllOrderOfUser(int uid) {
		// TODO Auto-generated method stub
		ArrayList<Order> ketQua = new ArrayList<Order>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM sorder WHERE uid=? ORDER BY datecreat DESC, oid ASC";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, uid);
			
			// Bước 3: thực thi câu lệnh SQL
			//System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int oid = rs.getInt("oid");
//				int uid = rs.getInt("uid");
				String deliveryaddress = rs.getString("deliveryaddress");
				Timestamp datecreat = rs.getTimestamp("datecreat");
				int total = rs.getInt("total");
				int status = rs.getInt("status");
				UserDAO ud = new UserDAO();
				User user = ud.selectById(uid);
				ExportedBookDAO itd = new ExportedBookDAO();
				List<ExportedBook> listItem = itd.selectByIdOfOrder(oid);
				
				Order order = new Order();
				order.setOid(oid);
				order.setUser(user);
				order.setDeliveryaddress(deliveryaddress);
				order.setDate_creat(datecreat);
				order.setItems(listItem);
				order.setTotal(total);
				order.setStatus(status);
				ketQua.add(order);
			}
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	public ArrayList<Order> selectAllOrderOfUsers() {
		// TODO Auto-generated method stub
		ArrayList<Order> ketQua = new ArrayList<Order>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM sorder ORDER BY datecreat DESC, oid ASC";
			PreparedStatement st = con.prepareStatement(sql);
			
			// Bước 3: thực thi câu lệnh SQL
			//System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int oid = rs.getInt("oid");
				int uid = rs.getInt("uid");
				String deliveryaddress = rs.getString("deliveryaddress");
				Timestamp datecreat = rs.getTimestamp("datecreat");
				int total = rs.getInt("total");
				int status = rs.getInt("status");
				UserDAO ud = new UserDAO();
				User user = ud.selectById(uid);
				ExportedBookDAO itd = new ExportedBookDAO();
				List<ExportedBook> listItem = itd.selectByIdOfOrder(oid);
				
				Order order = new Order();
				order.setOid(oid);
				order.setUser(user);
				order.setDeliveryaddress(deliveryaddress);
				order.setDate_creat(datecreat);
				order.setItems(listItem);
				order.setTotal(total);
				order.setStatus(status);
				ketQua.add(order);
			}
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	public Order select(int oid) { // lấy order theo id
		// TODO Auto-generated method stub
		Order order = new Order();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM sorder WHERE oid=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, oid);
			
			// Bước 3: thực thi câu lệnh SQL
			//System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int uid = rs.getInt("uid");
				String deliveryaddress = rs.getString("deliveryaddress");
				Timestamp datecreat = rs.getTimestamp("datecreat");
				int total = rs.getInt("total");
				int status = rs.getInt("status");
				UserDAO ud = new UserDAO();
				User user = ud.selectById(uid);
				ExportedBookDAO itd = new ExportedBookDAO();
				List<ExportedBook> listItem = itd.selectByIdOfOrder(oid);
				
				order.setOid(oid);
				order.setUser(user);
				order.setDeliveryaddress(deliveryaddress);
				order.setDate_creat(datecreat);
				order.setItems(listItem);
				order.setTotal(total);
				order.setStatus(status);
			}
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return order;
	}
	public int getTotalOrder() {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT count(*) FROM sorder";
			PreparedStatement st = con.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public int getTotalOrderByStatus(int status) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT count(*) FROM sorder WHERE status=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1,status);
			System.out.println(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public int sumRevenueOfOrder() {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT SUM(total) AS total_sum FROM sorder WHERE status=1";
			PreparedStatement st = con.prepareStatement(sql);
			System.out.println(sql);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				return rs.getInt(1);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return 0;
	}
	public ArrayList<Order> statisticRevenueByTime(String s,String e){
		ArrayList<Order> ketQua = new ArrayList<Order>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT DATE(datecreat) AS order_date, SUM(total) AS total_sum\r\n"
					+ "FROM sorder\r\n"
					+ "WHERE datecreat >= ? AND datecreat <= ? AND status=1\r\n"
					+ "GROUP BY DATE(datecreat);";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,s);
			st.setString(2,e);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				Timestamp datecreat = rs.getTimestamp(1);
				int total = rs.getInt(2);
				Order order = new Order();
				order.setDate_creat(datecreat);
				order.setTotal(total);
				ketQua.add(order);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return ketQua;
	}
	public ArrayList<Order> detailStatisticRevenueByTime(String sdate){
		ArrayList<Order> ketQua = new ArrayList<Order>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT *\r\n"
					+ "FROM sorder\r\n"
					+ "WHERE DATE(datecreat) = ? AND status=1";
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,sdate);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				int oid = rs.getInt("oid");
				int uid = rs.getInt("uid");
				String deliveryaddress = rs.getString("deliveryaddress");
				Timestamp datecreat = rs.getTimestamp("datecreat");
				int total = rs.getInt("total");
				int status = rs.getInt("status");
				UserDAO ud = new UserDAO();
				User user = ud.selectById(uid);
				ExportedBookDAO itd = new ExportedBookDAO();
				List<ExportedBook> listItem = itd.selectByIdOfOrder(oid);
				
				Order order = new Order();
				order.setOid(oid);
				order.setUser(user);
				order.setDeliveryaddress(deliveryaddress);
				order.setDate_creat(datecreat);
				order.setItems(listItem);
				order.setTotal(total);
				order.setStatus(status);
				ketQua.add(order);
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return ketQua;
	}
	public ArrayList<Order> detailStatisticBookByRevenue(String start,String end,int bid){
		ArrayList<Order> ketQua = new ArrayList<Order>();
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT * FROM sorder	WHERE datecreat >= ? AND datecreat <= ? AND status=1";//status IN (0, 1)
			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1,start);
			st.setString(2,end);
			ResultSet rs = st.executeQuery();
			while(rs.next()) {
				int oid = rs.getInt("oid");
				int uid = rs.getInt("uid");
				String deliveryaddress = rs.getString("deliveryaddress");
				Timestamp datecreat = rs.getTimestamp("datecreat");
				//int total = rs.getInt("total");
				int status = rs.getInt("status");
				UserDAO ud = new UserDAO();
				User user = ud.selectById(uid);
				ExportedBookDAO itd = new ExportedBookDAO();
				List<ExportedBook> listItem = itd.selectByIdOfOrderAndBook(oid, bid);
				int total = 0;
				if(listItem.size() > 0) {
					total = listItem.get(0).getPrice() * listItem.get(0).getQuantity();
				}
				
				Order order = new Order();
				order.setOid(oid);
				order.setUser(user);
				order.setDeliveryaddress(deliveryaddress);
				order.setDate_creat(datecreat);
				order.setItems(listItem);
				order.setTotal(total);
				order.setStatus(status);
				if(listItem.size() > 0) {
					ketQua.add(order);
				}
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		return ketQua;
	}
	public ArrayList<Order> selectOrderOfClientStatic(int uid, String start, String end) {
		// TODO Auto-generated method stub
		ArrayList<Order> ketQua = new ArrayList<Order>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM sorder WHERE uid=? AND datecreat BETWEEN ? AND ? AND status=1";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, uid);
			st.setString(2,start);
			st.setString(3, end);
			// Bước 3: thực thi câu lệnh SQL
			//System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int oid = rs.getInt("oid");
//				int uid = rs.getInt("uid");
				String deliveryaddress = rs.getString("deliveryaddress");
				Timestamp datecreat = rs.getTimestamp("datecreat");
				int total = rs.getInt("total");
				int status = rs.getInt("status");
				UserDAO ud = new UserDAO();
				User user = ud.selectById(uid);
				ExportedBookDAO itd = new ExportedBookDAO();
				List<ExportedBook> listItem = itd.selectByIdOfOrder(oid);
				
				Order order = new Order();
				order.setOid(oid);
				order.setUser(user);
				order.setDeliveryaddress(deliveryaddress);
				order.setDate_creat(datecreat);
				order.setItems(listItem);
				order.setTotal(total);
				order.setStatus(status);
				ketQua.add(order);
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
		Order ord = new Order();
		UserDAO ud = new UserDAO();
//		User u = ud.selectById(1);
//		ord.setUser(u);
//		ord.setDeliveryaddress("HN");
//		ord.setTotal(10000);
		OrderDAO od = new OrderDAO();
//		od.insert(ord);
//		ArrayList<Order> lst = od.selectByStatusOfOrder(0, 1);
//		for(Order i:lst) {
//			System.out.println(i);
//		}
		/*
		 * ArrayList<Order> l = new ArrayList<Order>(); l =
		 * od.selectByStatusOfOrderAllUser(0); for(Order o: l) { System.out.println(o);
		 * }
		 */
//		System.out.println(od.getTotalOrder());
//		ArrayList<Order> l = od.statisticRevenueByTime("2023-11-22", "2023-12-02");
//		for(Order i:l) {
//			System.out.println(i);
//		}
		//System.out.println(od.sumRevenueOfOrder());
		for(Order i:od.selectOrderOfClientStatic(1,"2023-11-10", "2023-12-10")) {
			System.out.println(i);
		}
	}
}
