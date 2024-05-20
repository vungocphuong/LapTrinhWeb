package control;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import dao.UserDAO;
import entity.User;

/**
 * Servlet implementation class Authenticate
 */
@MultipartConfig
@WebServlet("/authenticate")
public class Authenticate extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Authenticate() {
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
		try {
			String username = request.getParameter("username");
			String verificationCode = request.getParameter("verificationCode");

			UserDAO ud = new UserDAO();
			
			User user = ud.selectByUserName(username);
			
			String msgError = "";
			String msgSuccess = "";
			if (user != null) {
				// Kiem tra ma xac thuc co giong nhau hay khong? // Kiem tra xem ma xac thuc con hieu luc hay khong?
				if(user.getVerificationCode().equals(verificationCode)) {
					// Thanh Cong
					user.setAuthenticationStatus(true);
					ud.updateVerifyInformation(user);
					msgSuccess ="Xác thực thành công, hệ thống quay lại trang chủ sau 5s!";
				}else {
					// That Bai
					msgError ="Xác thực không thành công!";
				}
			}else {
				msgError ="Tài khoản không tồn tại!";
			}
			String url = "/authenticate.jsp";
			request.setAttribute("msgError", msgError);
			request.setAttribute("msgSuccess", msgSuccess);
			request.setAttribute("username", username);
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (ServletException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
