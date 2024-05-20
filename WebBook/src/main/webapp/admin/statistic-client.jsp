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

<link rel="stylesheet" href="<%=url%>/assets/css/normalize.min.css">
<link rel="stylesheet" href="<%=url%>/assets/css/main1.css">
<link rel="stylesheet" href="<%=url%>/assets/css/base.css">
<link rel="stylesheet" href="<%=url%>/assets/css/main.css">
<link rel="stylesheet" href="<%=url%>/assets/css/grid.css">
<link rel="stylesheet" href="<%=url%>/assets/css/responsive.css">
<link rel="stylesheet" href="<%=url%>/assets/font/fontawesome-free-6.1.1/css/all.min.css">
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
                    <label for="mobile-search-checkbox" class="header__mobile-search">
                        <i class="header__mobile-search-icon fa-solid fa-magnifying-glass"></i>
                    </label>

                    <div class="header__logo hide-on-tablet">
                        <a href="./" class="header__logo-link">
                            <img viewBox="0 0 192 65" class="header__logo-img shopee-svg-icon header-with-search__shopee-logo icon-shopee-logo" src="https://static.vecteezy.com/system/resources/previews/015/079/415/original/3d-bookstore-icon-png.png" width="30" height="90">
                        </a>
                    </div>
                    <span style="color: white;font-size: 40px;">THỐNG KÊ KHÁCH HÀNG</span>
                </div>
            </div>    
        </header>
        
     <div class="mainStatistic">
     	<form action="<%=url%>/admin-ctr?action=statistic-client-by-revenue" method="post" enctype="multipart/form-data">
     		<label for="date"><h4>Nhập ngày bắt đầu:</h4></label>
			<input type="date" name="start" value="${start }">
			<label for="date"><h4>Nhập ngày kết thúc:</h4></label>
			<input type="date" name="end" value="${end }">
			<input type="submit" class="button button4" value="Lọc"/>
			<a href="<%=url %>/home" class="button button4">Quay lại</a>
     	</form>
		<div id="data">
			<table class="content-table">
			  <thead>
			    <tr>
			      <th>UID</th>
			      <th>Name</th>
			      <th>Address</th>
			      <th>Phone</th>
			      <th>Email</th>
			      <th>Total revenue</th>
			      <th>Xem chi tiết</th>
			    </tr>
			  </thead>
			  <tbody>
			    
			   	<c:forEach items="${listStaticClient}" var="o">
			   		<tr>
			    		<td>${o.uid}</td>
				      	<td>${o.name}</td>
				      	<td>${o.address}</td>
				      	<td>${o.phone}</td>
				      	<td>${o.email}</td>
				      	<td>${o.revenue}</td>
				      	<td><input type="button" class="button button4"  value="Chọn" onclick="window.location.href='<%=url %>/admin-ctr?action=view-detailed-client-statistics&start=${start}&end=${end}&uid=${o.uid}'"/></td>
				    </tr>
			    </c:forEach>
			   
			  </tbody>
			</table>
		</div>
     </div>
    </div>
	
</body>
</html>