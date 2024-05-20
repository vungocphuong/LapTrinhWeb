package control;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.Part;

import dao.BookDAO;
import dao.CategoryDAO;
import dao.CommentDAO;
import dao.ExportedBookDAO;
import dao.OrderDAO;
import dao.UserDAO;
import entity.Book;
import entity.Category;
import entity.Comment;
import entity.ExportedBook;
import entity.Order;
import entity.User;
import util.MaHoa;



/**
 * Servlet implementation class KhachHangController
 */
@MultipartConfig
@WebServlet("/khach-hang")
public class KhachHangController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KhachHangController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		String action = request.getParameter("action");
		if (action.equals("view-book")) {
			viewBook(request, response);
		}else if(action.equals("add-to-cart")) {
			addToCart(request, response);
		}else if(action.equals("view-cart")) {
			viewCart(request, response);
		}else if(action.equals("delete-cart")) {
			deleteCart(request, response);
		}else if(action.equals("update-cart")) {
			updateCart(request, response);
		}else if(action.equals("checkout")) {
			checkout(request,response);
		}else if(action.equals("make-order")) {
			makeOrder(request,response);
		}else if(action.equals("view-order-all")) {
			viewOrderAll(request,response);
		}else if(action.equals("view-order-confirmed")) {
			viewOrderConfirmed(request,response);
		}else if(action.equals("view-order-waiting")) {
			viewOrderWaiting(request,response);
		}else if(action.equals("view-order-cancelled")) {
			viewOrderCancelled(request,response);
		}else if(action.equals("category")) {
			category(request,response);
		}else if(action.equals("add-comment")) {
			addComment(request,response);
		}else if(action.equals("change-information")) {
			changeInformation(request,response);
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
		}else if(action.equals("cancel-order")) {
			cancelOrder(request, response);
		}else if(action.equals("change-pass")) {
			changePass(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}
	private void viewBook(HttpServletRequest request, HttpServletResponse response) {
		try {
			String bookid = request.getParameter("bid");
			int bid = Integer.parseInt(bookid);
			int checkView = 1;
			CategoryDAO cd = new CategoryDAO();
			BookDAO bd = new BookDAO();
			CommentDAO cmtd = new CommentDAO();
			Book bookDetail = new Book();
			ArrayList<Category> listC = new ArrayList<Category>();
			ArrayList<Comment> listCmt = new ArrayList<Comment>();
			bookDetail = bd.selectById(bid);
			
			listC = cd.selectAll();
			listCmt = cmtd.selectByIdBook(bid);
			
			int countStar = 0;
			int count5 = 0;
			int count4 = 0;
			int count3 = 0;
			int count2 = 0;
			int count1 = 0;
			int sum = 0;
			
			double avg = 0;
			double c5=0;
			double c4=0;
			double c3=0;
			double c2=0;
			double c1=0;
			if(listCmt.size() > 0) {
				for(Comment c : listCmt) {
					if(c.getNumstar() == 5) {
						sum += 5;
						count5+=1;
						countStar+=1;
					}
					if(c.getNumstar() == 4) {
						sum += 4;
						count4+=1;
						countStar+=1;
					}
					if(c.getNumstar() == 3) {
						sum += 3;
						count3+=1;
						countStar+=1;
					}
					if(c.getNumstar() == 2) {
						sum += 2;
						count2+=1;
						countStar+=1;
					}
					if(c.getNumstar() == 1) {
						sum += 1;
						count1+=1;
						countStar+=1;
					}
				}
				
				avg = (double) Math.round((sum/countStar) * 10) / 10;
				c5 = count5*100/countStar;
				c4 = count4*100/countStar;
				c3 = count3*100/countStar;
				c2 = count2*100/countStar;
				c1 = count1*100/countStar;
			}else {
				countStar = 1;
				c5 = count5*100/countStar;
				c4 = count4*100/countStar;
				c3 = count3*100/countStar;
				c2 = count2*100/countStar;
				c1 = count1*100/countStar;
				countStar = 0;
				
			}
			
			request.setAttribute("bookDetail", bookDetail);
			request.setAttribute("listC", listC);
			request.setAttribute("listCmt", listCmt);
			request.setAttribute("checkView", checkView);
			request.setAttribute("avg", avg);
			request.setAttribute("countStar", countStar);
			request.setAttribute("count5", count5);
			request.setAttribute("count4", count4);
			request.setAttribute("count3", count3);
			request.setAttribute("count2", count2);
			request.setAttribute("count1", count1);
			request.setAttribute("c5", c5);
			request.setAttribute("c4", c4);
			request.setAttribute("c3", c3);
			request.setAttribute("c2", c2);
			request.setAttribute("c1", c1);
			
			request.getRequestDispatcher("/khachhang/client-bookdetail.jsp").forward(request, response);
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void addToCart(HttpServletRequest request, HttpServletResponse response) {
		try {
			BookDAO bd = new BookDAO();
			int quantity = 1;
			int id;
			if(request.getParameter("bid")!=null) {
				id = Integer.parseInt(request.getParameter("bid"));
				Book book = bd.selectById(id);
				if(book != null) {
					if(request.getParameter("quantity")!=null) {
						quantity = Integer.parseInt(request.getParameter("quantity"));
					}
					HttpSession session = request.getSession();
					if(session.getAttribute("order")==null) {
						Order order = new Order();
						List<ExportedBook> listItems = new ArrayList<ExportedBook>();
						ExportedBook item = new ExportedBook();
						item.setQuantity(quantity);
						item.setBook(book);
						item.setPrice(book.getPrice());
						listItems.add(item);
						order.setItems(listItems);
						session.setAttribute("order", order);
					}else {
						Order order = (Order)session.getAttribute("order");
						List<ExportedBook> listItems = order.getItems();
						boolean check = false;
						for(ExportedBook item : listItems) {
							if(item.getBook().getBid() == book.getBid()) {
								item.setQuantity(item.getQuantity()+quantity);
								check = true;
							
							}
						}
						if(check == false) {
							ExportedBook item = new ExportedBook();
							item.setQuantity(quantity);
							item.setBook(book);
							item.setPrice(book.getPrice());
							listItems.add(item);
							
							
						}
						
						session.setAttribute("order", order);
					}
					response.sendRedirect("home");
				}else {
					response.sendRedirect("home");
				}
			}
		} catch (NumberFormatException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void viewCart(HttpServletRequest request, HttpServletResponse response) {
		try {
			HttpSession session = request.getSession();
			Order order = (Order)session.getAttribute("order");
			int total = 0;
			if(order!=null && order.getItems().size() >0) {
				for(ExportedBook i:order.getItems()) {
					total += i.getPrice()*i.getQuantity();
				}
			}else {
				RequestDispatcher dispatcher = request.getRequestDispatcher("/khachhang/cart-list.jsp");
				dispatcher.forward(request, response);
			}
			order.setTotal(total);
			session.setAttribute("order", order);
			RequestDispatcher dispatcher = request.getRequestDispatcher("/khachhang/cart-list.jsp");
			dispatcher.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	private void deleteCart(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int id=0;
		if(request.getParameter("bid")!=null) {
			id = Integer.parseInt(request.getParameter("bid"));
		}
		HttpSession session = request.getSession();
		Order order = (Order)session.getAttribute("order");
		List<ExportedBook> listItems = order.getItems();
		int t = 0;
		for(ExportedBook item : listItems) {
			
			if(item.getBook().getBid() == id) {
				break;
			}
			t+=1;
		}
		listItems.remove(t);
		session.setAttribute("order", order);
//		RequestDispatcher dispatcher = request.getRequestDispatcher("/khachhang/cart-list.jsp");
//		dispatcher.forward(request, response); //tại sao ko lấy 2 dòng này mà lại lấy dòng dưới, thử xem lỗi sẽ sinh ra khi ta xóa trong cart-list rồi tải lại trang(Ctr+R) --> 500 error
		viewCart(request, response);
	}
	private void updateCart(HttpServletRequest request, HttpServletResponse response) {
		int id=0;
		if(request.getParameter("bid")!=null) {
			id = Integer.parseInt(request.getParameter("bid"));
		}
		int quantity = Integer.parseInt(request.getParameter("quantity"));
		HttpSession session = request.getSession();
		Order order = (Order)session.getAttribute("order");
		List<ExportedBook> listItems = order.getItems();
		
		for(ExportedBook item : listItems) {
			
			if(item.getBook().getBid() == id) {
				item.setQuantity(quantity);
				break;
			}
			
		}
		
		session.setAttribute("order", order);
		viewCart(request, response);
	}
	private void checkout(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		RequestDispatcher dispatcher = request.getRequestDispatcher("/khachhang/check-out.jsp");
		dispatcher.forward(request, response);
	}
	private void makeOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String deliveryaddress = request.getParameter("deliveryaddress");
		HttpSession session = request.getSession();
		Order order = (Order)session.getAttribute("order");
		order.setDeliveryaddress(deliveryaddress);
		
		User user = (User)session.getAttribute("user");
		order.setUser(user);
		
		BookDAO bd = new BookDAO();
		OrderDAO od = new OrderDAO();
		int oid = od.insert(order);
		
		ExportedBookDAO itd = new ExportedBookDAO();
		for(ExportedBook i:order.getItems()) {
			i.setOid(oid);
			itd.insert(i);
		}
		
		order = null;
		session.setAttribute("order", order);
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/khachhang/order-success.jsp");
		dispatcher.forward(request, response);
	}
	private void viewOrderAll(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		ArrayList<Order> listAllOrderOfUser = new ArrayList<Order>();
		OrderDAO od = new OrderDAO();
		listAllOrderOfUser = od.selectAllOrderOfUser(user.getUid());
		request.setAttribute("listAllOrderOfUser", listAllOrderOfUser);
		request.setAttribute("viewOrderAll", true);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/khachhang/view-order.jsp");
		dispatcher.forward(request, response);
		
	}
	private void viewOrderConfirmed(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		ArrayList<Order> listAllOrderOfUserConfirmed = new ArrayList<Order>();
		
		OrderDAO od = new OrderDAO();
		listAllOrderOfUserConfirmed = od.selectByStatusOfOrder(1,user.getUid());
		
		request.setAttribute("listAllOrderOfUserConfirmed", listAllOrderOfUserConfirmed);
		request.setAttribute("viewOrderOfUserConfirmed", true);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/khachhang/view-order.jsp");
		dispatcher.forward(request, response);
		
	}
	private void viewOrderWaiting(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		ArrayList<Order> listAllOrderOfUserWaitingConfirmed = new ArrayList<Order>();
		
		OrderDAO od = new OrderDAO();
		listAllOrderOfUserWaitingConfirmed = od.selectByStatusOfOrder(0,user.getUid());
		
		request.setAttribute("listAllOrderOfUserWaitingConfirmed", listAllOrderOfUserWaitingConfirmed);
		request.setAttribute("viewOrderOfUserWaitingConfirmed", true);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/khachhang/view-order.jsp");
		dispatcher.forward(request, response);
		
	}
	private void viewOrderCancelled(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");

		ArrayList<Order> listAllOrderOfUserCancelled = new ArrayList<Order>();
		
		OrderDAO od = new OrderDAO();
		listAllOrderOfUserCancelled = od.selectByStatusOfOrder(2,user.getUid());
		
		request.setAttribute("listAllOrderOfUserCancelled", listAllOrderOfUserCancelled);
		request.setAttribute("viewOrderOfUserCancelled", true);
		
		
		RequestDispatcher dispatcher = request.getRequestDispatcher("/khachhang/view-order.jsp");
		dispatcher.forward(request, response);
		
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
		
		request.getRequestDispatcher("/khachhang/index.jsp").forward(request, response);
	}
	private void addComment(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int star = 0;
		try {
			String starString = request.getParameter("star");
			star = Integer.parseInt(starString);
		} catch (Exception e) {
			// TODO: handle exception
		}
		
		String uidString = request.getParameter("uid");
		int uid = Integer.parseInt(uidString);
		String bidString = request.getParameter("bidC");
		int bid = Integer.parseInt(bidString);
		String comment = request.getParameter("comment");
		
		
		
		CommentDAO cmtd = new CommentDAO();
		Comment cmt = new Comment(0, uid, bid, star, comment, null);
		cmtd.insert(cmt);
		
		int checkView = 1;
		CategoryDAO cd = new CategoryDAO();
		BookDAO bd = new BookDAO();
		
		
		Book bookDetail = new Book();
		ArrayList<Category> listC = new ArrayList<Category>();
		ArrayList<Comment> listCmt = new ArrayList<Comment>();
		
		bookDetail = bd.selectById(bid);
		
		listC = cd.selectAll();
		listCmt = cmtd.selectByIdBook(bid);
		
		int countStar = 0;
		int count5 = 0;
		int count4 = 0;
		int count3 = 0;
		int count2 = 0;
		int count1 = 0;
		int sum = 0;
		double avg = 0;
		double c5=0;
		double c4=0;
		double c3=0;
		double c2=0;
		double c1=0;
		if(listCmt.size() > 0) {
			for(Comment c : listCmt) {
				if(c.getNumstar() == 5) {
					sum += 5;
					count5+=1;
					countStar+=1;
				}
				if(c.getNumstar() == 4) {
					sum += 4;
					count4+=1;
					countStar+=1;
				}
				if(c.getNumstar() == 3) {
					sum += 3;
					count3+=1;
					countStar+=1;
				}
				if(c.getNumstar() == 2) {
					sum += 2;
					count2+=1;
					countStar+=1;
				}
				if(c.getNumstar() == 1) {
					sum += 1;
					count1+=1;
					countStar+=1;
				}
			}
			
			avg = (double) Math.round((sum/countStar) * 10) / 10;
			c5 = count5*100/countStar;
			c4 = count4*100/countStar;
			c3 = count3*100/countStar;
			c2 = count2*100/countStar;
			c1 = count1*100/countStar;
		}else {
			countStar = 1;
			c5 = count5*100/countStar;
			c4 = count4*100/countStar;
			c3 = count3*100/countStar;
			c2 = count2*100/countStar;
			c1 = count1*100/countStar;
			countStar = 0;
			
		}
		
		request.setAttribute("bookDetail", bookDetail);
		request.setAttribute("listC", listC);
		request.setAttribute("checkView", checkView);
		request.setAttribute("listCmt", listCmt);
		request.setAttribute("avg", avg);
		request.setAttribute("countStar", countStar);
		request.setAttribute("count5", count5);
		request.setAttribute("count4", count4);
		request.setAttribute("count3", count3);
		request.setAttribute("count2", count2);
		request.setAttribute("count1", count1);
		request.setAttribute("c5", c5);
		request.setAttribute("c4", c4);
		request.setAttribute("c3", c3);
		request.setAttribute("c2", c2);
		request.setAttribute("c1", c1);
		
		request.getRequestDispatcher("/khachhang/client-bookdetail.jsp").forward(request, response);
	}
	private void changeInformation(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException {
		String uid = request.getParameter("uid");
		String name = request.getParameter("name");
		String gender = request.getParameter("gender");
		String dob = request.getParameter("dob");
		String address = request.getParameter("address");
		String phone = request.getParameter("phone");
		
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		user.setName(name);
		user.setGender(gender);
		if(dob != "" && dob.length()>0) {
			user.setDob(Date.valueOf(dob));
		}
		user.setAddress(address);
		user.setPhone(phone);
		
		Part file = request.getPart("imageFile");
		if(file != null && file.getSubmittedFileName().lastIndexOf(".") < file.getSubmittedFileName().length()-1) {
			String imageFileNameX = file.getSubmittedFileName();
			System.out.println("Selected Image File Name: "+imageFileNameX);
			
			int lastIndexOfDot = imageFileNameX.lastIndexOf(".");
			long millis = System.currentTimeMillis();
			String imageFileName = uid+"."+imageFileNameX.substring(lastIndexOfDot + 1);

			String uploadPath = "C:/Users/acer/eclipse-workspace/BaiTapLonbackup/src/main/webapp/avatar/"+imageFileName;
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
			
			imageFileName="avatar/"+imageFileName;
			user.setPhotoPath(imageFileName);
		}
		
		UserDAO ud = new UserDAO();
		ud.update(user);
		session.setAttribute("user", user);
		request.setAttribute("checkChangeInfor", 1);
		try {
			Thread.sleep(5000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//response.sendRedirect("khachhang/changeInformation.jsp");
		request.getRequestDispatcher("/khachhang/changeInformation.jsp").forward(request, response);
		
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
		request.getRequestDispatcher("/khachhang/index.jsp").forward(request, response);
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
        
        request.getRequestDispatcher("/khachhang/index.jsp").forward(request, response);
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
        request.getRequestDispatcher("/khachhang/index.jsp").forward(request, response);
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
        
        request.getRequestDispatcher("/khachhang/index.jsp").forward(request, response);
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
        request.getRequestDispatcher("/khachhang/index.jsp").forward(request, response);
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
		request.getRequestDispatcher("/khachhang/index.jsp").forward(request, response);
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
		request.getRequestDispatcher("/khachhang/index.jsp").forward(request, response);
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
        
        request.getRequestDispatcher("/khachhang/index.jsp").forward(request, response);
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
        
        
        request.getRequestDispatcher("/khachhang/index.jsp").forward(request, response);
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
        
        request.getRequestDispatcher("/khachhang/index.jsp").forward(request, response);
	}
	private void cancelOrder(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		int oid = Integer.parseInt(request.getParameter("oid"));
		OrderDAO od = new OrderDAO();
		Order order = od.select(oid);
		order.setStatus(2);
		od.update(order);
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		ArrayList<Order> listAllOrderOfUserWaitingConfirmed = new ArrayList<Order>();
		listAllOrderOfUserWaitingConfirmed = od.selectByStatusOfOrder(0, user.getUid());
		request.setAttribute("listAllOrderOfUserWaitingConfirmed", listAllOrderOfUserWaitingConfirmed);
		request.setAttribute("viewOrderOfUserWaitingConfirmed", true);
		request.getRequestDispatcher("/khachhang/view-order.jsp").forward(request, response);
	}
	private void changePass(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String curpass = request.getParameter("curpass");
		String newpass = request.getParameter("newpass");
		String confnewpass = request.getParameter("confnewpass");
		String baoLoi = "";
		HttpSession session = request.getSession();
		User user = (User)session.getAttribute("user");
		if(user.getPassword().equals(MaHoa.toSHA1(curpass))==false) {
			baoLoi = "Mật khẩu hiện tại sai";
		}else if(newpass.equals(confnewpass) == false) {
			baoLoi = "Mật khẩu mới không khớp nhau";
		}else {
			UserDAO ud = new UserDAO();
			user.setPassword(MaHoa.toSHA1(newpass));
			int k = ud.changePass(user);
			session.setAttribute("user", user);
			baoLoi = "khong";
		}
		request.setAttribute("newpass", newpass);
		request.setAttribute("curpass", curpass);
		request.setAttribute("confnewpass", confnewpass);
		request.setAttribute("baoLoi", baoLoi);
		request.getRequestDispatcher("/khachhang/change-pass.jsp").forward(request, response);
	}
}
