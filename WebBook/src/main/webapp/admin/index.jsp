<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@page import="entity.User"%>
<%@page import="entity.Category"%>
<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>
<!DOCTYPE html>
<head>
    <meta charset="UTF-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>VNP-ShopBook</title>
    <!-- <link rel="shortcut icon" href="./assets/img/bg-login" type="image/x-icon"> -->
    <link rel="stylesheet" href="<%=url %>/assets/css/normalize.min.css">
    <link rel="stylesheet" href="<%=url %>/assets/css/base.css">
    <link rel="stylesheet" href="<%=url %>/assets/css/main.css">
    <link rel="stylesheet" href="<%=url %>/assets/css/grid.css">
    <link rel="stylesheet" href="<%=url %>/assets/css/responsive.css">
    <link rel="stylesheet" href="<%=url %>/assets/font/fontawesome-free-6.1.1/css/all.min.css">
    <link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap&subset=vietnamese"
        rel="stylesheet">
</head>
<html>
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
                                    <a href="http://localhost:8080/BaiTapLonbackup/khachhang/changeInformation.jsp">Thay đổi thông tin</a>
                                </li>
                                <li class="header__navbar-user-item">
                                    <a href="">Đổi mật khẩu</a>
                                </li>
                                <li class="header__navbar-user-item">
                                    <a href="admin-ctr?action=view-order">Đơn mua</a>
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
                        <a href="home" class="header__logo-link">
                            <img viewBox="0 0 192 65" class="header__logo-img shopee-svg-icon header-with-search__shopee-logo icon-shopee-logo" src="https://static.vecteezy.com/system/resources/previews/015/079/415/original/3d-bookstore-icon-png.png" width="30" height="90">
                        </a>
                    </div>

                    <input type="checkbox" hidden id="mobile-search-checkbox" class="mobile-search-checkbox">
					<form class="header__search" action="admin-ctr?action=search-book" method="post" enctype="multipart/form-data">
	                    <div class="header__search">
	                        <div class="header__search-input-wrap">
	                            <!-- Header search input -->
	                            <input type="text" class="header__search-input" placeholder="Nhập để tìm kiếm sách" name="txtSearch" id="txtSearch" value="${txtSearch}">
	                            
	                        </div>
	                        
	                        <button class="header__search-btn" type="submit">
	                            <i class="header__search-btn-icon fa-solid fa-magnifying-glass"></i>
	                        </button>
	                    </div>
					</form>
                    <!-- Cart layout -->
                    <div class="header__cart">
                        <div class="header__cart-wrap">
                            <a href="khach-hang?action=view-cart"><i class="header__cart-icon fa-solid fa-cart-shopping"></i></a>

                            <span class="header__cart-notice">${receipt.items.size()>0?receipt.items.size():0}</span>

                        </div>
                    </div>
                </div>
            </div>
        </header>
        <!-- Container -->
        <div class="app__container" style="margin-top: 120px;">
            <div class="grid wide">
                <div class="row sm-gutter app_content">
                    <!-- Category left bar -->
                    <div class="col l-2 m-0 c-0">
                        <nav class="category">
                            <h3 class="category__heading">Danh mục</h3>

                            <ul class="category-list">
                            <li class="category-item ${checkAll == 1?"category-item--active":"" }">
                                    <a href="home" class="category-item__link">Tất cả</a>
                            </li>
                            <c:forEach items="${listC}" var="o">
                                <li class="category-item ${tag == o.catid || catid == o.catid?"category-item--active":"" }">
                                    <a href="admin-ctr?action=category&&catid=${o.catid}" class="category-item__link">${o.catname}</a>
                                </li>
                            
                            </c:forEach>
                            </ul>
                            
                        </nav>
                        <nav class="category">
                            <h3 class="category__heading">Quản lý</h3>

                            <ul class="category-list">
                            <li class="category-item">
                                    <a href="admin-ctr?action=view-order-all" class="category-item__link">Đơn mua</a>
                            </li>
                            
                            <li class="category-item">
                                    <a href="admin/find-supplier.jsp" class="category-item__link">Nhập hàng</a>
                            </li>
                            
                            <li class="category-item">
                                    <a href="admin-ctr?action=view-account-of-client" class="category-item__link">Xem tài khoản KH</a>
                            </li>
                            
                            </ul>
                            
                        </nav>
                        <nav class="category">
                            <h3 class="category__heading">Thống kê</h3>

                            <ul class="category-list">
	                            <li class="category-item">
	                                    <a href="admin-ctr?action=statistic-revenue" class="category-item__link">Thống kê doanh thu</a>
	                            </li>
	                            <li class="category-item">
	                                    <a href="admin-ctr?action=statistic-book-by-revenue" class="category-item__link">Thống kê sách</a>
	                            </li>
                            	<li class="category-item">
	                                    <a href="admin-ctr?action=statistic-client-by-revenue" class="category-item__link">Thống kê khách hàng</a>
	                            </li>
                            </ul>
                            
                        </nav>
                    </div>
					
                    <!-- Product -->
                    <div class="col l-10 m-12 c-12">
                        <!-- Filter bar -->
                        <div class="home-filter hide-on-mobile-tablet">
                            <span class="home-filter__label">Sắp xếp theo</span>
                            <%-- <a href="home"><button class="home-filter__btn btn btn ${checkAll == 1?"btn--primary":"" }">Phổ biến</button></a> --%>
                            <!-- <button class="home-filter__btn btn">Mới nhất</button> -->
                            <c:if test="${checkAll == 1 || tagBestSelling == 1}">
                            	<a href="admin-ctr?action=filter-by-best-selling"><button class="home-filter__btn btn btn ${tagBestSelling == 1?"btn--primary":"" }">Bán chạy</button></a>
                            </c:if>
                            <c:if test="${tag == 1 || tagBestSellingBookByCat == 1}">
                            	<a href="admin-ctr?action=filter-by-best-selling-book-by-cat&catid=${catid}"><button class="home-filter__btn btn btn ${tagBestSellingBookByCat == 1?"btn--primary":"" }">Bán chạy</button></a>
                            </c:if>
                            <c:if test="${tagS == 1 || tagBestSellingBookBySearch == 1}">
                            	<a href="admin-ctr?action=filter-by-best-selling-book-by-search&txtSearch=${txtSearch}"><button class="home-filter__btn btn btn ${tagBestSellingBookBySearch == 1?"btn--primary":"" }">Bán chạy</button></a>
                            </c:if>
                            <div class="select-input">
                                <span class="select-input__label" style="${tagIncrease == 1 || tagDecrease == 1 || tagIncreaseAndCat == 1 || tagSIncrease == 1 || tagSDecrease == 1?"color:red;":""}">Giá</span>
                                <i class="select-input__icon fa-solid fa-angle-down"></i>

                                <!-- List options -->
                                <ul class="select-input__list">
                                	<c:if test="${tag == null && tagS == null}">
	                                    <li class="select-input__item">
	                                        <a href="admin-ctr?action=filter-by-price-increase" class="select-input__link" style="${tagIncrease == 1?"color:red;":""}">
	                                            Giá: Thấp đến Cao
	                                        </a>
	                                    </li>
	                                    <li class="select-input__item">
	                                        <a href="admin-ctr?action=filter-by-price-decrease" class="select-input__link" style="${tagDecrease == 1?"color:red;":""}">
	                                            Giá: Cao đến Thấp
	                                        </a>
	                                    </li>
                                    </c:if>
                                    <c:if test="${tag > 0 }">
                                    	<li class="select-input__item">
	                                        <a href="admin-ctr?action=filter-by-price-increase-and-cat&catid=${catid}" class="select-input__link" style="${tagIncreaseAndCat == 1?"color:red;":""}">
	                                            Giá: Thấp đến Cao
	                                        </a>
	                                    </li>
	                                    <li class="select-input__item">
	                                        <a href="admin-ctr?action=filter-by-price-decrease-and-cat&catid=${catid}" class="select-input__link" style="${tagDecreaseAndCat == 1?"color:red;":""}">
	                                            Giá: Cao đến Thấp
	                                        </a>
	                                    </li>
                                    </c:if>
                                    <c:if test="${tagS > 0 }">
                                    	<li class="select-input__item">
	                                        <a href="admin-ctr?action=search-book-by-price-increase&txtSearch=${txtSearch}" class="select-input__link" style="${tagSIncrease == 1?"color:red;":""}">
	                                            Giá: Thấp đến Cao
	                                        </a>
	                                    </li>
	                                    <li class="select-input__item">
	                                        <a href="admin-ctr?action=search-book-by-price-decrease&txtSearch=${txtSearch}" class="select-input__link" style="${tagSDecrease == 1?"color:red;":""}">
	                                            Giá: Cao đến Thấp
	                                        </a>
	                                    </li>
                                    </c:if>
                                </ul>
                                
                            </div>

                            <div class="home-filter__page">
                                <span class="home-filter__page-num">
                                    <span class="home-filter__page-current">${index}</span>/<span
                                        class="home-filter__page-total">${endPage}</span>
                                </span>
                                <div class="home-filter__page-control">
                                	<c:if test="${tag > 0 && tagIncrease == null && tagIncreaseAndCat == null && tagDecrease == null && tagDecreaseAndCat == null}">
			                            <a href="admin-ctr?action=category&catid=${catid}&indexPage=${index-1 >= 1?index-1:index}" class="home-filter__page-btn">
	                                        <i class="home-filter__page-icon fa-solid fa-angle-left"></i>
	                                    </a>
									</c:if>
									<c:if test="${tag == null && tagS == null && tagIncrease == null && tagIncreaseAndCat == null && tagDecrease == null && tagDecreaseAndCat == null && tagBestSelling == null && tagBestSellingBookByCat == null && tagBestSellingBookBySearch == null}">
			                            <a href="home?indexPage=${index-1 >= 1?index-1:index}" class="home-filter__page-btn">
	                                        <i class="home-filter__page-icon fa-solid fa-angle-left"></i>
	                                    </a>
									</c:if>
									<c:if test="${tagBestSelling == 1}">
			                            <a href="admin-ctr?action=filter-by-best-selling&indexPage=${index-1 >= 1?index-1:index}" class="home-filter__page-btn">
	                                        <i class="home-filter__page-icon fa-solid fa-angle-left"></i>
	                                    </a>
									</c:if> 
									<c:if test="${tagBestSellingBookByCat == 1}">
			                            <a href="admin-ctr?action=filter-by-best-selling-book-by-cat&catid=${catid}&indexPage=${index-1 >= 1?index-1:index}" class="home-filter__page-btn">
	                                        <i class="home-filter__page-icon fa-solid fa-angle-left"></i>
	                                    </a>
									</c:if> 
									<c:if test="${tagBestSellingBookBySearch == 1}">
			                            <a href="admin-ctr?action=filter-by-best-selling-book-by-search&txtSearch=${txtSearch}&indexPage=${index-1 >= 1?index-1:index}" class="home-filter__page-btn">
	                                        <i class="home-filter__page-icon fa-solid fa-angle-left"></i>
	                                    </a>
									</c:if> 
									<c:if test="${tagS == 1 && tagSIncrease == null && tagSDecrease == null}">
			                            <a href="admin-ctr?action=search-book&txtSearch=${txtSearch}&indexPage=${index-1 >= 1?index-1:index}" class="home-filter__page-btn">
	                                        <i class="home-filter__page-icon fa-solid fa-angle-left"></i>
	                                    </a>
									</c:if>
									<c:if test="${tagIncrease == 1}">
			                            <a href="admin-ctr?action=filter-by-price-increase&indexPage=${index-1 >= 1?index-1:index}" class="home-filter__page-btn">
	                                        <i class="home-filter__page-icon fa-solid fa-angle-left"></i>
	                                    </a>
									</c:if>
									<c:if test="${tagIncreaseAndCat == 1}">
			                            <a href="admin-ctr?action=filter-by-price-increase-and-cat&catid=${catid}&indexPage=${index-1 >= 1?index-1:index}" class="home-filter__page-btn">
	                                        <i class="home-filter__page-icon fa-solid fa-angle-left"></i>
	                                    </a>
									</c:if>
									<c:if test="${tagDecrease == 1}">
			                            <a href="admin-ctr?action=filter-by-price-decrease&indexPage=${index-1 >= 1?index-1:index}" class="home-filter__page-btn">
	                                        <i class="home-filter__page-icon fa-solid fa-angle-left"></i>
	                                    </a>
									</c:if>
									<c:if test="${tagDecreaseAndCat == 1}">
			                            <a href="admin-ctr?action=filter-by-price-decrease-and-cat&catid=${catid}&indexPage=${index-1 >= 1?index-1:index}" class="home-filter__page-btn">
	                                        <i class="home-filter__page-icon fa-solid fa-angle-left"></i>
	                                    </a>
									</c:if>
									<c:if test="${tagSIncrease == 1}">
			                            <a href="admin-ctr?action=search-book-by-price-increase&txtSearch=${txtSearch}&indexPage=${index-1 >= 1?index-1:index}" class="home-filter__page-btn">
	                                        <i class="home-filter__page-icon fa-solid fa-angle-left"></i>
	                                    </a>
									</c:if> 
									<c:if test="${tagSDecrease == 1}">
			                            <a href="admin-ctr?action=search-book-by-price-decrease&txtSearch=${txtSearch}&indexPage=${index-1 >= 1?index-1:index}" class="home-filter__page-btn">
	                                        <i class="home-filter__page-icon fa-solid fa-angle-left"></i>
	                                    </a>
									</c:if> 
                           
                                    <c:if test="${tag > 0 && tagIncrease == null && tagIncreaseAndCat == null && tagDecrease == null && tagDecreaseAndCat == null}">
			                            <a href="admin-ctr?action=category&catid=${catid}&indexPage=${index+1 <= endPage?index+1:index}" class="home-filter__page-btn">
	                                        <i class="home-filter__page-icon fa-solid fa-angle-right"></i>
	                                    </a>
									</c:if>
									<c:if test="${tag == null && tagS == null && tagIncrease == null && tagIncreaseAndCat == null && tagDecrease == null && tagDecreaseAndCat == null && tagBestSelling == null && tagBestSellingBookByCat == null && tagBestSellingBookBySearch == null}">
			                            <a href="home?indexPage=${index+1 <= endPage?index+1:index}" class="home-filter__page-btn">
	                                        <i class="home-filter__page-icon fa-solid fa-angle-right"></i>
	                                    </a>
									</c:if>
									<c:if test="${tagBestSelling == 1}">
			                            <a href="admin-ctr?action=filter-by-best-selling&indexPage=${index+1 <= endPage?index+1:index}" class="home-filter__page-btn">
	                                        <i class="home-filter__page-icon fa-solid fa-angle-right"></i>
	                                    </a>
									</c:if> 
									<c:if test="${tagBestSellingBookByCat == 1}">
			                            <a href="admin-ctr?action=filter-by-best-selling-book-by-cat&catid=${catid}&indexPage=${index+1 <= endPage?index+1:index}" class="home-filter__page-btn">
	                                        <i class="home-filter__page-icon fa-solid fa-angle-right"></i>
	                                    </a>
									</c:if> 
									<c:if test="${tagBestSellingBookBySearch == 1}">
			                            <a href="admin-ctr?action=filter-by-best-selling-book-by-search&txtSearch=${txtSearch}&indexPage=${index+1 <= endPage?index+1:index}" class="home-filter__page-btn">
	                                        <i class="home-filter__page-icon fa-solid fa-angle-right"></i>
	                                    </a>
									</c:if> 
									<c:if test="${tagS == 1 && tagSIncrease == null && tagSDecrease == null}">
			                            <a href="admin-ctr?action=search-book&txtSearch=${txtSearch}&indexPage=${index+1 <= endPage?index+1:index}" class="home-filter__page-btn">
	                                        <i class="home-filter__page-icon fa-solid fa-angle-right"></i>
	                                    </a>
									</c:if>
									<c:if test="${tagIncrease == 1}">
			                            <a href="admin-ctr?action=filter-by-price-increase&indexPage=${index+1 <= endPage?index+1:index}" class="home-filter__page-btn">
	                                        <i class="home-filter__page-icon fa-solid fa-angle-right"></i>
	                                    </a>
									</c:if>
									<c:if test="${tagIncreaseAndCat == 1}">
			                            <a href="admin-ctr?action=filter-by-price-increase-and-cat&catid=${catid}&indexPage=${index+1 <= endPage?index+1:index}" class="home-filter__page-btn">
	                                        <i class="home-filter__page-icon fa-solid fa-angle-right"></i>
	                                    </a>
									</c:if>
									<c:if test="${tagDecrease == 1}">
			                            <a href="admin-ctr?action=filter-by-price-decrease&indexPage=${index+1 <= endPage?index+1:index}" class="home-filter__page-btn">
	                                        <i class="home-filter__page-icon fa-solid fa-angle-right"></i>
	                                    </a>
									</c:if>
									<c:if test="${tagDecreaseAndCat == 1}">
			                            <a href="admin-ctr?action=filter-by-price-decrease-and-cat&catid=${catid}&indexPage=${index+1 <= endPage?index+1:index}" class="home-filter__page-btn">
	                                        <i class="home-filter__page-icon fa-solid fa-angle-right"></i>
	                                    </a>
									</c:if>
									<c:if test="${tagSIncrease == 1}">
			                            <a href="admin-ctr?action=search-book-by-price-increase&txtSearch=${txtSearch}&indexPage=${index+1 <= endPage?index+1:index}" class="home-filter__page-btn">
	                                        <i class="home-filter__page-icon fa-solid fa-angle-right"></i>
	                                    </a>
									</c:if> 
									<c:if test="${tagSDecrease == 1}">
			                            <a href="admin-ctr?action=search-book-by-price-decrease&txtSearch=${txtSearch}&indexPage=${index+1 <= endPage?index+1:index}" class="home-filter__page-btn">
	                                        <i class="home-filter__page-icon fa-solid fa-angle-right"></i>
	                                    </a>
									</c:if> 
                                </div>
                            </div>
                        </div>

                        
						<a href="admin/add-book.jsp"><button class="home-filter__btn btn btn btn--primary">New book</button></a>
                        <!-- Product list -->
                        <div class="home-product">
                            <div class="row sm-gutter">

                                <!-- Product item -->
                                <c:forEach items="${listB}" var="o">
                                <div class="col l-2-4 m-4 c-6">
                                    <div class="home-product-item">
                                        <div class="home-product-item__img"
                                            style="background-image: url(${o.coverurl});">
                                        </div>
                                        <h4 class="home-product-item__name">
                                            ${o.title}
                                        </h4>
                                        <div class="home-product-item__price">
                                            <span class="home-product-item__price-old">26.000.000đ</span>
                                            <span class="home-product-item__price-current">${o.price}đ</span>
                                        </div>
                                        <div class="home-product-item__action">
                                            <span class="home-product-item__like home-product-item__like--liked">
                                                <i class="home-product-item__like-icon-empty fa-regular fa-heart"></i>
                                                <i class="home-product-item__like-icon-fill fa-solid fa-heart"></i>
                                            </span>
                                            <div class="home-product-item__rating">
                                                <i class="home-product-item__star--gold fa-solid fa-star"></i>
                                                <i class="home-product-item__star--gold fa-solid fa-star"></i>
                                                <i class="home-product-item__star--gold fa-solid fa-star"></i>
                                                <i class="home-product-item__star--gold fa-solid fa-star"></i>
                                                <i class=" fa-solid fa-star"></i>
                                            </div>
                                            <span class="home-product-item__sold">${o.sold} đã bán</span>
                                        </div>
                                        <div class="home-product-item__origin">
                                           
											<span class="home-product-item__origin-name"><a href="admin-ctr?action=update-book&bid=${o.bid}&ck=false" style="color: red;text-decoration: none;">Sửa sách</a></span> <!-- Khi nhấn view sẽ chuyển sang trang viewbook với tham số truyền đi là bid -->
			      							<span class="home-product-item__origin-name"><a href="#" onclick="checkdelete(${o.bid})" style="color: red;text-decoration: none;">Xóa sách</a></span>
                                        </div>
                                        <div class="home-product-item__favourite">
                                            <i class="fa-solid fa-check"></i>
                                            <span>Yêu thích</span>
                                        </div>
                                        <div class="home-product-item__sale-off">
                                            <span class="home-product-item__sale-off-percent">10%</span>
                                            <span class="home-product-item__sale-off-label">GIẢM</span>
                                        </div>
                                    </div>
                                </div>
								</c:forEach>
                                

                            </div>
                        </div>
                        <!-- Pagination -->
                        <ul class="pagination home-product__pagination">
                            <c:if test="${tag > 0 && tagIncrease == null && tagIncreaseAndCat == null && tagDecrease == null && tagDecreaseAndCat == null}">
								<li class="pagination-item">
	                                <a href="admin-ctr?action=category&catid=${catid}&indexPage=${index-1 >= 1?index-1:index}" class="pagination-item__link">
	                                    <i class="pagination-item__icon fa-solid fa-angle-left"></i>
	                                </a>
	                            </li>
							</c:if>
							<c:if test="${tag == null && tagS == null && tagIncrease == null && tagIncreaseAndCat == null && tagDecrease == null && tagDecreaseAndCat == null && tagBestSelling == null && tagBestSellingBookByCat == null && tagBestSellingBookBySearch == null}">
								<li class="pagination-item">
	                                <a href="home?indexPage=${index-1 >= 1?index-1:index}" class="pagination-item__link">
	                                    <i class="pagination-item__icon fa-solid fa-angle-left"></i>
	                                </a>
	                            </li>
							</c:if>
							<c:if test="${tagBestSelling == 1}">
								<li class="pagination-item">
	                                <a href="admin-ctr?action=filter-by-best-selling&indexPage=${index-1 >= 1?index-1:index}" class="pagination-item__link">
	                                    <i class="pagination-item__icon fa-solid fa-angle-left"></i>
	                                </a>
	                            </li>
							</c:if>
							<c:if test="${tagBestSellingBookByCat == 1}">
								<li class="pagination-item">
	                                <a href="admin-ctr?action=filter-by-best-selling-book-by-cat&catid=${catid}&indexPage=${index-1 >= 1?index-1:index}" class="pagination-item__link">
	                                    <i class="pagination-item__icon fa-solid fa-angle-left"></i>
	                                </a>
	                            </li>
							</c:if>
							<c:if test="${tagBestSellingBookBySearch == 1}">
								<li class="pagination-item">
	                                <a href="admin-ctr?action=filter-by-best-selling-book-by-search&txtSearch=${txtSearch}&indexPage=${index-1 >= 1?index-1:index}" class="pagination-item__link">
	                                    <i class="pagination-item__icon fa-solid fa-angle-left"></i>
	                                </a>
	                            </li>
							</c:if>
							<c:if test="${tagS == 1 && tagSIncrease == null && tagSDecrease == null}">
								<li class="pagination-item">
	                                <a href="admin-ctr?action=search-book&txtSearch=${txtSearch}&indexPage=${index-1 >= 1?index-1:index}" class="pagination-item__link">
	                                    <i class="pagination-item__icon fa-solid fa-angle-left"></i>
	                                </a>
	                            </li>
							</c:if>
							<c:if test="${tagIncrease == 1}">
								<li class="pagination-item">
	                                <a href="admin-ctr?action=filter-by-price-increase&indexPage=${index-1 >= 1?index-1:index}" class="pagination-item__link">
	                                    <i class="pagination-item__icon fa-solid fa-angle-left"></i>
	                                </a>
	                            </li>
							</c:if>
							<c:if test="${tagIncreaseAndCat == 1}">
								<li class="pagination-item">
	                                <a href="admin-ctr?action=filter-by-price-increase-and-cat&catid=${catid}&indexPage=${index-1 >= 1?index-1:index}" class="pagination-item__link">
	                                    <i class="pagination-item__icon fa-solid fa-angle-left"></i>
	                                </a>
	                            </li>
							</c:if>
							<c:if test="${tagDecrease == 1}">
								<li class="pagination-item">
	                                <a href="admin-ctr?action=filter-by-price-decrease&indexPage=${index-1 >= 1?index-1:index}" class="pagination-item__link">
	                                    <i class="pagination-item__icon fa-solid fa-angle-left"></i>
	                                </a>
	                            </li>
							</c:if>
							<c:if test="${tagDecreaseAndCat == 1}">
								<li class="pagination-item">
	                                <a href="admin-ctr?action=filter-by-price-decrease-and-cat&catid=${catid}&indexPage=${index-1 >= 1?index-1:index}" class="pagination-item__link">
	                                    <i class="pagination-item__icon fa-solid fa-angle-left"></i>
	                                </a>
	                            </li>
							</c:if>
							<c:if test="${tagSIncrease == 1}">
								<li class="pagination-item">
	                                <a href="admin-ctr?action=search-book-by-price-increase&txtSearch=${txtSearch}&indexPage=${index-1 >= 1?index-1:index}" class="pagination-item__link">
	                                    <i class="pagination-item__icon fa-solid fa-angle-left"></i>
	                                </a>
	                            </li>
							</c:if> 
							<c:if test="${tagSDecrease == 1}">
								<li class="pagination-item">
	                                <a href="admin-ctr?action=search-book-by-price-decrease&txtSearch=${txtSearch}&indexPage=${index-1 >= 1?index-1:index}" class="pagination-item__link">
	                                    <i class="pagination-item__icon fa-solid fa-angle-left"></i>
	                                </a>
	                            </li>
							</c:if> 
							
                            <c:forEach begin="1" end="${endPage}" var="i">
                            	<c:if test="${tag > 0 && tagIncrease == null && tagIncreaseAndCat == null && tagDecrease == null && tagDecreaseAndCat == null}">
									<li class="pagination-item ${index == i?"pagination-item--active":"" }">
	                                	<a href="admin-ctr?action=category&catid=${catid}&indexPage=${i}" class="pagination-item__link">${i}</a>
	                            	</li>
								</c:if>
								<c:if test="${tag == null && tagS == null && tagIncrease == null && tagIncreaseAndCat == null && tagDecrease == null && tagDecreaseAndCat == null && tagBestSelling == null && tagBestSellingBookByCat == null && tagBestSellingBookBySearch == null}">
									<li class="pagination-item ${index == i?"pagination-item--active":"" }">
	                                	<a href="home?indexPage=${i}" class="pagination-item__link">${i}</a>
	                            	 </li>
								</c:if>
								<c:if test="${tagBestSelling == 1}">
									<li class="pagination-item ${index == i?"pagination-item--active":"" }">
	                                	<a href="admin-ctr?action=filter-by-best-selling&indexPage=${i}" class="pagination-item__link">${i}</a>
	                            	 </li>
								</c:if>
								<c:if test="${tagBestSellingBookByCat == 1}">
									<li class="pagination-item ${index == i?"pagination-item--active":"" }">
	                                	<a href="admin-ctr?action=filter-by-best-selling-book-by-cat&catid=${catid }&indexPage=${i}" class="pagination-item__link">${i}</a>
	                            	 </li>
								</c:if>
								<c:if test="${tagBestSellingBookBySearch == 1}">
									<li class="pagination-item ${index == i?"pagination-item--active":"" }">
	                                	<a href="admin-ctr?action=filter-by-best-selling-book-by-search&txtSearch=${txtSearch}&indexPage=${i}" class="pagination-item__link">${i}</a>
	                            	 </li>
								</c:if>
								<c:if test="${tagS == 1 && tagSIncrease == null && tagSDecrease == null}">
									<li class="pagination-item ${index == i?"pagination-item--active":"" }">
	                                	<a href="admin-ctr?action=search-book&txtSearch=${txtSearch}&indexPage=${i}" class="pagination-item__link">${i}</a>
	                            	</li>
								</c:if>
								<c:if test="${tagSIncrease == 1}">
									<li class="pagination-item ${index == i?"pagination-item--active":"" }">
	                                	<a href="admin-ctr?action=search-book-by-price-increase&txtSearch=${txtSearch}&indexPage=${i}" class="pagination-item__link">${i}</a>
	                            	</li>
								</c:if>
								<c:if test="${tagSDecrease == 1}">
									<li class="pagination-item ${index == i?"pagination-item--active":"" }">
	                                	<a href="admin-ctr?action=search-book-by-price-decrease&txtSearch=${txtSearch}&indexPage=${i}" class="pagination-item__link">${i}</a>
	                            	</li>
								</c:if>
                            	<c:if test="${tagIncrease == 1}">
									<li class="pagination-item ${index == i?"pagination-item--active":"" }">
	                                	<a href="admin-ctr?action=filter-by-price-increase&indexPage=${i}" class="pagination-item__link">${i}</a>
	                            	</li>
								</c:if>
								<c:if test="${tagIncreaseAndCat == 1}">
									<li class="pagination-item ${index == i?"pagination-item--active":"" }">
	                                	<a href="admin-ctr?action=filter-by-price-increase-and-cat&catid=${catid}&indexPage=${i}" class="pagination-item__link">${i}</a>
	                            	</li>
								</c:if>
								<c:if test="${tagDecrease == 1}">
									<li class="pagination-item ${index == i?"pagination-item--active":"" }">
	                                	<a href="admin-ctr?action=filter-by-price-decrease&indexPage=${i}" class="pagination-item__link">${i}</a>
	                            	</li>
								</c:if>
								<c:if test="${tagDecreaseAndCat == 1}">
									<li class="pagination-item ${index == i?"pagination-item--active":"" }">
	                                	<a href="admin-ctr?action=filter-by-price-decrease-and-cat&catid=${catid}&indexPage=${i}" class="pagination-item__link">${i}</a>
	                            	</li>
								</c:if>
                            </c:forEach>
                            
                            <c:if test="${tag > 0 && tagIncrease == null && tagIncreaseAndCat == null && tagDecrease == null && tagDecreaseAndCat == null}">
								<li class="pagination-item">
	                                <a href="admin-ctr?action=category&catid=${catid}&indexPage=${index+1 <= endPage?index+1:index}" class="pagination-item__link">
	                                    <i class="pagination-item__icon fa-solid fa-angle-right"></i>
	                                </a>
	                            </li>
							</c:if>
                            <c:if test="${tag == null && tagS == null && tagIncrease == null && tagIncreaseAndCat == null && tagDecrease == null && tagDecreaseAndCat == null && tagBestSelling == null && tagBestSellingBookByCat == null && tagBestSellingBookBySearch == null}">
								<li class="pagination-item">
	                                <a href="home?indexPage=${index+1 <= endPage?index+1:index}" class="pagination-item__link">
	                                    <i class="pagination-item__icon fa-solid fa-angle-right"></i>
	                                </a>
	                            </li>
							</c:if>
							<c:if test="${tagBestSelling == 1}">
								<li class="pagination-item">
	                                <a href="admin-ctr?action=filter-by-best-selling&indexPage=${index+1 <= endPage?index+1:index}" class="pagination-item__link">
	                                    <i class="pagination-item__icon fa-solid fa-angle-right"></i>
	                                </a>
	                            </li>
							</c:if>
							<c:if test="${tagBestSellingBookByCat == 1}">
								<li class="pagination-item">
	                                <a href="admin-ctr?action=filter-by-best-selling-book-by-cat&catid=${catid}&indexPage=${index+1 <= endPage?index+1:index}" class="pagination-item__link">
	                                    <i class="pagination-item__icon fa-solid fa-angle-right"></i>
	                                </a>
	                            </li>
							</c:if>
							<c:if test="${tagBestSellingBookBySearch == 1}">
								<li class="pagination-item">
	                                <a href="admin-ctr?action=filter-by-best-selling-book-by-search&txtSearch=${txtSearch}&indexPage=${index+1 <= endPage?index+1:index}" class="pagination-item__link">
	                                    <i class="pagination-item__icon fa-solid fa-angle-right"></i>
	                                </a>
	                            </li>
							</c:if>
							<c:if test="${tagS == 1 && tagSIncrease == null && tagSDecrease == null}">
								<li class="pagination-item">
	                                <a href="admin-ctr?action=search-book&txtSearch=${txtSearch}&indexPage=${index+1 <= endPage?index+1:index}" class="pagination-item__link">
	                                    <i class="pagination-item__icon fa-solid fa-angle-right"></i>
	                                </a>
	                            </li>
							</c:if>
							<c:if test="${tagSIncrease == 1}">
								<li class="pagination-item">
	                                <a href="admin-ctr?action=search-book-by-price-increase&txtSearch=${txtSearch}&indexPage=${index+1 <= endPage?index+1:index}" class="pagination-item__link">
	                                    <i class="pagination-item__icon fa-solid fa-angle-right"></i>
	                                </a>
	                            </li>
							</c:if> 
							<c:if test="${tagSDecrease == 1}">
								<li class="pagination-item">
	                                <a href="admin-ctr?action=search-book-by-price-decrease&txtSearch=${txtSearch}&indexPage=${index+1 <= endPage?index+1:index}" class="pagination-item__link">
	                                    <i class="pagination-item__icon fa-solid fa-angle-right"></i>
	                                </a>
	                            </li>
							</c:if> 
							<c:if test="${tagIncrease == 1}">
								<li class="pagination-item">
	                                <a href="admin-ctr?action=filter-by-price-increase&indexPage=${index+1 <= endPage?index+1:index}" class="pagination-item__link">
	                                    <i class="pagination-item__icon fa-solid fa-angle-right"></i>
	                                </a>
	                            </li>
							</c:if>
							<c:if test="${tagIncreaseAndCat == 1}">
								<li class="pagination-item">
	                                <a href="admin-ctr?action=filter-by-price-increase-and-cat&catid=${catid}&indexPage=${index+1 <= endPage?index+1:index}" class="pagination-item__link">
	                                    <i class="pagination-item__icon fa-solid fa-angle-right"></i>
	                                </a>
	                            </li>
							</c:if>
							<c:if test="${tagDecrease == 1}">
								<li class="pagination-item">
	                                <a href="admin-ctr?action=filter-by-price-decrease&indexPage=${index+1 <= endPage?index+1:index}" class="pagination-item__link">
	                                    <i class="pagination-item__icon fa-solid fa-angle-right"></i>
	                                </a>
	                            </li>
							</c:if>
							<c:if test="${tagDecreaseAndCat == 1}">
								<li class="pagination-item">
	                                <a href="admin-ctr?action=filter-by-price-decrease-and-cat&catid=${catid}&indexPage=${index+1 <= endPage?index+1:index}" class="pagination-item__link">
	                                    <i class="pagination-item__icon fa-solid fa-angle-right"></i>
	                                </a>
	                            </li>
							</c:if>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
		
        <!-- Footer -->
        <footer class="footer">
            <div class="grid wide footer__content">
                <div class="row">
                    <div class="col l-2-4 m-4 c-6">
                        <h3 class="footer__heading">Chăm sóc khách hàng</h3>
                        <ul class="footer-list">
                            <li class="footer-item">
                                <a href="#" class="footer-item__link">Trung tâm trợ giúp</a>
                            </li>
                            <li class="footer-item">
                                <a href="#" class="footer-item__link">KShop mail</a>
                            </li>
                            <li class="footer-item">
                                <a href="#" class="footer-item__link">Hướng dẫn mua hàng</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col l-2-4 m-4 c-6">
                        <h3 class="footer__heading">Về ticked</h3>
                        <ul class="footer-list">
                            <li class="footer-item">
                                <a href="#" class="footer-item__link">Giới thiệu</a>
                            </li>
                            <li class="footer-item">
                                <a href="#" class="footer-item__link">Tuyển dụng</a>
                            </li>
                            <li class="footer-item">
                                <a href="#" class="footer-item__link">Điều khoản</a>
                            </li>
                        </ul>
                    </div>
                    <div class="col l-2-4 m-4 c-6">
                        <h3 class="footer__heading">Danh mục</h3>
                        
                        <ul class="footer-list">
                        <c:forEach items="${listC}" var="o">
                            <li class="footer-item">
                                <a href="../admin-ctr?action=category&&catid=${o.catid}" class="footer-item__link">${o.catname}</a>
                            </li>
                        </c:forEach>
                        </ul>
                    </div>
                    <div class="col l-2-4 m-4 c-6">
                        <h3 class="footer__heading">Theo dõi chúng tôi trên</h3>
                        <ul class="footer-list">
                            <li class="footer-item">
                                <a href="#" class="footer-item__link">
                                    <i class="footer-item__icon fa-brands fa-facebook"></i>
                                    Facebook
                                </a>
                            </li>
                            <li class="footer-item">
                                <a href="#" class="footer-item__link">
                                    <i class="footer-item__icon fa-brands fa-instagram"></i>
                                    Instagram
                                </a>
                            </li>
                            <li class="footer-item">
                                <a href="#" class="footer-item__link">
                                    <i class="footer-item__icon fa-brands fa-linkedin"></i>
                                    LinkedIn
                                </a>
                            </li>
                        </ul>
                    </div>
                    <div class="col l-2-4 m-8 c-12">
                        <h3 class="footer__heading">Vào cửa hàng trên ứng dụng</h3>
                        <div class="footer__download">
                            <img class="footer__download-qr" src="./assets/img/qr_code.png" alt="Download QR">
                            <div class="footer__download-apps">
                                <a href="" class="footer__download-app-link">
                                    <img src="./assets/img/googleplay.png" alt="Google Play"
                                        class="footer__download-app-img">
                                </a>
                                <a href="" class="footer__download-app-link">
                                    <img src="./assets/img/appstore.png" alt="Appstore"
                                        class="footer__download-app-img">
                                </a>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="footer__bottom">
                <div class="grid wide">
                    <p class="footer_text">© 2023 - Bản quyền thuộc về Công ty Vũ Ngọc Phương-B20DCAT142</p>
                </div>
            </div>
        </footer>
    </div>

    <!-- Modal layout -->
    <div class="modal login-modal">
        <div class="modal__overlay"></div>
        <div class="modal__body">
            <!-- Login form -->
            <div class="auth-form">
                <div class="auth-form__container">
                    <div class="auth-form__header">
                        <h3 class="auth-form__heading">Đăng nhập</h3>
                        <span class="auth-form__switch-btn">Đăng ký</span>
                    </div>

                    <div class="auth-form__form">
                        <div class="auth-form__group">
                            <input type="text" class="auth-form__input" placeholder="Email của bạn">
                        </div>
                        <div class="auth-form__group">
                            <input type="password" class="auth-form__input" placeholder="Mật khẩu của bạn">
                        </div>
                    </div>

                    <div class="auth-form__aside">
                        <div class="auth-form__help">
                            <a href="" class="auth-form__help-link auth-form__help-forgot">
                                Quên mật khẩu
                            </a>
                            <span class="auth-form__help-separate"></span>
                            <a href="" class="auth-form__help-link">
                                Cần trợ giúp?
                            </a>
                        </div>
                    </div>

                    <div class="auth-form__controls">
                        <button class="btn auth-form__controls-back btn--normal">TRỞ LẠI</button>
                        <button class="btn btn--primary">ĐĂNG NHẬP</button>
                    </div>
                </div>

                <div class="auth-form__socials">
                    <a href="" class="auth-form__socials--facebook btn btn--size-s btn--with-icon">
                        <i class="auth-form__socials-icon fa-brands fa-facebook-square"></i>
                        <span class="auth-form__socials-title">Kết nối với Facebook</span>
                    </a>
                    <a href="" class="auth-form__socials--google btn btn--size-s btn--with-icon">
                        <i class="auth-form__socials-icon fa-brands fa-google"></i>
                        <span class="auth-form__socials-title">Kết nối với Google</span>
                    </a>
                </div>
            </div>
        </div>
    </div>

    <!-- Modal layout -->
    <div class="register-modal modal">
        <div class="modal__overlay"></div>
        <div class="modal__body">
            <!-- Register form -->
            <div class="auth-form">
                <div class="auth-form__container">
                    <div class="auth-form__header">
                        <h3 class="auth-form__heading">Đăng ký</h3>
                        <span class="auth-form__switch-btn">Đăng nhập</span>
                    </div>

                    <div class="auth-form__form">
                        <div class="auth-form__group">
                            <input type="text" class="auth-form__input" placeholder="Email của bạn">
                        </div>
                        <div class="auth-form__group">
                            <input type="password" class="auth-form__input" placeholder="Mật khẩu của bạn">
                        </div>
                        <div class="auth-form__group">
                            <input type="password" class="auth-form__input" placeholder="Nhập lại mật khẩu">
                        </div>
                    </div>

                    <div class="auth-form__aside">
                        <p class="auth-form__policy-text">
                            Bằng việc đăng kí, bạn đã đồng ý với Shopee về
                            <a href="" class="auth-form__text-link">Điều khoản dịch vụ</a>
                            &
                            <a href="" class="auth-form__text-link">Chính sách bảo mật</a>
                        </p>
                    </div>

                    <div class="auth-form__controls">
                        <button class="btn auth-form__controls-back btn--normal">TRỞ LẠI</button>
                        <button class="btn btn--primary">ĐĂNG KÍ</button>
                    </div>
                </div>

                <div class="auth-form__socials">
                    <a href="" class="auth-form__socials--facebook btn btn--size-s btn--with-icon">
                        <i class="auth-form__socials-icon fa-brands fa-facebook-square"></i>
                        <span class="auth-form__socials-title">Kết nối với Facebook</span>
                    </a>
                    <a href="" class="auth-form__socials--google btn btn--size-s btn--with-icon">
                        <i class="auth-form__socials-icon fa-brands fa-google"></i>
                        <span class="auth-form__socials-title">Kết nối với Google</span>
                    </a>
                </div>
            </div>
        </div>
    </div>
    <script src="./assets/js/main.js"></script>
    <script>
    
		function checkdelete(bid){
			var option = confirm("Bạn chắc muốn xóa sản phẩm này, việc xóa sản phẩm ảnh hưởng đến danh sách sản phẩm hóa đơn nhập và bán!")
			if(option === true){
				window.location.href = 'admin-ctr?action=delete-book&bid='+bid;
			}
		}
		function checkview(){
			return confirm('Bạn phải đăng nhập để xem chi tiết!');
		}
    </script>
    
</body>
</html>