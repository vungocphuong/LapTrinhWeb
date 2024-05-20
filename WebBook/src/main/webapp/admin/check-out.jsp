<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.User"%>
<%@page import="entity.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>VNP-ShopBook</title>
<link rel="shortcut icon" href="" type="image/x-icon">
<link rel="stylesheet" href="<%=url %>/assets/css/normalize.min.css">
<link rel="stylesheet" href="<%=url %>/assets/css/base.css">
<link rel="stylesheet" href="<%=url %>/assets/css/main.css">
<link rel="stylesheet" href="<%=url %>/assets/css/grid.css">
<link rel="stylesheet" href="<%=url %>/assets/css/responsive.css">
<link rel="stylesheet" href="<%=url %>/assets/font/fontawesome-free-6.1.1/css/all.min.css">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap&subset=vietnamese"
    rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>
<style>
	label{
		font-size: 15px;
	}
	.container{
		font-size: 15px;
	}
	.container input{
		font-size: 15px;
	}
	.container textarea {
		font-size: 15px;
	}
	.button {
	  background-color: #4CAF50; /* Green */
	  border: none;
	  color: white;
	  padding: 10px 20px;
	  text-align: center;
	  text-decoration: none;
	  display: inline-block;
	  font-size: 15 px;
	  margin: 4px 2px;
	  transition-duration: 0.4s;
	  cursor: pointer;
	  border-radius: 8px;
	}
	.button1 {
	  background-color: #4CAF50; 
	  color: white; 
	  border: 2px solid while;
	}
	.button1:hover {
	  background-color: white;
	  color: black;
	  border: 2px solid black;
	}
	.button2 {
	  background-color: #FFCC00; 
	  color: white; 
	  border: 2px solid white;
	}
	
	.button2:hover {
	  background-color: white;
	  color: black;
	  border: 2px solid black;
	}
	.red{
		color: red;
	}
