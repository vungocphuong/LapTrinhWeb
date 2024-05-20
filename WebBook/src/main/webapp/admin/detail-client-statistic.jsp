<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.User"%>
<%@page import="entity.Order"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="com.google.gson.Gson"%>
<%@ page import="com.google.gson.JsonObject"%>
<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>VNP-ShopBook</title>
<link rel="stylesheet" href="./assets/css/view-order.css">
<link rel="stylesheet" href="./assets/css/normalize.min.css">
<link rel="stylesheet" href="./assets/css/base.css">
<link rel="stylesheet" href="./assets/css/main.css">
<link rel="stylesheet" href="./assets/css/grid.css">
<link rel="stylesheet" href="./assets/css/responsive.css">
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
                	<span style="color: white;font-size: 40px;">ĐƠN HÀNG THỐNG KÊ</span>
                </div>
        </header>
    </div>
    <div style="display: flex;">
    	<a href="<%=url%>/admin-ctr?action=statistic-client-by-revenue&start=${start}&end=${end}" class="button button1">Quay lại</a>
    </div>
    <div  class="">
    	<c:forEach items="${listDetailOrder}" var="o">
	    <div class="orderI">
	    		<h4>Tên KH:${o.user.name}</h4>
		    	<h4>Địa chỉ:${o.user.address}</h4>
		    	<h4>Số điện thoại:${o.user.phone}</h4>
	    		<c:forEach items="${o.items}" var="i">
	      		<div class="row">
	          		<div class="col-sm-2 hidden-xs" style="margin-top: 5px;"><img src="${i.book.coverurl}" alt="..." class="img-responsive" width="90" height="100"/></div>
		          	<div class="col-sm-4">
			            <h4 class="nomargin">${i.book.title}</h4>
			            <h6 class="nomargin">Tác giả: ${i.book.author}</h6>
			            <h6 class="nomargin">Giá: ${i.price}vnd</h6>
			            <h6 class="nomargin">Số lượng: ${i.quantity}</h6>
		           </div>
		      </div>
		      </c:forEach>
		    <h4>Ngày đặt: ${o.date_creat}</h4>
		 	<h4>Nơi nhận: ${o.deliveryaddress}</h4>
		 	<h4>Tổng tiền: ${o.total}</h4>
		</div>
		</c:forEach>
    </div>

</body>
</html>