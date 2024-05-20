package control;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BookDAO;
import dao.CategoryDAO;
import entity.Book;
import entity.Category;
import entity.User;



/**
 * Servlet implementation class Home
 */
@WebServlet(name = "Home", urlPatterns = {"/home"})
public class Home extends HttpServlet {
       
    /**
     * @see HttpServlet#HttpServlet()
     */
  
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        //b1: get data from dao
        String indexPage = request.getParameter("indexPage");
        if(indexPage == null) {
        	indexPage = "1";
        }
        int index = Integer.parseInt(indexPage);
        
        
        BookDAO bd = new BookDAO();
        CategoryDAO cd = new CategoryDAO();
        ArrayList<Book> list = new ArrayList<Book>();
        ArrayList<Category> listC = new ArrayList<Category>();
        list = bd.pagingBook(index);
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
        request.setAttribute("checkAll", 1);
        HttpSession session = request.getSession();
        User user = (User)session.getAttribute("user");
        if(user == null || user.isAdmin() == false) {
        	request.getRequestDispatcher("khachhang/index.jsp").forward(request, response);
        }else if(user.isAdmin() == true) {
        	request.getRequestDispatcher("admin/index.jsp").forward(request, response);
        }
        
//        DAO dao = new DAO();
//        List<Product> list = dao.getAllProduct();
//        List<Category> listC = dao.getAllCategory();
//        Product last = dao.getLast();
//        
//        //b2: set data to jsp
//        request.setAttribute("listP", list);
//        request.setAttribute("listCC", listC);
//        request.setAttribute("p", last);
//        request.getRequestDispatcher("Home.jsp").forward(request, response);
        //404 -> url
        //500 -> jsp properties
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		processRequest(request, response);
	}

}
