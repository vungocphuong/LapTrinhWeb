package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import context.JDBCUtil;
import entity.ClientStatistic;

public class ClientStatisticDAO {
	public ArrayList<ClientStatistic> statisticClient(String start, String end){
		ArrayList<ClientStatistic> kq = new ArrayList<ClientStatistic>();
		
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT \r\n"
					+ "    u.uid, u.name, address, phone, email, SUM(o.total) AS total\r\n"
					+ "FROM \r\n"
					+ "    user u\r\n"
					+ "JOIN \r\n"
					+ "    sorder o ON u.uid = o.uid  \r\n"
					+ "WHERE\r\n"
					+ "    o.datecreat BETWEEN ? AND ? AND o.status = 1\r\n"
					+ "GROUP BY\r\n"
					+ "    u.uid, u.username  \r\n"
					+ "ORDER BY \r\n"
					+ "    total DESC";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			st.setString(1,start);
			st.setString(2,end);
			
			System.out.println(sql);
			
			ResultSet rs = st.executeQuery();
			
			while(rs.next()) {
				int uid = rs.getInt(1);
				String name = rs.getString(2);
				String address = rs.getString(3);
				String phone = rs.getString(4);
				String email = rs.getString(5);
				int revenue = rs.getInt(6);
				
				ClientStatistic u = new ClientStatistic(uid, "", "", email, name, "", phone, address, "", "", false, false, null, null, revenue);
				kq.add(u);
			}
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return kq;
	}
	public static void main(String[] args) {
		ClientStatisticDAO usd = new ClientStatisticDAO();
		ArrayList<ClientStatistic> lst = usd.statisticClient("2023-08-01", "2023-12-03");
		for(ClientStatistic i:lst) {
			System.out.println(i);
			
		}
	}
}
