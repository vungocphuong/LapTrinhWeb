package control;

import java.io.IOException;
import java.sql.Date;
import java.util.Calendar;
import java.util.Random;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.UserDAO;
import entity.User;
import util.Email;
import util.MaHoa;
import util.RandomNumber;

/**
 * Servlet implementation class Register
 */
@WebServlet("/register")
public class Register extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Register() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		response.setContentType("text/html;charset=UTF-8");
		request.setCharacterEncoding("UTF-8");
		try {
			String username = request.getParameter("username");
			String pass = request.getParameter("pass");
			String repass = request.getParameter("repass");
			String name = request.getParameter("name");
			String gender = request.getParameter("gender");
			String dob = request.getParameter("dob");
			String address = request.getParameter("address");
			String phone = request.getParameter("phone");
			String email = request.getParameter("email");
			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			//request.setAttribute("username", username);
			request.setAttribute("name", name);
			request.setAttribute("gender", gender);
			request.setAttribute("dob", dob);
			request.setAttribute("address", address);
			request.setAttribute("phone", phone);
			
			String url = "";

			String baoLoi = "";
			UserDAO ud = new UserDAO();

			if (ud.checkUsername(username)) {
				baoLoi += "Tên đăng nhập đã tồn tại, vui lòng chọn tên đăng nhập khác.<br/>";
			}

			if (!pass.equals(repass)) {
				baoLoi += "Mẫu khẩu không khớp.<br/>";
			} else {
				//pass = MaHoa.toSHA1(matKhau);
			}

			request.setAttribute("baoLoi", baoLoi);

			if (baoLoi.length() > 0) {
				url = "/register.jsp";
			} else {
				Random rd = new Random();
				//String maKhachHang = System.currentTimeMillis() + rd.nextInt(1000) + "";
//				KhachHang kh = new KhachHang(maKhachHang, tenDangNhap, matKhau, hoVaTen, gioiTinh, diaChiKhachHang,
//						diaChiNhanHang, diaChiMuaHang, Date.valueOf(ngaySinh), dienThoai, email, dongYNhanMail != null);
				User user  = new User(0, username, MaHoa.toSHA1(pass), email, name, gender, phone, address, null, null, false, false, Date.valueOf(dob), null);
				if (ud.insert(user) > 0) {
										
					// Day so xac thuc
					String randomNumber = RandomNumber.getRandomNumber();

					// Quy dinh thoi gian hieu luc
					Date todaysDate = new Date(new java.util.Date().getTime());
					Calendar c = Calendar.getInstance();
					c.setTime(todaysDate);
					c.add(Calendar.DATE, 1);
					Date authenticationCodeLifetime = new Date(c.getTimeInMillis());

					// Trang thai xac thuc = false		 			
					boolean authenticationStatus = false;
					
					user.setVerificationCode(randomNumber);
					user.setAuthenticationCodeLifetime(authenticationCodeLifetime);
					user.setAuthenticationStatus(authenticationStatus);
					
					
					if(ud.updateVerifyInformation(user)>0) {
						// Gui email cho khach hang
						Email.sendEmail(user.getEmail(), "Xác thực tài khoản tại VNP.vn", getNoiDung(user));
					}
				}
				url = "/authenticate.jsp";
			}
			RequestDispatcher rd = getServletContext().getRequestDispatcher(url);
			rd.forward(request, response);
		} catch (ServletException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}

	}
	public static String getNoiDung(User user) {
		String link = "http://localhost:8080/BaiTapLonbackup/authenticate?username="+user.getUsername()+"&verificationCode="+user.getVerificationCode();
		String noiDung = "<p>VNP.vn xin ch&agrave;o bạn <strong>"+user.getName()+"</strong>,</p>\r\n"
				+ "<p>Vui l&ograve;ng x&aacute;c thực t&agrave;i khoản của bạn bằng c&aacute;ch nhập m&atilde; <strong>"+user.getVerificationCode()+"</strong>, hoặc click trực tiếp v&agrave;o đường link sau đ&acirc;y:</p>\r\n"
				+ "<p><a href=\""+link+"\">"+link+"</a></p>\r\n"
				+ "<p>Đ&acirc;y l&agrave; email tự động, vui l&ograve;ng kh&ocirc;ng phản hồi email n&agrave;y.</p>\r\n"
				+ "<p>Tr&acirc;n trọng cảm ơn.</p>";
		return noiDung;
	}
}