</style>
</head>
<body>
	<div class="app">
        <!-- Header -->
        <header class="header">
            <div class="grid wide">
                <nav class="header__navbar hide-on-mobile-tablet">
                    <ul class="header__navbar-list">
                        <li class="header__navbar-item">
                            <a href="https://banhang.shopee.vn/"
                                class="header__navbar-item-link header__navbar-item--separate">Kênh người bán</a>
                        </li>
                        <li class="header__navbar-item">
                            <a href="https://shopee.vn/m/sell-on-shopee"
                                class="header__navbar-item-link header__navbar-item--separate">Trở thành Người bán
                                ShopBook</a>
                        </li>
                        <li class="header__navbar-item header__navbar-item--has-qr header__navbar-item--separate">
                            Tải ứng dụng

                            <!-- Header QR Code -->
                            <div class="header__qr">
                                <img class="header__qr-img" src="./assets/img/qr_code.png" alt="QR code">
                                <div class="header__qr-apps">
                                    <a href="" class="header__qr-link">
                                        <img src="./assets/img/googleplay.png" alt="Google Play"
                                            class="header__qr-download-img">
                                    </a>
                                    <a href="" class="header__qr-link">
                                        <img src="./assets/img/appstore.png" alt="Appstore"
                                            class="header__qr-download-img">
                                    </a>
                                </div>
                            </div>
                        </li>
                        <li class="header__navbar-item">
                            <span class="header__navbar-title--no-pointer">Kết nối</span>
                            <a href="https://www.facebook.com/khoahd7621/" class="header__navbar-icon-link">
                                <i class="header__navbar-icon fa-brands fa-facebook"></i>
                            </a>
                            <a href="https://www.instagram.com/khoahd7621/" class="header__navbar-icon-link">
                                <i class="header__navbar-icon fa-brands fa-instagram"></i>
                            </a>
                        </li>
                    </ul>
                    <ul class="header__navbar-list">
                        <li class="header__navbar-item header__navbar-item--has-notify">
                            <a href="#" class="header__navbar-item-link">
                                <i class="header__navbar-icon far fa-bell"></i>
                                Thông báo
                            </a>

                            <!-- Header Notification  -->
                            <div class="header__notify">
                                <header class="header__notify-header">
                                    <h3>Thông báo mới nhận</h3>
                                </header>
                                <ul class="header__notify-list">
                                    <li class="header__notify-item header__notify-item--viewed">
                                        <a href="" class="header__notify-link">
                                            <img src="https://www.vietskin.vn/wp-content/uploads/2020/08/image-skincare-9.jpg"
                                                alt="" class="header__notify-img">
                                            <div class="header__notify-info">
                                                <span class="header__notify-name">Xác thực chính hãng nguồn gốc các sản
                                                    phẩm Ohui</span>
                                                <span class="header__notify-description">Xác thực chính hãng nguồn gốc
                                                    các sản phẩm Ohui</span>
                                            </div>
                                        </a>
                                    </li>
                                    <li class="header__notify-item">
                                        <a href="" class="header__notify-link">
                                            <img src="https://luanvanviet.com/wp-content/uploads/2020/08/hinh-anh-san-pham-la-gi-4.jpg"
                                                alt="" class="header__notify-img">
                                            <div class="header__notify-info">
                                                <span class="header__notify-name">Sale Sốc bộ dưỡng Ohui The First Tái
                                                    tạo trẻ hóa da SALE OFF 70%</span>
                                                <span class="header__notify-description">Siêu sale duy nhất 3 ngày 11 -
                                                    13/12/2022</span>
                                            </div>
                                        </a>
                                    </li>
                                    <li class="header__notify-item">
                                        <a href="" class="header__notify-link">
                                            <img src="http://d20aeo683mqd6t.cloudfront.net/vi/articles/title_images/000/040/760/medium/Hadalabo-5822.jpg?2021"
                                                alt="" class="header__notify-img">
                                            <div class="header__notify-info">
                                                <span class="header__notify-name">DA NHẠY CẢM THÌ CÓ DÙNG ĐƯỢC SECRET
                                                    KHÔNG?</span>
                                                <span class="header__notify-description">"Da mình nhạy cảm thì có dùng
                                                    được Secret không?"</span>
                                            </div>
                                        </a>
                                    </li>
                                    <li class="header__notify-item">
                                        <a href="" class="header__notify-link">
                                            <img src="https://www.thmilk.vn/wp-content/uploads/2021/03/RICE_464x297.jpg"
                                                alt="" class="header__notify-img">
                                            <div class="header__notify-info">
                                                <span class="header__notify-name">BỘ SƯU TẬP PHIÊN BẢN GIỚI HẠN MÙA LỆ
                                                    HỘI 2021</span>
                                                <span class="header__notify-description">BỘ SƯU TẬP PHIÊN BẢN GIỚI HẠN
                                                    MÙA LỆ HỘI 2021</span>
                                            </div>
                                        </a>
                                    </li>
                                </ul>
                                <footer class="header__notify-footer">
                                    <a href="#" class="header__notify-footer-btn">
                                        Xem tất cả
                                    </a>
                                </footer>
                            </div>
                        </li>
                        <li class="header__navbar-item">
                            <a href="https://help.shopee.vn/vn/s" class="header__navbar-item-link">
                                <i class="header__navbar-icon far fa-circle-question"></i>
                                Hỗ trợ
                            </a>
                        </li>
                        <%
								Object obj = session.getAttribute("user");
								User user = null;
								if (obj!=null)
									user = (User)obj;
								
								if(user==null){
						%>
                        <li class="header__navbar-item header__navbar-item--bold header__navbar-item--separate">
                            <a  href="register.jsp" style="text-decoration: none;color: white;">
							Đăng kí
							</a>
                        </li>
                        <li class="header__navbar-item header__navbar-item--bold">
                            <a  href="login.jsp" style="text-decoration: none;color: white;">
							Đăng nhập
							</a>
                        </li>
                        <%}else{ %>
                        <li class="header__navbar-item header__navbar-user">
                            <img src="https://i.pinimg.com/736x/13/2e/a7/132ea72d2bc3b85d90409c9e8f2d0f4a.jpg" alt=""
                                class="header__navbar-user-img">
                            <span class="header__navbar-user-name"><%=user.getUsername() %></span>

                            <ul class="header__navbar-user-menu">
                                <li class="header__navbar-user-item">
                                    <a href="">Tài khoản của tôi</a>
                                </li>
                                <li class="header__navbar-user-item">
                                    <a href="">Địa chỉ của tôi</a>
                                </li>
                                <li class="header__navbar-user-item">
                                    <a href="">Đơn mua</a>
                                </li>
                                <li class="header__navbar-user-item header__navbar-user-item--separate">
                                    <a href="logout">Đăng xuất</a>
                                </li>
                            </ul>
                        </li>
                        <%} %>
                    </ul>
                </nav>

                <!-- Header with search -->
                <div class="header-with-search">
                	<span style="color: white;font-size: 40px;">THANH TOÁN</span>
                </div>
        </header>
    </div>
    <div class="container" style="margin-top: 150px;">
		<form class="form" method="post" name="check_out_form" id="check_out_form" enctype="multipart/form-data" action="<%=url%>/admin-ctr?action=make-receipt">
			<input type="hidden" name="action" value="make-order"/>
			<div class="mb-3">
			    <label for="name" class="form-label">Tên nhà cung cấp</label>
			    <input type="text" class="form-control" id="name" name="name" value="${supplier.name}" disabled>
			</div>
			<div class="mb-3">
			    <label for="address" class="form-label">Địa chỉ</label>
			    <input type="text" class="form-control" id="address" name="address" value="${supplier.address}" disabled>
			</div>
			<div class="mb-3">
			    <label for="phone" class="form-label">Điện thoại</label>
			    <input type="text" class="form-control" id="phone" name="phone" value="${supplier.phone}" disabled>
			</div>
			<h3><b>Review & Payment</b></h3>
			<table id="cart" class="table table-hover table-condensed">
			    <thead>
			      <tr>
			        <th style="width:50%;">Sách/Truyện</th>
			        <th style="width:20%">Price(vnđ)</th>
			        <th style="width:10%">Quantity</th>
			        <th style="width:20%" class="text-center">Subtotal</th>
			        
			      </tr>
			    </thead>
			    <tbody>
			      <c:forEach items="${receipt.items}" var="item">
			      	<form class="form" method="post" name="form_id" id="form_id" enctype="multipart/form-data" action="">
			      		<tr>
				        <td data-th="Product">
				          <div class="row">
				            <div class="col-sm-2 hidden-xs"><img src="<%=url %>/${item.book.coverurl}" alt="..." class="img-responsive" height="100"/></div>
				            <div class="col-sm-10">
				              <h4 class="nomargin">${item.book.title}</h4>
				              <h6 class="nomargin">Tác giả:${item.book.author}</h6>
				              
				            </div>
				          </div>
				        </td>
				        <td data-th="Price" style="margin-top:10px;">${item.price}</td>
				        <td data-th="Quantity">
				          <input type="hidden" id="bid" name="bid" value="${item.book.bid}">
				          <input type="number" class="form-control text-center" value="${item.quantity}" id="quantity" name="quantity" disabled>
				        </td>
				        <td data-th="Subtotal" class="text-center">${item.price*item.quantity}</td>
				        
				      </tr>
			      	</form>
			      </c:forEach>
			    </tbody>
			    <tfoot>
			      
			      <tr>
			        <td><a href="<%=url %>/admin/find-imported-book.jsp" class="button button2"><i class="fa fa-angle-left"></i> Continue Shopping</a></td>
			        <td colspan="2" class="hidden-xs"></td>
			        <td class="hidden-xs text-center"><strong>Total : ${order.total}</strong></td>
			        <td><input type="submit" value="Agree" class="button button1"></td>
			      </tr>
			    </tfoot>
			  </table>
		</form>
	</div>
</body>
</html>