package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.User;
import util.MaHoa;

/**
 * Servlet implementation class Login
 */
@WebServlet("/login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Login() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html;charset=UTF-8");
		String username = request.getParameter("username");
		String password = request.getParameter("pass");
		//matKhau = MaHoa.toSHA1(matKhau);
		
		User u = new User();
		u.setUsername(username);
		//u.setPassword(password);
		u.setPassword(MaHoa.toSHA1(password));
		UserDAO ud = new UserDAO();
		User user = ud.selectByUserNameAndPassWord(u);

		if (user != null && user.isAuthenticationStatus()) {
			// nhớ là request chỉ đi được 1 trang thôi còn HttpSesion nó đi được nhiều trang và nó có thể lưu trữ được
			HttpSession session = request.getSession(); // tạo phiên lưu trữ
			session.setAttribute("user", user); // phiên sẽ lưu trữ đối tượng khachHang 
			response.sendRedirect("home");
		}else {
			request.setAttribute("baoLoi", "Tên đăng nhập hoặc mật khẩu không đúng / hoặc Tài khoản chưa xác thực!");
			RequestDispatcher rd = getServletContext().getRequestDispatcher("/login.jsp");
			rd.forward(request, response);
		}
		
//		RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
//		rd.forward(request, response);
	}

}
