package control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.BookDAO;
import dao.CategoryDAO;
import dao.ClientStatisticDAO;
import dao.ExportedBookDAO;
import dao.ImportedBookDAO;
import dao.OrderDAO;
import dao.ReceiptDAO;
import dao.SupplierDAO;
import dao.UserDAO;
import entity.Book;
import entity.Category;
import entity.ClientStatistic;
import entity.ExportedBook;
import entity.ImportedBook;
import entity.Order;
import entity.Receipt;
import entity.Supplier;
import entity.User;

/**
 * Servlet implementation class AdminController
 */
@MultipartConfig
@WebServlet("/admin-ctr")
public class AdminController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AdminController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		HttpSession session = request.getSession(); // tạo phiên lưu trữ
		User user = (User) session.getAttribute("user");
		if(user != null && user.isAdmin()) {
			if(action.equals("add-book")) {
				addBook(request,response);
			}else if(action.equals("update-book")) {
				updateBook(request,response);
			}else if(action.equals("delete-book")) {
				deleteBook(request,response);
			}else if(action.equals("category")) {
				category(request,response);
			}else if(action.equals("search-book")) {
				searchBook(request,response);
			}else if(action.equals("filter-by-price-increase")) {
				filterByPriceIncrease(request, response);
			}else if(action.equals("filter-by-price-increase-and-cat")) {
				filterByPriceIncreaseAndCat(request, response);
			}else if(action.equals("filter-by-price-decrease")) {
				filterByPriceDecrease(request, response);
			}else if(action.equals("filter-by-price-decrease-and-cat")) {
				filterByPriceDecreaseAndCat(request, response);
			}else if(action.equals("search-book-by-price-increase")) {
				searchBookByPriceIncrease(request,response);
			}else if(action.equals("search-book-by-price-decrease")) {
				searchBookByPriceDecrease(request,response);
			}else if(action.equals("filter-by-best-selling")) {
				filterByBestSelling(request,response);
			}else if(action.equals("filter-by-best-selling-book-by-cat")) {
				filterByBestSellingBookByCat(request, response);
			}else if(action.equals("filter-by-best-selling-book-by-search")) {
				filterByBestSellingBookBySearch(request, response);
			}else if(action.equals("view-order-all")) {
				viewOrderAll(request,response);
			}else if(action.equals("view-order-confirmed")) {
				viewOrderConfirmed(request,response);
			}else if(action.equals("view-order-waiting")) {
				viewOrderWaiting(request,response);
			}else if(action.equals("view-order-cancelled")) {
				viewOrderCancelled(request,response);
			}else if(action.equals("confirm-order")) {
				confirmOrder(request,response);
			}else if(action.equals("search-supplier")) {
				searchSupplier(request,response);
			}else if(action.equals("choose-supplier")){
				chooseSupplier(request,response);
			}else if(action.equals("search-imported-book")) {
				searchImportedBook(request,response);
			}else if(action.equals("add-to-import-list")) {
				addToImportList(request,response);
			}else if(action.equals("view-cart")) {
				viewCart(request,response);
			}else if(action.equals("update-cart")) {
				updateCart(request,response);
			}else if(action.equals("delete-cart")) {
				deleteCart(request,response);
			}else if(action.equals("make-receipt")) {
				makeReceipt(request,response);
			}else if(action.equals("statistic-revenue")) {
				statisticRevenue(request,response);
			}else if(action.equals("view-detailed-order-statistics")) {
				viewDetailedOrderStatistics(request,response);
			}else if(action.equals("statistic-book-by-revenue")) {
				statisticBookByRevenue(request,response);
			}else if(action.equals("view-detailed-book-statistics")) {
				viewDetailedBookStatistics(request,response);
			}else if(action.equals("statistic-client-by-revenue")) {
				statisticClientByRevenue(request,response);
			}else if(action.equals("view-detailed-client-statistics")) {
				viewDetailedClientStatistics(request,response);
			}else if(action.equals("view-account-of-client")) {
				viewAccountOfClient(request,response);
			}
		}else {
			response.sendRedirect("home");
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void addBook(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String title = request.getParameter("title");
		String author = request.getParameter("author");
		String catidString  = request.getParameter("category");
		int catid = Integer.parseInt(catidString);
		String rlsdate = request.getParameter("rlsdate");
		String pageString = request.getParameter("page");
		int page = Integer.parseInt(pageString);
		String descr = request.getParameter("descr");
		String priceString = request.getParameter("price");
		int price = Integer.parseInt(priceString);
		String soldString = request.getParameter("sold");
		int sold = Integer.parseInt(soldString);
		
		CategoryDAO cd = new CategoryDAO();
		BookDAO bd = new BookDAO();
		Category t = new Category();
		t.setCatid(catid);
		Category c = cd.selectById(t);
		Book b = new Book(0, title, author, c, Date.valueOf(rlsdate), page, sold, price, descr, "");
		int bid = bd.insert(b);
		b.setBid(bid);
		Part file = request.getPart("imageFile");
		if(file != null && file.getSubmittedFileName().lastIndexOf(".") < file.getSubmittedFileName().length()-1) {
			String imageFileNameX = file.getSubmittedFileName();
			System.out.println("Selected Image File Name: "+imageFileNameX);
			
			int lastIndexOfDot = imageFileNameX.lastIndexOf(".");
			//long millis = System.currentTimeMillis();
			String imageFileName = bid+"."+imageFileNameX.substring(lastIndexOfDot + 1);

			String uploadPath = "C:/Users/acer/eclipse-workspace/BaiTapLonbackup/src/main/webapp/img/"+imageFileName;
			System.out.println("Upload path: "+uploadPath);
			File ftmp = new File(uploadPath);
			if(ftmp.exists()) {
				if(ftmp.delete()) {
				   System.out.println("File da duoc xoa thanh cong");
				} else {
				   System.out.println("Xoa file that bai");
				}
			}
			try {
				FileOutputStream fos = new FileOutputStream(uploadPath);
				InputStream is = file.getInputStream();
				byte[] data = new byte[is.available()];
				is.read(data);
				fos.write(data);
				fos.close();
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
			
			imageFileName="img/"+imageFileName;
			b.setCoverurl(imageFileName);
		}
		bd.update(b);
		//response.sendRedirect("home");
		request.setAttribute("book", b);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		request.getRequestDispatcher("/admin/add-book.jsp").forward(request, response);
		
	}
	private void updateBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String ck = request.getParameter("ck");
		String bidStr = request.getParameter("bid");
		int bid = Integer.parseInt(bidStr);
		if(ck.equals("false")) {
			BookDAO bd = new BookDAO();
			Book b = bd.selectById(bid);
			request.setAttribute("book", b);
			request.getRequestDispatcher("/admin/update-book.jsp").forward(request, response);
		}else {
			String title = request.getParameter("title");
			String author = request.getParameter("author");
			String catidString  = request.getParameter("category");
			int catid = Integer.parseInt(catidString);
			String rlsdate = request.getParameter("rlsdate");
			String pageString = request.getParameter("page");
			int page = Integer.parseInt(pageString);
			String descr = request.getParameter("descr");
			String priceString = request.getParameter("price");
			int price = Integer.parseInt(priceString);
			String soldString = request.getParameter("sold");
			int sold = Integer.parseInt(soldString);
			String image = request.getParameter("image");
			System.out.println(image);
			CategoryDAO cd = new CategoryDAO();
			BookDAO bd = new BookDAO();
			Category t = new Category();
			t.setCatid(catid);
			Category c = cd.selectById(t);
			Book b = bd.selectById(bid);
			b.setTitle(title);
			b.setAuthor(author);
			b.setCategory(c);
			b.setRlsdate(Date.valueOf(rlsdate));
			b.setPage(page);
			b.setDescr(descr);
			b.setPrice(price);
			b.setSold(sold);
			
			Part file = request.getPart("imageFile");
			if(file != null && file.getSubmittedFileName().lastIndexOf(".") < file.getSubmittedFileName().length()-1) {
				String imageFileNameX = file.getSubmittedFileName();
				System.out.println("Selected Image File Name: "+imageFileNameX);
				
				int lastIndexOfDot = imageFileNameX.lastIndexOf(".");
				//long millis = System.currentTimeMillis();
				String imageFileName = bid+"."+imageFileNameX.substring(lastIndexOfDot + 1);

				String uploadPath = "C:/Users/acer/eclipse-workspace/BaiTapLonbackup/src/main/webapp/img/"+imageFileName;
				System.out.println("Upload path: "+uploadPath);
				File ftmp = new File(uploadPath);
				if(ftmp.exists()) {
					if(ftmp.delete()) {
					   System.out.println("File da duoc xoa thanh cong");
					} else {
					   System.out.println("Xoa file that bai");
					}
				}
				try {
					FileOutputStream fos = new FileOutputStream(uploadPath);
					InputStream is = file.getInputStream();
					byte[] data = new byte[is.available()];
					is.read(data);
					fos.write(data);
					fos.close();
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
				
				imageFileName="img/"+imageFileName;
				b.setCoverurl(imageFileName);
			}
			bd.update(b);
			//response.sendRedirect("home");
			request.setAttribute("book", b);
			request.setAttribute("ck", "true");
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			request.getRequestDispatcher("/admin/update-book.jsp").forward(request, response);
			
		}
	}
	private void deleteBook(HttpServletRequest request, HttpServletResponse response) throws IOException {
		int bid = Integer.parseInt(request.getParameter("bid"));
		ExportedBookDAO epd = new ExportedBookDAO();
		ImportedBookDAO ipd = new ImportedBookDAO();
		BookDAO bd = new BookDAO();
		epd.delete(bid);
		ipd.delete(bid);
		bd.delete(bid);
		response.sendRedirect("home");
	}
	private void category(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String catid = request.getParameter("catid");
		String indexPage = request.getParameter("indexPage");
		if(indexPage == null) {
        	indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
		
		// đã lấy được catid về rồi
		BookDAO bd = new BookDAO();
		CategoryDAO cd = new CategoryDAO();
		
		ArrayList<Book> list = new ArrayList<Book>();
		ArrayList<Category> listC = new ArrayList<Category>();
		
		
		list = bd.pagingBookByCat(index, Integer.parseInt(catid));
		listC = cd.selectAll();
		
		int endPage = bd.getTotalBookByCat(Integer.parseInt(catid))/10;
        if(bd.getTotalBook() % 10 != 0) {
        	endPage++;
        }
		//ở đây ta vẫn phải đẩy lại listC tóm lại nó sẽ là update của trang home nếu ko đẩy listC thì listC( tức danh sách thể loại sẽ bị null)
		
		//ta sẽ đẩy cái list này cho listB ở trang control/home sẽ thay vì đẩy tất cả các sách lên thì sẽ đẩy theo thể loại sách thôi
        request.setAttribute("endPage", endPage);
        request.setAttribute("index", index);
        request.setAttribute("listB", list);
		request.setAttribute("listC", listC);
		request.setAttribute("tag", catid);
		request.setAttribute("catid", catid);
		
		request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
	}
	private void searchBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String txtSearch = request.getParameter("txtSearch");
		String indexPage = request.getParameter("indexPage");
		if(indexPage == null) {
        	indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
		
		
		BookDAO bd = new BookDAO();
		CategoryDAO cd = new CategoryDAO();
		
		ArrayList<Book> list = new ArrayList<Book>();
		ArrayList<Category> listC = new ArrayList<Category>();
		
		list = bd.searchBook(txtSearch, index);
		listC = cd.selectAll();
		int endPage = bd.getTotalBookSearch(txtSearch)/10;
        if(bd.getTotalBookSearch(txtSearch) % 10 != 0) {
        	endPage++;
        }
		//ở đây ta vẫn phải đẩy lại listC tóm lại nó sẽ là update của trang home nếu ko đẩy listC thì listC( tức danh sách thể loại sẽ bị null)
		
		//ta sẽ đẩy cái list này cho listB ở trang control/home sẽ thay vì đẩy tất cả các sách lên thì sẽ đẩy theo thể loại sách thôi
        request.setAttribute("endPage", endPage);
        request.setAttribute("index", index);
        request.setAttribute("listB", list);
		request.setAttribute("listC", listC);
		request.setAttribute("tagS", 1);
		request.setAttribute("txtSearch", txtSearch);
		request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
	}
	private void filterByPriceIncrease(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String indexPage = request.getParameter("indexPage");
        if(indexPage == null) {
        	indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        
        
        BookDAO bd = new BookDAO();
        CategoryDAO cd = new CategoryDAO();
        ArrayList<Book> list = new ArrayList<Book>();
        ArrayList<Category> listC = new ArrayList<Category>();
        list = bd.pagingBookByIncreasingPrice(index);
        listC = cd.selectAll();
        //dem so trang: giả sử mỗi trang có 10 quyển sách
        int endPage = bd.getTotalBook()/10;
        if(bd.getTotalBook() % 10 != 0) {
        	endPage++;
        }
        request.setAttribute("endPage", endPage);
        request.setAttribute("index", index);
        request.setAttribute("listB", list);
        request.setAttribute("listC", listC);
        request.setAttribute("tagIncrease", 1);
        request.setAttribute("checkAll", 1);
        
        request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
	}
	private void filterByPriceIncreaseAndCat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String indexPage = request.getParameter("indexPage");
		String catidStr = request.getParameter("catid");
		int catid = Integer.parseInt(catidStr);
		
        if(indexPage == null) {
        	indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        
        
        BookDAO bd = new BookDAO();
        CategoryDAO cd = new CategoryDAO();
        ArrayList<Book> list = new ArrayList<Book>();
        ArrayList<Category> listC = new ArrayList<Category>();
        list = bd.pagingBookByIncreasingPriceAndCat(index,catid);
        listC = cd.selectAll();
        //dem so trang: giả sử mỗi trang có 10 quyển sách
        int endPage = bd.getTotalBookByCat(catid)/10;
        if(bd.getTotalBookByCat(catid) % 10 != 0) {
        	endPage++;
        }
        request.setAttribute("endPage", endPage);//cái này để hiển thị từ 1 đến endPage
        request.setAttribute("index", index);//cái này để active số thứ tự 
        request.setAttribute("listB", list);
        request.setAttribute("listC", listC);
        request.setAttribute("catid", catid);
        request.setAttribute("tagIncreaseAndCat", 1);
        request.setAttribute("tag", catid);
        request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
	}
	private void filterByPriceDecrease(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String indexPage = request.getParameter("indexPage");
        if(indexPage == null) {
        	indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        
        
        BookDAO bd = new BookDAO();
        CategoryDAO cd = new CategoryDAO();
        ArrayList<Book> list = new ArrayList<Book>();
        ArrayList<Category> listC = new ArrayList<Category>();
        list = bd.pagingBookByDecreasingPrice(index);
        listC = cd.selectAll();
        //dem so trang: giả sử mỗi trang có 10 quyển sách
        int endPage = bd.getTotalBook()/10;
        if(bd.getTotalBook() % 10 != 0) {
        	endPage++;
        }
        request.setAttribute("endPage", endPage);
        request.setAttribute("index", index);
        request.setAttribute("listB", list);
        request.setAttribute("listC", listC);
        request.setAttribute("tagDecrease", 1);
        request.setAttribute("checkAll", 1);
        
        request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
	}
	private void filterByPriceDecreaseAndCat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String indexPage = request.getParameter("indexPage");
		String catidStr = request.getParameter("catid");
		int catid = Integer.parseInt(catidStr);
		
        if(indexPage == null) {
        	indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        
        
        BookDAO bd = new BookDAO();
        CategoryDAO cd = new CategoryDAO();
        ArrayList<Book> list = new ArrayList<Book>();
        ArrayList<Category> listC = new ArrayList<Category>();
        list = bd.pagingBookByDecreasingPriceAndCat(index,catid);
        listC = cd.selectAll();
        //dem so trang: giả sử mỗi trang có 10 quyển sách
        int endPage = bd.getTotalBookByCat(catid)/10;
        if(bd.getTotalBookByCat(catid) % 10 != 0) {
        	endPage++;
        }
        request.setAttribute("endPage", endPage);//cái này để hiển thị từ 1 đến endPage
        request.setAttribute("index", index);//cái này để active số thứ tự 
        request.setAttribute("listB", list);
        request.setAttribute("listC", listC);
        request.setAttribute("catid", catid);
        request.setAttribute("tagDecreaseAndCat", 1);
        request.setAttribute("tag", catid);
        request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
	}
	private void searchBookByPriceIncrease(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String txtSearch = request.getParameter("txtSearch");
		String indexPage = request.getParameter("indexPage");
		if(indexPage == null) {
        	indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
		
		
		BookDAO bd = new BookDAO();
		CategoryDAO cd = new CategoryDAO();
		
		ArrayList<Book> list = new ArrayList<Book>();
		ArrayList<Category> listC = new ArrayList<Category>();
		
		list = bd.searchBookByIncreasingPrice(txtSearch, index);
		listC = cd.selectAll();
		int endPage = bd.getTotalBookSearch(txtSearch)/10;
        if(bd.getTotalBookSearch(txtSearch) % 10 != 0) {
        	endPage++;
        }
		//ở đây ta vẫn phải đẩy lại listC tóm lại nó sẽ là update của trang home nếu ko đẩy listC thì listC( tức danh sách thể loại sẽ bị null)
		
		//ta sẽ đẩy cái list này cho listB ở trang control/home sẽ thay vì đẩy tất cả các sách lên thì sẽ đẩy theo thể loại sách thôi
        request.setAttribute("endPage", endPage);
        request.setAttribute("index", index);
        request.setAttribute("listB", list);
		request.setAttribute("listC", listC);
		request.setAttribute("tagS", 1);
		request.setAttribute("txtSearch", txtSearch);
		request.setAttribute("tagSIncrease", 1);
		request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
	}
	private void searchBookByPriceDecrease(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String txtSearch = request.getParameter("txtSearch");
		String indexPage = request.getParameter("indexPage");
		if(indexPage == null) {
        	indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
		
		
		BookDAO bd = new BookDAO();
		CategoryDAO cd = new CategoryDAO();
		
		ArrayList<Book> list = new ArrayList<Book>();
		ArrayList<Category> listC = new ArrayList<Category>();
		
		list = bd.searchBookByDecreasingPrice(txtSearch, index);
		listC = cd.selectAll();
		int endPage = bd.getTotalBookSearch(txtSearch)/10;
        if(bd.getTotalBookSearch(txtSearch) % 10 != 0) {
        	endPage++;
        }
		//ở đây ta vẫn phải đẩy lại listC tóm lại nó sẽ là update của trang home nếu ko đẩy listC thì listC( tức danh sách thể loại sẽ bị null)
		
		//ta sẽ đẩy cái list này cho listB ở trang control/home sẽ thay vì đẩy tất cả các sách lên thì sẽ đẩy theo thể loại sách thôi
        request.setAttribute("endPage", endPage);
        request.setAttribute("index", index);
        request.setAttribute("listB", list);
		request.setAttribute("listC", listC);
		request.setAttribute("tagS", 1);
		request.setAttribute("txtSearch", txtSearch);
		request.setAttribute("tagSDecrease", 1);
		request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
	}
	private void filterByBestSelling(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String indexPage = request.getParameter("indexPage");
        if(indexPage == null) {
        	indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        
        
        BookDAO bd = new BookDAO();
        CategoryDAO cd = new CategoryDAO();
        ArrayList<Book> list = new ArrayList<Book>();
        ArrayList<Category> listC = new ArrayList<Category>();
        list = bd.pagingBestSellingBook(index);
        listC = cd.selectAll();
        //dem so trang: giả sử mỗi trang có 10 quyển sách
        int endPage = bd.getTotalBook()/10;
        if(bd.getTotalBook() % 10 != 0) {
        	endPage++;
        }
        request.setAttribute("endPage", endPage);
        request.setAttribute("index", index);
        request.setAttribute("listB", list);
        request.setAttribute("listC", listC);
        request.setAttribute("tagBestSelling", 1);
        request.setAttribute("checkAll", 1);
        
        request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
	}
	private void filterByBestSellingBookByCat(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String indexPage = request.getParameter("indexPage");
		int catid = Integer.parseInt(request.getParameter("catid"));
        if(indexPage == null) {
        	indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        
        
        BookDAO bd = new BookDAO();
        CategoryDAO cd = new CategoryDAO();
        ArrayList<Book> list = new ArrayList<Book>();
        ArrayList<Category> listC = new ArrayList<Category>();
        list = bd.pagingBestSellingBookByCat(index,catid);
        listC = cd.selectAll();
        //dem so trang: giả sử mỗi trang có 10 quyển sách
        int endPage = bd.getTotalBookByCat(catid)/10;
        if(bd.getTotalBookByCat(catid) % 10 != 0) {
        	endPage++;
        }
        request.setAttribute("endPage", endPage);
        request.setAttribute("index", index);
        request.setAttribute("listB", list);
        request.setAttribute("listC", listC);
        request.setAttribute("catid", catid);
        request.setAttribute("tagBestSellingBookByCat", 1);
        
        
        request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
	}
	private void filterByBestSellingBookBySearch(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String indexPage = request.getParameter("indexPage");
		String txtSearch = request.getParameter("txtSearch");
        if(indexPage == null) {
        	indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        
        
        BookDAO bd = new BookDAO();
        CategoryDAO cd = new CategoryDAO();
        ArrayList<Book> list = new ArrayList<Book>();
        ArrayList<Category> listC = new ArrayList<Category>();
        list = bd.pagingBestSellingBookBySearch(index,txtSearch);
        listC = cd.selectAll();
        //dem so trang: giả sử mỗi trang có 10 quyển sách
        int endPage = bd.getTotalBookSearch(txtSearch)/10;
        if(bd.getTotalBookSearch(txtSearch) % 10 != 0) {
        	endPage++;
        }
        request.setAttribute("endPage", endPage);
        request.setAttribute("index", index);
        request.setAttribute("listB", list);
        request.setAttribute("listC", listC);
        request.setAttribute("txtSearch", txtSearch);
        request.setAttribute("tagBestSellingBookBySearch", 1);
        request.setAttribute("tagS", 1);
        
        request.getRequestDispatcher("/admin/index.jsp").forward(request, response);
	}
	private void viewOrderAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Order> listAllOrderOfUsers = new ArrayList<Order>();
		OrderDAO od = new OrderDAO();
		listAllOrderOfUsers = od.selectAllOrderOfUsers();
		int cntTotal = od.getTotalOrder();
		int cntConfirmed = od.getTotalOrderByStatus(1);
		int cntWaiting = od.getTotalOrderByStatus(0);
		int cntCancelled = od.getTotalOrderByStatus(2);
		
		request.setAttribute("cntTotal", cntTotal);
		request.setAttribute("cntConfirmed", cntConfirmed);
		request.setAttribute("cntWaiting", cntWaiting);
		request.setAttribute("cntCancelled", cntCancelled);
		request.setAttribute("listAllOrderOfUsers", listAllOrderOfUsers);
		request.setAttribute("viewOrderAll", true);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/view-order.jsp");
		dispatcher.forward(request, response);
		
	}
	private void viewOrderConfirmed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Order> listAllOrderOfUsersConfirmed = new ArrayList<Order>();
		
		OrderDAO od = new OrderDAO();
		listAllOrderOfUsersConfirmed = od.selectByStatusOfOrderAllUser(1);
		int cntTotal = od.getTotalOrder();
		int cntConfirmed = od.getTotalOrderByStatus(1);
		int cntWaiting = od.getTotalOrderByStatus(0);
		int cntCancelled = od.getTotalOrderByStatus(2);
		
		request.setAttribute("cntTotal", cntTotal);
		request.setAttribute("cntConfirmed", cntConfirmed);
		request.setAttribute("cntWaiting", cntWaiting);
		request.setAttribute("cntCancelled", cntCancelled);
		request.setAttribute("listAllOrderOfUsersConfirmed", listAllOrderOfUsersConfirmed);
		request.setAttribute("viewOrderOfUsersConfirmed", true);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/view-order.jsp");
		dispatcher.forward(request, response);
		
	}
	private void viewOrderWaiting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		ArrayList<Order> listAllOrderOfUsersWaitingConfirmed = new ArrayList<Order>();
		
		OrderDAO od = new OrderDAO();
		listAllOrderOfUsersWaitingConfirmed = od.selectByStatusOfOrderAllUser(0);
		int cntTotal = od.getTotalOrder();
		int cntConfirmed = od.getTotalOrderByStatus(1);
		int cntWaiting = od.getTotalOrderByStatus(0);
		int cntCancelled = od.getTotalOrderByStatus(2);
		
		request.setAttribute("cntTotal", cntTotal);
		request.setAttribute("cntConfirmed", cntConfirmed);
		request.setAttribute("cntWaiting", cntWaiting);
		request.setAttribute("cntCancelled", cntCancelled);
		request.setAttribute("listAllOrderOfUsersWaitingConfirmed", listAllOrderOfUsersWaitingConfirmed);
		request.setAttribute("viewOrderOfUsersWaitingConfirmed", true);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/view-order.jsp");
		dispatcher.forward(request, response);
		
	}
	private void viewOrderCancelled(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Order> listAllOrderOfUsersCancelled = new ArrayList<Order>();
		
		OrderDAO od = new OrderDAO();
		listAllOrderOfUsersCancelled = od.selectByStatusOfOrderAllUser(2);
		int cntTotal = od.getTotalOrder();
		int cntConfirmed = od.getTotalOrderByStatus(1);
		int cntWaiting = od.getTotalOrderByStatus(0);
		int cntCancelled = od.getTotalOrderByStatus(2);
		
		request.setAttribute("cntTotal", cntTotal);
		request.setAttribute("cntConfirmed", cntConfirmed);
		request.setAttribute("cntWaiting", cntWaiting);
		request.setAttribute("cntCancelled", cntCancelled);
		request.setAttribute("listAllOrderOfUsersCancelled", listAllOrderOfUsersCancelled);
		request.setAttribute("viewOrderOfUsersCancelled", true);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/view-order.jsp");
		dispatcher.forward(request, response);
		
	}
	private void confirmOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int oid = Integer.parseInt(request.getParameter("oid"));
		OrderDAO od = new OrderDAO();
		ExportedBookDAO exd = new ExportedBookDAO();
		BookDAO bd = new BookDAO();
		
		Order order = od.select(oid);
		order.setStatus(1);
		od.update(order);
		List<ExportedBook> listExportedBook = new ArrayList<ExportedBook>();
		listExportedBook = exd.selectByIdOfOrder(oid);
		for(ExportedBook i:listExportedBook) {
			Book b = bd.selectById(i.getBook().getBid());
			b.setSold(i.getQuantity()+b.getSold());
			bd.update(b);
		}
		ArrayList<Order> listAllOrderOfUsersWaitingConfirmed = new ArrayList<Order>();
		listAllOrderOfUsersWaitingConfirmed = od.selectByStatusOfOrderAllUser(0);
		int cntTotal = od.getTotalOrder();
		int cntConfirmed = od.getTotalOrderByStatus(1);
		int cntWaiting = od.getTotalOrderByStatus(0);
		int cntCancelled = od.getTotalOrderByStatus(2);
		
		request.setAttribute("cntTotal", cntTotal);
		request.setAttribute("cntConfirmed", cntConfirmed);
		request.setAttribute("cntWaiting", cntWaiting);
		request.setAttribute("cntCancelled", cntCancelled);
		request.setAttribute("listAllOrderOfUsersWaitingConfirmed", listAllOrderOfUsersWaitingConfirmed);
		request.setAttribute("viewOrderOfUsersWaitingConfirmed", true);
		request.getRequestDispatcher("/admin/view-order.jsp").forward(request, response);
	}
	private void searchSupplier(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String txtSearch = request.getParameter("txtSearch");
		SupplierDAO sd = new SupplierDAO();
		ArrayList<Supplier> listSupplier = new ArrayList<Supplier>();
		
		listSupplier = sd.searchSupplier(txtSearch);
		request.setAttribute("listSupplier", listSupplier);
		request.getRequestDispatcher("/admin/find-supplier.jsp").forward(request, response);
	}
	private void chooseSupplier(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String sidStr = request.getParameter("sid");
		int sid = Integer.parseInt(sidStr);
		SupplierDAO sd = new SupplierDAO();
		Supplier supplier = sd.selectById(sid);
		HttpSession session = request.getSession();
		session.setAttribute("supplier", supplier);
		response.sendRedirect("admin/find-imported-book.jsp");
	}
	private void searchImportedBook(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String txtSearch = request.getParameter("txtSearch");
		BookDAO bd = new BookDAO();
		ArrayList<Book> listSearchBook = new ArrayList<Book>();
		listSearchBook = bd.searchImportedBook(txtSearch);
		request.setAttribute("listSearchBook", listSearchBook);
		request.getRequestDispatcher("/admin/find-imported-book.jsp").forward(request, response);
	}
	private void  addToImportList(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String bidStr = request.getParameter("chooseBook");
		String txtQuantity = request.getParameter("txtQuantity");
		String txtPrice = request.getParameter("txtPrice");
		int bid = Integer.parseInt(bidStr);
		int quantity = Integer.parseInt(txtQuantity);
		int price = Integer.parseInt(txtPrice);
		BookDAO bd = new BookDAO();
		HttpSession session = request.getSession();
		Book book = bd.selectById(bid);
		if(book != null) {
			if(session.getAttribute("receipt") == null) {
				Receipt receipt = new Receipt();
				List<ImportedBook> listItems = new ArrayList<ImportedBook>();
				ImportedBook item = new ImportedBook();
				item.setQuantity(quantity);
				item.setBook(book);
				item.setPrice(price);
				listItems.add(item);
				receipt.setItems(listItems);
				session.setAttribute("receipt", receipt);
			}else {
				Receipt receipt = (Receipt)session.getAttribute("receipt");
				List<ImportedBook> listItems = receipt.getItems();
				boolean check = false;
				for(ImportedBook item : listItems) {
					if(item.getBook().getBid() == book.getBid()) {
						item.setQuantity(quantity+item.getQuantity());
						check = true;
					}
				}
				if(check == false) {
					ImportedBook item = new ImportedBook();
					item.setBook(book);
					item.setQuantity(quantity);
					item.setPrice(price);
					listItems.add(item);
				}
				session.setAttribute("receipt", receipt);
			}
			response.sendRedirect("admin/find-imported-book.jsp");
		}else {
			response.sendRedirect("admin/find-imported-book.jsp");
		}
	}
	private void viewCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Receipt receipt = (Receipt)session.getAttribute("receipt");
		int total = 0;
		if(receipt!=null && receipt.getItems().size() >0) {
			for(ImportedBook i:receipt.getItems()) {
				total += i.getPrice()*i.getQuantity();
			}
		}else {
			RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/cart-list.jsp");
			dispatcher.forward(request, response);
		}
		receipt.setTotal(total);
		session.setAttribute("receipt", receipt);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/cart-list.jsp");
		dispatcher.forward(request, response);
	}
	private void updateCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=0;
		if(request.getParameter("bid")!=null) {
			id = Integer.parseInt(request.getParameter("bid"));
		}
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		HttpSession session = request.getSession();
		Receipt receipt = (Receipt)session.getAttribute("receipt");
		List<ImportedBook> listItems = receipt.getItems();
		
		for(ImportedBook item : listItems) {
			
			if(item.getBook().getBid() == id) {
				item.setQuantity(quantity);
				break;
			}
			
		}
		
		session.setAttribute("receipt", receipt);
		viewCart(request, response);
	}
	private void deleteCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=0;
		if(request.getParameter("bid")!=null) {
			id = Integer.parseInt(request.getParameter("bid"));
		}
		HttpSession session = request.getSession();
		Receipt receipt = (Receipt)session.getAttribute("receipt");
		List<ImportedBook> listItems = receipt.getItems();
		int t = 0;
		for(ImportedBook item : listItems) {
			if(item.getBook().getBid() == id) {
				break;
			}
			t+=1;
		}
		listItems.remove(t);
		session.setAttribute("receipt", receipt);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/khachhang/cart-list.jsp");
//		dispatcher.forward(request, response); //tại sao ko lấy 2 dòng này mà lại lấy dòng dưới, thử xem lỗi sẽ sinh ra khi ta xóa trong cart-list rồi tải lại trang(Ctr+R) --> 500 error
		viewCart(request, response);
	}
	private void makeReceipt(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Receipt receipt = (Receipt)session.getAttribute("receipt");
		Supplier supplier = (Supplier)session.getAttribute("supplier");
		receipt.setSupplier(supplier);
		
		ReceiptDAO rd = new ReceiptDAO();
		ImportedBookDAO imd = new ImportedBookDAO();
		int rid = rd.insert(receipt);
		
		for(ImportedBook i:receipt.getItems()) {
			i.setRid(rid);
			imd.insert(i);
		}
		receipt = null;
		session.setAttribute("receipt", receipt);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/receipt-success.jsp");
		dispatcher.forward(request, response);
	}
	private void statisticRevenue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		if(start == null || start.length()==0) {
			
			LocalDate today = LocalDate.now();
		    LocalDate tenDaysAgo = today.minusDays(30);
		    
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    start = tenDaysAgo.format(formatter);
	
			
			/* start = "2023-11-22"; */
		}else {
		    start = Date.valueOf(start).toString();
		}
		if(end == null || end.length() == 0 ) {
			LocalDate currentDate = LocalDate.now();
		    
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    end = currentDate.format(formatter);
//			end = "2023-12-02";
		}else {
			end = Date.valueOf(end).toString();
		}
		OrderDAO od = new OrderDAO();
		ArrayList<Order> listStaticRevenue = od.statisticRevenueByTime(start, end);
		int sumRevenue = od.sumRevenueOfOrder();
		request.setAttribute("listStaticRevenue", listStaticRevenue);
		request.setAttribute("sumRevenue", sumRevenue);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/statistic-revenue.jsp");
		dispatcher.forward(request, response);
	}
	private void viewDetailedOrderStatistics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String sdate = request.getParameter("sdate");
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		OrderDAO od = new OrderDAO();
		ArrayList<Order> listDetailOrders = od.detailStatisticRevenueByTime(sdate);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("sdate", sdate);
		request.setAttribute("listDetailOrders", listDetailOrders);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/detail-order-statistic.jsp");
		dispatcher.forward(request, response);
	}
	private void statisticBookByRevenue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		if(start == null || start.length()==0) {
			
			LocalDate today = LocalDate.now();
		    LocalDate tenDaysAgo = today.minusDays(30);
		    
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    start = tenDaysAgo.format(formatter);
	
			
			/* start = "2023-11-22"; */
		}else {
		    start = Date.valueOf(start).toString();
		}
		if(end == null || end.length() == 0 ) {
			LocalDate currentDate = LocalDate.now();
		    
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    end = currentDate.format(formatter);
//			end = "2023-12-02";
		}else {
			end = Date.valueOf(end).toString();
		}
		BookDAO bd = new BookDAO();
		ArrayList<Book> listStaticBook = bd.statisticBookByRevenue(start, end);
		
		request.setAttribute("listStaticBook", listStaticBook);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/statistic-book.jsp");
		dispatcher.forward(request, response);
	}
	private void viewDetailedBookStatistics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		int bid = Integer.parseInt(request.getParameter("bid"));
		OrderDAO od = new OrderDAO();
		ArrayList<Order> listDetailBookByOrder = od.detailStatisticBookByRevenue(start, end, bid);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("listDetailBookByOrder", listDetailBookByOrder);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/detail-book-statistic.jsp");
		dispatcher.forward(request, response);
	}
	private void statisticClientByRevenue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		if(start == null || start.length()==0) {
			
			LocalDate today = LocalDate.now();
		    LocalDate tenDaysAgo = today.minusDays(30);
		    
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    start = tenDaysAgo.format(formatter);
	
			
			/* start = "2023-11-22"; */
		}else {
		    start = Date.valueOf(start).toString();
		}
		if(end == null || end.length() == 0 ) {
			LocalDate currentDate = LocalDate.now();
		    
		    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		    end = currentDate.format(formatter);
//			end = "2023-12-02";
		}else {
			end = Date.valueOf(end).toString();
		}
		ClientStatisticDAO cld = new ClientStatisticDAO();
		ArrayList<ClientStatistic> listStaticClient = new ArrayList<ClientStatistic>();
		listStaticClient = cld.statisticClient(start, end);
		request.setAttribute("listStaticClient", listStaticClient);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/statistic-client.jsp");
		dispatcher.forward(request, response);
	}
	private void viewDetailedClientStatistics(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String start = request.getParameter("start");
		String end = request.getParameter("end");
		int uid = Integer.parseInt(request.getParameter("uid"));
		OrderDAO od  = new OrderDAO();
		ArrayList<Order> listDetailOrder = new ArrayList<Order>();
		listDetailOrder = od.selectOrderOfClientStatic(uid, start, end);
		request.setAttribute("start", start);
		request.setAttribute("end", end);
		request.setAttribute("listDetailOrder", listDetailOrder);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/detail-client-statistic.jsp");
		dispatcher.forward(request, response);
	}
	private void viewAccountOfClient(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserDAO ud = new UserDAO();
		ArrayList<User> listAccount = ud.selectAllAccountOfClient();
		int total = listAccount.size();
		request.setAttribute("listAccount", listAccount);
		request.setAttribute("total", total);
		RequestDispatcher dispatcher = request.getRequestDispatcher("/admin/view-account-of-client.jsp");
		dispatcher.forward(request, response);
	}
}
