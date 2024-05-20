<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>VNP-ShopBook</title>
<!-- <link rel="shortcut icon" href="" type="image/x-icon"> -->
<link rel="stylesheet" href="./assets/css/normalize.min.css">
<link rel="stylesheet" href="./assets/css/base.css">
<link rel="stylesheet" href="./assets/css/main.css">
<link rel="stylesheet" href="./assets/css/main2.css">
<link rel="stylesheet" href="./assets/css/grid.css">
<link rel="stylesheet" href="./assets/css/responsive.css">
<link rel="stylesheet" href="./assets/font/fontawesome-free-6.1.1/css/all.min.css">
<link href="https://fonts.googleapis.com/css2?family=Roboto:wght@300;400;500;700&display=swap&subset=vietnamese"
    rel="stylesheet">
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js" integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS" crossorigin="anonymous"></script>
<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.0/jquery.min.js"></script>
<style>
	body{
	 font-size: 15px;
	}
	
	.mb-3 input{
		font-size: 15px;
	}
	.mb-3 textarea{
		font-size: 15px;
	}
	.mb-3 select{
		font-size: 15px;
	}
	/*span cảnh báo */
	.red{
		color: red;
	}
	canvas {
	  height: 300px;
	  border-style: solid;
	  border-width: 1px;
	  border-color: black;
	}
	.canvas1 {
	  height: 0px;
	  border-style: solid;
	  border-width: 1px;
	  border-color: black;
	}
	/*--style of star */
	div.stars {
	  width: 270px;
	  display: inline-block;
	}
	input.star { display: none; }
	
	label.star {
	  float: right;
	  padding: 10px;
	  font-size: 30px;
	  color: #444;
	  transition: all .2s;
	}
	input.star:checked ~ label.star:before {
	  content: '\f005';
	  color: #FD4;
	  transition: all .25s;
	}
	input.star-5:checked ~ label.star:before {
	  color: #FE7;
	  text-shadow: 0 0 20px #952;
	}
	input.star-1:checked ~ label.star:before { color: #F62; } 
	label.star:hover { transform: rotate(-15deg) scale(1.3); }
	label.star:before {
	  content: '\f006';
	  font-family: FontAwesome;
	}
	
	
	/*style of comment client*/
	.link-muted { color: #aaa; } .link-muted:hover { color: #1266f1; }
	#guidanhgia{
		background-color: green;
		color: white;
		border-radius: 8px;
	}
</style>
<!-- Thư viện js này tôi đã không sử dụng trong bài này, xem link sau để hiểu hơn: https://codepen.io/sultanmcdoom/pen/XBOyEx  -->
<script src="https://www.dukelearntoprogram.com/course1/common/js/image/SimpleImage.js">
</script>

<script>
function chooseFile(fileInput){
	if(fileInput.files && fileInput.files[0]){
		var reader = new FileReader();
		reader.onload = function(e){
			$('#image').attr('src', e.target.result);
		}
		reader.readAsDataURL(fileInput.files[0]);
	}
}
</script>
<!-- Font Awesome Icon Library: đánh giá sao-->
<link rel="stylesheet" href="//netdna.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css">
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
                                    <a href="khach-hang?action=view-order">Đơn mua</a>
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

                    <input type="checkbox" hidden id="mobile-search-checkbox" class="mobile-search-checkbox">

                    <div class="header__search">
                        <div class="header__search-input-wrap">
                            <!-- Header search input -->
                            <input type="text" class="header__search-input" placeholder="Nhập để tìm kiếm sản phẩm">

                        </div>
                        <button class="header__search-btn">
                            <i class="header__search-btn-icon fa-solid fa-magnifying-glass"></i>
                        </button>

                    </div>

                    <!-- Cart layout -->
                    <div class="header__cart">
                        <div class="header__cart-wrap">
                            <a href="khach-hang?action=view-cart"><i class="header__cart-icon fa-solid fa-cart-shopping"></i></a>

                            <span class="header__cart-notice">${order.items.size()>0?order.items.size():0}</span>


                        </div>
                    </div>
                </div>
            </div>
            
        </header>
        
    </div>
    <div class="container" style="margin-top: 200px; margin-left: 200px;font-size: 15px;">
		<form class="form" method="post" name="form_id" id="form_id" enctype="multipart/form-data"> <!-- enctype="multipart/form-data": hỗ trợ upload file image -->
			<div class="row">
				<div class="col-sm-7">
					<div class="row">
					
						<div class="col-sm-6">
							 <input type="hidden" id="bid" name="bid" value="${bookDetail.bid}">
							<div class="mb-3">
							  <label for="title" class="form-label">Tiêu đề<span class="red">*</span></label>
							  <input type="text" class="form-control" id="title" name="title" required="required" value="${bookDetail.title}" ${checkView == 1 ? "disabled":"" }>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="mb-3">
							  <label for="author" class="form-label">Tác giả<span class="red">*</span></label>
							  <input type="text" class="form-control" id="author" name="author" required="required" value="${bookDetail.author}" ${checkView == 1 ? "disabled":"" }>
							</div>
						</div>
						
					</div> 
					
					<div class="mb-3">
					  <label for="descr" class="form-label">Mô tả về sách</label>
					  <textarea class="form-control" id="descr" name="descr" ${checkView == 1 ? "disabled":"" }>${bookDetail.descr}</textarea>
					</div> 
					
					<div class="row">
					
						<div class="col-sm-6">
							<div class="mb-3">
							  <label for="rlsdate" class="form-label">Ngày phát hành<span class="red">*</span></label>
							  <input type="date" class="form-control" id="rlsdate" name="rlsdate" required="required" value="${bookDetail.rlsdate}" ${checkView == 1 ? "disabled":"" }>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="mb-3">
							  <label for="page" class="form-label">Số trang</label>
							  <input type="number" class="form-control" id="page" name="page" value="${bookDetail.page}" ${checkView == 1 ? "disabled":"" }>
							</div>
						</div>
						
					</div> 
					
					<div class="mb-3">
					  <label for="category" class="form-label">Thể loại</label>
					  <select class="form-select" aria-label="Default select example" id="category" name="category" ${checkView == 1 ? "disabled":"" }>
						  <c:forEach items="${listC}" var="o" >
						  	<option value="${o.catid}" ${o.catname == bookDetail.category.catname ? "selected":""}>${o.catname}</option>
						  </c:forEach>
						  
					  </select>
					</div>
					
					<div class="row">
					
						<div class="col-sm-6">
							<div class="mb-3">
							  <label for="price" class="form-label">Giá sách (vnđ)</label>
							  <input type="number" class="form-control" id="price" name="price" value="${bookDetail.price}" ${checkView == 1 ? "disabled":"" }>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="mb-3">
							  <label for="sold" class="form-label">Đã bán</label>
							  <input type="number" class="form-control" id="sold" name="sold" value="${bookDetail.sold}" ${checkView == 1 ? "disabled":"" }>
							</div>
						</div>
						
					</div> 
				</div>
				<div class="col-sm-5">
				
					<!--  	<p> 
						Filename:	
						<input type="file" multiple="false" accept="image/*" id=finput onchange="upload()" ${checkView == 1 ? "disabled":"" }>
						</p>
						<canvas id="canv1"><img src="${bookDetail.coverurl}" class="canvas" alt="khong co anh hien thi"></canvas>
						<div style="margin-top: 100px; margin-left: 300px;">
							<a href="edit?bid=${bookDetail.bid}" role="button" class="btn btn-success">Edit</a>
						</div> -->
					<div class="mb-3">
						<input type="file" name="imageFile" id="imageFile" onchange="chooseFile(this)"
						accept="image/gif, image/jpeg, image/png" ${checkView == 1 ? "disabled":"" }>
					</div>
					<div class="mb-3">
						<img src="${bookDetail.coverurl}" alt="khong co anh hien thi"/ id="image" height="290">
					</div>
					<div class="mb-3" style="margin-left: 50px; margin-top: 50px;">
						<c:if test="${sessionScope.user != null && sessionScope.user.isAdmin()==false}">
			      			<a href="khach-hang?bid=${bookDetail.bid}&&action=add-to-cart" role="button" class="btn btn-success" style="font-size: 15px;">
			      			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
							  <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
							</svg>
			                Thêm vào giỏ hàng
			                </a>
						</c:if>
						<c:if test="${sessionScope.user == null}">
			      			<a href="" role="button" class="btn btn-success" style="font-size: 15px;" onclick="return checkAddToCart()">
			      			<svg xmlns="http://www.w3.org/2000/svg" width="16" height="16" fill="currentColor" class="bi bi-cart" viewBox="0 0 16 16">
							  <path d="M0 1.5A.5.5 0 0 1 .5 1H2a.5.5 0 0 1 .485.379L2.89 3H14.5a.5.5 0 0 1 .491.592l-1.5 8A.5.5 0 0 1 13 12H4a.5.5 0 0 1-.491-.408L2.01 3.607 1.61 2H.5a.5.5 0 0 1-.5-.5zM3.102 4l1.313 7h8.17l1.313-7H3.102zM5 12a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm7 0a2 2 0 1 0 0 4 2 2 0 0 0 0-4zm-7 1a1 1 0 1 1 0 2 1 1 0 0 1 0-2zm7 0a1 1 0 1 1 0 2 1 1 0 0 1 0-2z"/>
							</svg>
			                Thêm vào giỏ hàng
			                </a>
						</c:if>
		                
					</div>
					
					
				</div>
			</div>
		</form>
	
	</div>
	
	<h4 style="margin-left: 205px;margin-top: 20px;">Xếp hạng quyển sách này</h3>
	<h6 style="margin-left: 205px;">Cho người khác biết suy nghĩ của bạn</h6>
	<div class="stars" style="margin-left: 163px;">
		
	  <form action="khach-hang" enctype="multipart/form-data">
	  	<input type="hidden" name="action" value="add-comment">
	    <div>
	    	<input class="star star-5" id="star-5" type="radio" name="star" value="5" onclick="myFunction()"/>
		    <label class="star star-5" for="star-5"></label>
		    <input class="star star-4" id="star-4" type="radio" name="star" value="4" onclick="myFunction()"/>
		    <label class="star star-4" for="star-4"></label>
		    <input class="star star-3" id="star-3" type="radio" name="star" value="3" onclick="myFunction()"/>
		    <label class="star star-3" for="star-3"></label>
		    <input class="star star-2" id="star-2" type="radio" name="star" value="2" onclick="myFunction()"/>
		    <label class="star star-2" for="star-2"></label>
		    <input class="star star-1" id="star-1" type="radio" name="star" value="1" onclick="myFunction()"/>
		    <label class="star star-1" for="star-1"></label>
	    </div>
	    
		<div style="margin-left: 40px; width: 1000px;">
			<label for="descr" class="form-label">Comment</label>
			<textarea class="form-control" id="comment" name="comment" rows="5" cols="60" disabled style="font-size: 15px;"></textarea>
		</div>
		<%if(user != null){ %>
			<input type="hidden" id="uid" name="uid" value="<%=user.getUid()%>">
			<input type="hidden" id="bidC" name="bidC" value="${bookDetail.bid}">
			<div style="margin-left: 900px; margin-top: 10px;">
				<input type="submit" value="Gửi đánh giá" id="guidanhgia" name="guidanhgia" disabled>
			</div>
		<%}else{ %>
			<div style="margin-left: 900px; margin-top: 10px;">
				<input type="button" value="Gửi đánh giá" id="guidanhgia" name="guidanhgia" disabled onclick="return checkComment()">
			</div>
		<%} %>
		
		
	  </form>
	<!-- Add icon library of  User Rating Scorecard, tài liệu tham khảo: https://www.w3schools.com/howto/howto_css_user_rating.asp-->
	<link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
	<!-- style of  User Rating Scorecard -->
	<style>
	* {
	  box-sizing: border-box;
	}
	
	.heading {
	  font-size: 25px;
	  margin-right: 25px;
	}
	
	.fa {
	  font-size: 25px;
	}
	
	.checked {
	  color: orange;
	}
	
	/* Three column layout */
	.side {
	  float: left;
	  width: 15%;
	  margin-top: 10px;
	}
	
	.middle {
	  float: left;
	  width: 70%;
	  margin-top: 10px;
	}
	
	/* Place text to the right */
	.right {
	  text-align: right;
	}
	
	/* Clear floats after the columns */
	.row:after {
	  content: "";
	  display: table;
	  clear: both;
	}
	
	/* The bar container */
	.bar-container {
	  width: 100%;
	  background-color: #f1f1f1;
	  text-align: center;
	  color: white;
	}
	
	/* Individual bars */
	.bar-5 {width: ${c5}%; height: 18px; background-color: #04AA6D;}
	.bar-4 {width: ${c4}%; height: 18px; background-color: #2196F3;}
	.bar-3 {width: ${c3}%; height: 18px; background-color: #00bcd4;}
	.bar-2 {width: ${c2}%; height: 18px; background-color: #ff9800;}
	.bar-1 {width: ${c1}%; height: 18px; background-color: #f44336;}
	
	/* Responsive layout - make the columns stack on top of each other instead of next to each other */
	@media (max-width: 400px) {
	  .side, .middle {
	    width: 100%;
	  }
	  /* Hide the right column on small screens */
	  .right {
	    display: none;
	  }
	}
	
	</style>
	<p style="width: 500px;margin-left: 40px;">${avg} average based on ${countStar} reviews.</p>
	<hr style="border:3px solid #f1f1f1">
	
	<div class="row" style="width: 700px; margin-left: 40px;">
	  <div class="side">
	    <div>5 star</div>
	  </div>
	  <div class="middle">
	    <div class="bar-container">
	      <div class="bar-5"></div>
	    </div>
	  </div>
	  <div class="side right">
	    <div>${count5}</div>
	  </div>
	  <div class="side">
	    <div>4 star</div>
	  </div>
	  <div class="middle">
	    <div class="bar-container">
	      <div class="bar-4"></div>
	    </div>
	  </div>
	  <div class="side right">
	    <div>${count4}</div>
	  </div>
	  <div class="side">
	    <div>3 star</div>
	  </div>
	  <div class="middle">
	    <div class="bar-container">
	      <div class="bar-3"></div>
	    </div>
	  </div>
	  <div class="side right">
	    <div>${count3}</div>
	  </div>
	  <div class="side">
	    <div>2 star</div>
	  </div>
	  <div class="middle">
	    <div class="bar-container">
	      <div class="bar-2"></div>
	    </div>
	  </div>
	  <div class="side right">
	    <div>${count2}</div>
	  </div>
	  <div class="side">
	    <div>1 star</div>
	  </div>
	  <div class="middle">
	    <div class="bar-container">
	      <div class="bar-1"></div>
	    </div>
	  </div>
	  <div class="side right">
	    <div>${count1}</div>
	  </div>
	</div>
	</div>
	<section>
	  <div class="container my-5 py-5" style="margin-left: 90px;">
	    <div class="row d-flex justify-content-center">
	      <div class="col-md-12 col-lg-10">
	        <div class="card text-dark">
	        <c:forEach items="${listCmt}" var="o">
	        	<div class="card-body p-4">
	            <div class="d-flex flex-start">
	              <img class="rounded-circle shadow-1-strong me-3"
	                src="https://mdbcdn.b-cdn.net/img/Photos/Avatars/img%20(26).webp" alt="avatar" width="60"
	                height="60" />
	              <div>
	                <h6 class="fw-bold mb-1" style="font-size: 15px;">${o.uname}</h6>
	                <div class="d-flex align-items-center mb-3">
	                  <p class="mb-0">
	                    ${o.time} 
	                    <!--  <span class="badge bg-success">Approved</span> -->
	                    <span class="fa fa-star ${o.numstar >= 1 ? "checked":"" }"></span>
						<span class="fa fa-star ${o.numstar >= 2 ? "checked":"" }"></span>
						<span class="fa fa-star ${o.numstar >= 3 ? "checked":"" }"></span>
						<span class="fa fa-star ${o.numstar >= 4 ? "checked":"" }"></span>
						<span class="fa fa-star ${o.numstar >= 5 ? "checked":"" }"></span>
	                  </p>
	                  <a href="#!" class="link-muted"><i class="fas fa-pencil-alt ms-2"></i></a>
	                  <a href="#!" class="text-success"><i class="fas fa-redo-alt ms-2"></i></a>
	                  <a href="#!" class="link-danger"><i class="fas fa-heart ms-2"></i></a>
	                </div>
	                <p class="mb-0">
	                  ${o.content}
	                </p>
	              </div>
	            </div>
	          </div>
	
	          <hr class="my-0" style="height: 1px;" />
	        </c:forEach>
	
	        </div>
	      </div>
	    </div>
	  </div>
	</section>
	
</body>
<script>
function myFunction() {
  document.getElementById("guidanhgia").disabled = false;
  document.getElementById("comment").disabled = false;
}
function checkAddToCart() {
	return confirm('Bạn phải đăng nhập để đặt hàng!');
}
function checkComment(){
	return confirm('Bạn phải đăng nhập để gửi bình luận!');
}

</script>

</html>