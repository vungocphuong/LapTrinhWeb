package dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import context.JDBCUtil;
import entity.Book;
import entity.Category;

public class BookDAO {
	public ArrayList<Book> selectAll(){
		ArrayList<Book> ketQua = new ArrayList<Book>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM book";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				
				int bid = rs.getInt("bid");
				String title = rs.getString("title");
				String author = rs.getString("author");
				int catid = rs.getInt("catid");
				Date rlsdate = rs.getDate("rlsdate");
				int page = rs.getInt("page");
				int sold = rs.getInt("sold");
				int price = rs.getInt("price"); 
				String descr = rs.getString("descr");
				String coverurl = rs.getString("coverurl");
				
				Category c = new Category(catid, "", "");
				CategoryDAO cd = new CategoryDAO();
				Category category = cd.selectById(c);
				Book book =  new Book(bid, title, author, category, rlsdate, page, sold, price, descr, coverurl);
				ketQua.add(book);
				
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketQua;
	}
	public Book selectById(int id){
		Book ketQua = null;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM book WHERE bid=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int bid = rs.getInt("bid");
				String title = rs.getString("title");
				String author = rs.getString("author");
				int catid = rs.getInt("catid");
				Date rlsdate = rs.getDate("rlsdate");
				int page = rs.getInt("page");
				int sold = rs.getInt("sold");
				int price = rs.getInt("price");
				String descr = rs.getString("descr");
				String coverurl = rs.getString("coverurl");
				
				Category c = new Category(catid, "", "");
				CategoryDAO cd = new CategoryDAO();
				Category category = cd.selectById(c);
				Book book =  new Book(bid, title, author, category, rlsdate, page, sold, price, descr, coverurl);
				ketQua = book;
			}
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	
	}
	public ArrayList<Book> selectByIdOfCat(int id) {
		// TODO Auto-generated method stub
		ArrayList<Book> ketQua = new ArrayList<Book>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM book WHERE catid=?";
			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, id);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int bid = rs.getInt("bid");
				String title = rs.getString("title");
				String author = rs.getString("author");
				int catid = rs.getInt("catid");
				Date rlsdate = rs.getDate("rlsdate");
				int page = rs.getInt("page");
				int sold = rs.getInt("sold");
				int price = rs.getInt("price");
				String descr = rs.getString("descr");
				String coverurl = rs.getString("coverurl");
				
				Category c = new Category(catid, "", "");
				CategoryDAO cd = new CategoryDAO();
				Category category = cd.selectById(c);
				Book book =  new Book(bid, title, author, category, rlsdate, page, sold, price, descr, coverurl);
				ketQua.add(book);
			}
			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return ketQua;
	}
	
	public int update(Book t) {
		// TODO Auto-generated method stub
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "UPDATE book " + " SET " + " title=?" + ", author=?" + ", catid=?" + ", rlsdate=?"
					+ ", page=?" + ", sold=?" + ", price=?" + ", descr=?" + ", coverurl=?" + " WHERE bid=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setString(1, t.getTitle());
			st.setString(2, t.getAuthor());
			st.setInt(3, t.getCategory().getCatid());
			st.setDate(4, t.getRlsdate());
			st.setInt(5, t.getPage());
			st.setInt(6, t.getSold());
			st.setInt(7, t.getPrice());
			st.setString(8, t.getDescr());
			st.setString(9, t.getCoverurl());
			st.setInt(10, t.getBid());
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
	
	public int delete(int bid) {
		// TODO Auto-generated method stub
		int ketQua = 0;
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "DELETE from book " + " WHERE bid=?";

			PreparedStatement st = con.prepareStatement(sql);
			st.setInt(1, bid);

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
	
	public int insert(Book t) {
		// TODO Auto-generated method stub
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "INSERT INTO book (title, author, catid, rlsdate, page, sold, price, descr, coverurl) "
					+ " VALUES (?,?,?,?,?,?,?,?,?)";
			PreparedStatement st = con.prepareStatement(sql,Statement.RETURN_GENERATED_KEYS);
			
			st.setString(1, t.getTitle());
			st.setString(2, t.getAuthor());
			st.setInt(3, t.getCategory().getCatid());
			st.setDate(4, t.getRlsdate());
			st.setInt(5, t.getPage());
			st.setInt(6, t.getSold());
			st.setInt(7, t.getPrice());
			st.setString(8, t.getDescr());
			st.setString(9, t.getCoverurl());

			// Bước 3: thực thi câu lệnh SQL
			st.executeUpdate();
			ResultSet rs = st.getGeneratedKeys();
			// Bước 4:
			if (rs.next()) {
				   return rs.getInt(1); // để lấy được id này thì ở trên nhớ phải thêm 1 đoạn "Statement.RETURN_GENERATED_KEYS"
			}
			rs.close();

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return 0; 	
	}
	public ArrayList<Book> pagingBook(int index){
		ArrayList<Book> ketQua = new ArrayList<Book>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM book ORDER BY bid LIMIT 10 OFFSET ?";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			st.setInt(1, (index-1)*10);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				
				int bid = rs.getInt("bid");
				String title = rs.getString("title");
				String author = rs.getString("author");
				int catid = rs.getInt("catid");
				Date rlsdate = rs.getDate("rlsdate");
				int page = rs.getInt("page");
				int sold = rs.getInt("sold");
				int price = rs.getInt("price"); 
				String descr = rs.getString("descr");
				String coverurl = rs.getString("coverurl");
				
				Category c = new Category(catid, "", "");
				CategoryDAO cd = new CategoryDAO();
				Category category = cd.selectById(c);
				Book book =  new Book(bid, title, author, category, rlsdate, page, sold, price, descr, coverurl);
				ketQua.add(book);
				
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}
	public ArrayList<Book> pagingBookByCat(int index,int catid){
		ArrayList<Book> ketQua = new ArrayList<Book>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM book WHERE catid=? ORDER BY bid LIMIT 10 OFFSET ?";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			st.setInt(1, catid);
			st.setInt(2, (index-1)*10);
			
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				
				int bid = rs.getInt("bid");
				String title = rs.getString("title");
				String author = rs.getString("author");
				Date rlsdate = rs.getDate("rlsdate");
				int page = rs.getInt("page");
				int sold = rs.getInt("sold");
				int price = rs.getInt("price"); 
				String descr = rs.getString("descr");
				String coverurl = rs.getString("coverurl");
				
				Category c = new Category(catid, "", "");
				CategoryDAO cd = new CategoryDAO();
				Category category = cd.selectById(c);
				Book book =  new Book(bid, title, author, category, rlsdate, page, sold, price, descr, coverurl);
				ketQua.add(book);
				
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}
	public ArrayList<Book> pagingBookByIncreasingPrice(int index){
		ArrayList<Book> ketQua = new ArrayList<Book>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM book ORDER BY price LIMIT 10 OFFSET ?";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			st.setInt(1, (index-1)*10);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				
				int bid = rs.getInt("bid");
				String title = rs.getString("title");
				String author = rs.getString("author");
				int catid = rs.getInt("catid");
				Date rlsdate = rs.getDate("rlsdate");
				int page = rs.getInt("page");
				int sold = rs.getInt("sold");
				int price = rs.getInt("price"); 
				String descr = rs.getString("descr");
				String coverurl = rs.getString("coverurl");
				
				Category c = new Category(catid, "", "");
				CategoryDAO cd = new CategoryDAO();
				Category category = cd.selectById(c);
				Book book =  new Book(bid, title, author, category, rlsdate, page, sold, price, descr, coverurl);
				ketQua.add(book);
				
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}
	public ArrayList<Book> pagingBookByDecreasingPrice(int index){
		ArrayList<Book> ketQua = new ArrayList<Book>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM book ORDER BY price DESC LIMIT 10 OFFSET ?";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			st.setInt(1, (index-1)*10);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				
				int bid = rs.getInt("bid");
				String title = rs.getString("title");
				String author = rs.getString("author");
				int catid = rs.getInt("catid");
				Date rlsdate = rs.getDate("rlsdate");
				int page = rs.getInt("page");
				int sold = rs.getInt("sold");
				int price = rs.getInt("price"); 
				String descr = rs.getString("descr");
				String coverurl = rs.getString("coverurl");
				
				Category c = new Category(catid, "", "");
				CategoryDAO cd = new CategoryDAO();
				Category category = cd.selectById(c);
				Book book =  new Book(bid, title, author, category, rlsdate, page, sold, price, descr, coverurl);
				ketQua.add(book);
				
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}
	public ArrayList<Book> pagingBookByIncreasingPriceAndCat(int index,int catid){
		ArrayList<Book> ketQua = new ArrayList<Book>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM book WHERE catid=? ORDER BY price LIMIT 10 OFFSET ?";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			st.setInt(1, catid);
			st.setInt(2, (index-1)*10);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				
				int bid = rs.getInt("bid");
				String title = rs.getString("title");
				String author = rs.getString("author");
				Date rlsdate = rs.getDate("rlsdate");
				int page = rs.getInt("page");
				int sold = rs.getInt("sold");
				int price = rs.getInt("price"); 
				String descr = rs.getString("descr");
				String coverurl = rs.getString("coverurl");
				
				Category c = new Category(catid, "", "");
				CategoryDAO cd = new CategoryDAO();
				Category category = cd.selectById(c);
				Book book =  new Book(bid, title, author, category, rlsdate, page, sold, price, descr, coverurl);
				ketQua.add(book);
				
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}
	public ArrayList<Book> pagingBookByDecreasingPriceAndCat(int index,int catid){
		ArrayList<Book> ketQua = new ArrayList<Book>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM book WHERE catid=? ORDER BY price DESC LIMIT 10 OFFSET ?";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			System.out.println(sql);
			st.setInt(1, catid);
			st.setInt(2, (index-1)*10);
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				
				int bid = rs.getInt("bid");
				String title = rs.getString("title");
				String author = rs.getString("author");
				Date rlsdate = rs.getDate("rlsdate");
				int page = rs.getInt("page");
				int sold = rs.getInt("sold");
				int price = rs.getInt("price"); 
				String descr = rs.getString("descr");
				String coverurl = rs.getString("coverurl");
				
				Category c = new Category(catid, "", "");
				CategoryDAO cd = new CategoryDAO();
				Category category = cd.selectById(c);
				Book book =  new Book(bid, title, author, category, rlsdate, page, sold, price, descr, coverurl);
				ketQua.add(book);
				
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return ketQua;
	}
	public int getTotalBook() {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT count(*) FROM book";
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
	
	public int getTotalBookByCat(int catid) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT count(*) FROM book WHERE catid=?";
			PreparedStatement st = con.prepareStatement(sql);
			System.out.println(sql);
			st.setInt(1, catid);
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
	public int getTotalBookSearch(String txtSearch) {
		try {
			Connection con = JDBCUtil.getConnection();
			String sql = "SELECT count(*) FROM book WHERE title like ?";
			PreparedStatement st = con.prepareStatement(sql);
			System.out.println(sql);
			st.setString(1, "%"+txtSearch+"%");
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
	public ArrayList<Book> searchBook(String txtSearch,int index){
		ArrayList<Book> ketQua = new ArrayList<Book>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM book WHERE title like ? ORDER BY bid LIMIT 10 OFFSET ?";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			st.setString(1,"%"+txtSearch+"%");
			st.setInt(2, (index-1)*10);
			System.out.println(sql);
			
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				
				int bid = rs.getInt("bid");
				String title = rs.getString("title");
				String author = rs.getString("author");
				int catid = rs.getInt("catid");
				Date rlsdate = rs.getDate("rlsdate");
				int page = rs.getInt("page");
				int sold = rs.getInt("sold");
				int price = rs.getInt("price"); 
				String descr = rs.getString("descr");
				String coverurl = rs.getString("coverurl");
				
				Category c = new Category(catid, "", "");
				CategoryDAO cd = new CategoryDAO();
				Category category = cd.selectById(c);
				Book book =  new Book(bid, title, author, category, rlsdate, page, sold, price, descr, coverurl);
				ketQua.add(book);
				
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketQua;
	}
	public ArrayList<Book> searchBookByIncreasingPrice(String txtSearch,int index){
		ArrayList<Book> ketQua = new ArrayList<Book>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM book WHERE title like ? ORDER BY price LIMIT 10 OFFSET ?";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			st.setString(1,"%"+txtSearch+"%");
			st.setInt(2, (index-1)*10);
			System.out.println(sql);
			
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				
				int bid = rs.getInt("bid");
				String title = rs.getString("title");
				String author = rs.getString("author");
				int catid = rs.getInt("catid");
				Date rlsdate = rs.getDate("rlsdate");
				int page = rs.getInt("page");
				int sold = rs.getInt("sold");
				int price = rs.getInt("price"); 
				String descr = rs.getString("descr");
				String coverurl = rs.getString("coverurl");
				
				Category c = new Category(catid, "", "");
				CategoryDAO cd = new CategoryDAO();
				Category category = cd.selectById(c);
				Book book =  new Book(bid, title, author, category, rlsdate, page, sold, price, descr, coverurl);
				ketQua.add(book);
				
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketQua;
	}
	public ArrayList<Book> searchBookByDecreasingPrice(String txtSearch,int index){
		ArrayList<Book> ketQua = new ArrayList<Book>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM book WHERE title like ? ORDER BY price DESC LIMIT 10 OFFSET ?";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			st.setString(1,"%"+txtSearch+"%");
			st.setInt(2, (index-1)*10);
			System.out.println(sql);
			
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				
				int bid = rs.getInt("bid");
				String title = rs.getString("title");
				String author = rs.getString("author");
				int catid = rs.getInt("catid");
				Date rlsdate = rs.getDate("rlsdate");
				int page = rs.getInt("page");
				int sold = rs.getInt("sold");
				int price = rs.getInt("price"); 
				String descr = rs.getString("descr");
				String coverurl = rs.getString("coverurl");
				
				Category c = new Category(catid, "", "");
				CategoryDAO cd = new CategoryDAO();
				Category category = cd.selectById(c);
				Book book =  new Book(bid, title, author, category, rlsdate, page, sold, price, descr, coverurl);
				ketQua.add(book);
				
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketQua;
	}
	public ArrayList<Book> pagingBestSellingBook(int index){
		ArrayList<Book> ketQua = new ArrayList<Book>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM book ORDER BY sold DESC LIMIT 10 OFFSET ?";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			st.setInt(1, (index-1)*10);
			System.out.println(sql);
			
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				
				int bid = rs.getInt("bid");
				String title = rs.getString("title");
				String author = rs.getString("author");
				int catid = rs.getInt("catid");
				Date rlsdate = rs.getDate("rlsdate");
				int page = rs.getInt("page");
				int sold = rs.getInt("sold");
				int price = rs.getInt("price"); 
				String descr = rs.getString("descr");
				String coverurl = rs.getString("coverurl");
				
				Category c = new Category(catid, "", "");
				CategoryDAO cd = new CategoryDAO();
				Category category = cd.selectById(c);
				Book book =  new Book(bid, title, author, category, rlsdate, page, sold, price, descr, coverurl);
				ketQua.add(book);
				
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketQua;
	}
	public ArrayList<Book> pagingBestSellingBookByCat(int index,int catid){
		ArrayList<Book> ketQua = new ArrayList<Book>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM book WHERE catid=? ORDER BY sold DESC LIMIT 10 OFFSET ?";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			st.setInt(1, catid);
			st.setInt(2, (index-1)*10);
			System.out.println(sql);
			
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				
				int bid = rs.getInt("bid");
				String title = rs.getString("title");
				String author = rs.getString("author");
//				int catid = rs.getInt("catid");
				Date rlsdate = rs.getDate("rlsdate");
				int page = rs.getInt("page");
				int sold = rs.getInt("sold");
				int price = rs.getInt("price"); 
				String descr = rs.getString("descr");
				String coverurl = rs.getString("coverurl");
				
				Category c = new Category(catid, "", "");
				CategoryDAO cd = new CategoryDAO();
				Category category = cd.selectById(c);
				Book book =  new Book(bid, title, author, category, rlsdate, page, sold, price, descr, coverurl);
				ketQua.add(book);
				
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketQua;
	}
	public ArrayList<Book> pagingBestSellingBookBySearch(int index,String txtSearch){
		ArrayList<Book> ketQua = new ArrayList<Book>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM book WHERE title like ? ORDER BY sold DESC LIMIT 10 OFFSET ?";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			st.setString(1,"%"+txtSearch+"%");
			st.setInt(2, (index-1)*10);
			System.out.println(sql);
			
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				
				int bid = rs.getInt("bid");
				String title = rs.getString("title");
				String author = rs.getString("author");
				int catid = rs.getInt("catid");
				Date rlsdate = rs.getDate("rlsdate");
				int page = rs.getInt("page");
				int sold = rs.getInt("sold");
				int price = rs.getInt("price"); 
				String descr = rs.getString("descr");
				String coverurl = rs.getString("coverurl");
				
				Category c = new Category(catid, "", "");
				CategoryDAO cd = new CategoryDAO();
				Category category = cd.selectById(c);
				Book book =  new Book(bid, title, author, category, rlsdate, page, sold, price, descr, coverurl);
				ketQua.add(book);
				
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketQua;
	}
	public ArrayList<Book> searchImportedBook(String txtSearch){
		ArrayList<Book> ketQua = new ArrayList<Book>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT * FROM book WHERE title like ?";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			st.setString(1,"%"+txtSearch+"%");
			
			System.out.println(sql);
			
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				
				int bid = rs.getInt("bid");
				String title = rs.getString("title");
				String author = rs.getString("author");
				int catid = rs.getInt("catid");
				Date rlsdate = rs.getDate("rlsdate");
				int page = rs.getInt("page");
				int sold = rs.getInt("sold");
				int price = rs.getInt("price"); 
				String descr = rs.getString("descr");
				String coverurl = rs.getString("coverurl");
				
				Category c = new Category(catid, "", "");
				CategoryDAO cd = new CategoryDAO();
				Category category = cd.selectById(c);
				Book book =  new Book(bid, title, author, category, rlsdate, page, sold, price, descr, coverurl);
				ketQua.add(book);
				
			}

			// Bước 5:
			JDBCUtil.closeConnection(con);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		return ketQua;
	}
	public ArrayList<Book> statisticBookByRevenue(String start, String end){
		ArrayList<Book> ketQua = new ArrayList<Book>();
		try {
			// Bước 1: tạo kết nối đến CSDL
			Connection con = JDBCUtil.getConnection();

			// Bước 2: tạo ra đối tượng statement
			String sql = "SELECT b.bid,b.title, b.author,b.catid, SUM(eb.price*eb.quantity) AS revenue\r\n"
					+ "FROM book AS b\r\n"
					+ "JOIN exportedbook AS eb ON b.bid = eb.bid\r\n"
					+ "JOIN sorder AS o ON eb.oid = o.oid\r\n"
					+ "WHERE o.datecreat >= ? AND o.datecreat <= ? AND STATUS=1\r\n"
					+ "GROUP BY eb.bid\r\n"
					+ "ORDER BY revenue DESC;";
			PreparedStatement st = con.prepareStatement(sql);

			// Bước 3: thực thi câu lệnh SQL
			st.setString(1,start);
			st.setString(2,end);
			
			System.out.println(sql);
			
			ResultSet rs = st.executeQuery();

			// Bước 4:
			while (rs.next()) {
				int bid = rs.getInt("bid");
				String title = rs.getString("title");
				String author = rs.getString("author");
				int catid = rs.getInt("catid");
				int revenue = rs.getInt("revenue");
				
				Category c = new Category(catid, "", "");
				CategoryDAO cd = new CategoryDAO();
				Category category = cd.selectById(c);
				Book book =  new Book(bid, title, author, category, null, 0, 0, 0, "", "");
				book.setRevenue(revenue);
				ketQua.add(book);
				
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
		BookDAO bookDAO = new BookDAO();
		
//		for (Book book : bookDAO.selectByIdOfCat(1)) {
//			System.out.println(book);
//		}
//		Category c = new Category(2, "Truyen trinh tham", "Phu hop cho tre 5 tuoi");
//		Book b = new Book(1, "Nang bach tuyet va 7 chu lun tap 2", "Nguyen Van A", c, java.sql.Date.valueOf("2002-01-02"), 100, 70,50000, "Truyen uu thich cua nhieu tre", "img\\image1.jpg");
//		bookDAO.insert(b);
		//		bookDAO.update(b);
		ArrayList<Book> lst = bookDAO.statisticBookByRevenue("2023-10-29", "2023-11-11");
		for(Book i: lst) {
			System.out.println(i);
			System.out.println(i.getRevenue());
		}
//		System.out.println(bookDAO.getTotalBookSearch("conan"));
		
	}
}
