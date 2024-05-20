<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.User"%>
<%@page import="entity.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>VNP-ShopBook</title>
<link rel="shortcut icon" href="" type="image/x-icon">
<link rel="stylesheet" href="./assets/css/normalize.min.css">
<link rel="stylesheet" href="./assets/css/base.css">
<link rel="stylesheet" href="./assets/css/main.css">
<link rel="stylesheet" href="./assets/css/grid.css">
<link rel="stylesheet" href="./assets/css/responsive.css">
<link rel="stylesheet" href="./assets/css/cart-list.css">
<link rel="stylesheet" href="./assets/font/fontawesome-free-6.1.1/css/all.min.css">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap&subset=vietnamese"
    rel="stylesheet">
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-4bw+/aepP/YC94hEpVNVgiZdgIC5+VKNBQNGCHeKRQN+PtmoHDEXuppvnDJzQIu9" crossorigin="anonymous">
<link href="//maxcdn.bootstrapcdn.com/font-awesome/4.1.0/css/font-awesome.min.css" rel="stylesheet">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.1/dist/js/bootstrap.min.js" integrity="sha384-Rx+T1VzGupg4BHQYs2gCW9It+akI2MM/mndMCy36UVfodzcJcF0GGLxZIzObiEfa" crossorigin="anonymous"></script>

</head>
<body>
	<div class="app">
        <!-- Header -->
        <header class="header">
            <div class="grid wide">
                <nav class="header__navbar hide-on-mobile-tablet">
                    <ul class="header__navbar-list">
                        <li class="header__navbar-item">
                            <a href="https://www.facebook.com/vnp.tryhackme"
                                class="header__navbar-item-link header__navbar-item--separate">Kênh người bán</a>
                        </li>
                        <li class="header__navbar-item">
                            <a href="https://www.facebook.com/vnp.tryhackme"
                                class="header__navbar-item-link header__navbar-item--separate">Trở thành Người bán ShopBook
                                </a>
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
                            <a href="https://www.facebook.com/vnp.tryhackme" class="header__navbar-icon-link">
                                <i class="header__navbar-icon fa-brands fa-facebook"></i>
                            </a>
                            <a href="https://www.facebook.com/vnp.tryhackme" class="header__navbar-icon-link">
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
                        </li>
                        <li class="header__navbar-item">
                            <a href="https://www.facebook.com/vnp.tryhackme" class="header__navbar-item-link">
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
                	<span style="color: white;font-size: 40px;">GIỎ HÀNG</span>
                </div>
        </header>
    </div>
    <%
	    Object obj1 = session.getAttribute("order");
		Order order = null;
		if (obj1!=null)
			order = (Order)obj1;
		
		if(order!=null && order.getItems().size() > 0){
    %>
    <div class="container" style="width:1600px;margin-top: 150px;">
	  <table id="cart" class="table table-hover table-condensed">
	    <thead>
	      <tr>
	        <th style="width:48%;" class="text-center">Sách/Truyện</th>
	        <th style="width:12%">Price(vnđ)</th>
	        <th style="width:8%">Quantity</th>
	        <th style="width:12%" class="text-center">Subtotal</th>
	        <th style="width:20%"></th>
	      </tr>
	    </thead>
	    <tbody>
	      <c:forEach items="${order.items}" var="item">
	      	<form class="form" method="post" name="form_cart_list" id="form_cart_list" enctype="multipart/form-data" action="khach-hang">
	      		<input type="hidden" name="action" value="update-cart"/>
	      		<tr>
		        <td data-th="Product">
		          <div class="row">
		            <div class="col-sm-2 hidden-xs"><img src="${item.book.coverurl}" alt="..." class="img-responsive" width="100"/></div>
		            <div class="col-sm-10">
		              <h4 class="nomargin">${item.book.title}</h4>
		              <h6 class="nomargin">Tác giả:${item.book.author}</h6>
		              
		            </div>
		          </div>
		        </td>
		        <td data-th="Price" style="margin-top:10px;">${item.book.price}</td>
		        <td data-th="Quantity">
		          <input type="hidden" id="bid" name="bid" value="${item.book.bid}">
		          <input type="number" class="form-control text-center" value="${item.quantity}" id="quantity" name="quantity">
		        </td>
		        <td data-th="Subtotal" class="text-center">${item.book.price*item.quantity}</td>
		        <td class="actions" data-th="">
				  <input type="submit" value="Update" class="button button1">
		          <a href="khach-hang?bid=${item.book.bid}&&action=delete-cart" role="button" class="button button3">Delete</a>
		        </td>
		      </tr>
	      	</form>
	      </c:forEach>
	    </tbody>
	    <tfoot>
	      <tr class="visible-xs">
	        <td class="text-center"><strong>Total ${order.total}</strong></td>
	      </tr>
	      <tr>
	        <td><a href="home" class="button button2"><i class="fa fa-angle-left"></i> Continue Shopping</a></td>
	        <td colspan="2" class="hidden-xs"></td>
	        <td class="hidden-xs text-center"><strong>Total ${order.total}</strong></td>
	        <td><a href="khach-hang?action=checkout" class="button button1">Checkout <i class="fa fa-angle-right"></i></a></td>
	      </tr>
	    </tfoot>
	  </table>
	</div>
	<%}else{ %>
		<div class="container" style="width:1600px;margin-top: 150px;">
		  <table id="cart" class="table table-hover table-condensed">
		    <thead>
		      <tr>
		        <th style="width:48%;" class="text-center">Sách/Truyện</th>
		        <th style="width:12%">Price(vnđ)</th>
		        <th style="width:8%">Quantity</th>
		        <th style="width:12%" class="text-center">Subtotal</th>
		        <th style="width:20%"></th>
		      </tr>
		    </thead>
		    <tbody>
		      
		    </tbody>
		    
		  </table>
		  <h3>Không có sản phẩm nào trong giở hàng</h3>
		  <h3>Quay trở về <a href="home">trang tru</a></h3>
		</div>
	<%} %>
</body>
</html>