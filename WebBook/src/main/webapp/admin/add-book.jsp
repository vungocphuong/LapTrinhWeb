<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="entity.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>VNP-ShopBook</title>
<!-- <link rel="shortcut icon" href="" type="image/x-icon"> -->
<link rel="stylesheet" href="http://localhost:8080/BaiTapLonbackup/assets/css/normalize.min.css">
<link rel="stylesheet" href="http://localhost:8080/BaiTapLonbackup/assets/css/base.css">
<link rel="stylesheet" href="http://localhost:8080/BaiTapLonbackup/assets/css/main.css">
<link rel="stylesheet" href="http://localhost:8080/BaiTapLonbackup/assets/css/grid.css">
<link rel="stylesheet" href="http://localhost:8080/BaiTapLonbackup/assets/css/responsive.css">
<link rel="stylesheet" href="http://localhost:8080/BaiTapLonbackup/assets/font/fontawesome-free-6.1.1/css/all.min.css">
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
<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
String ck = request.getAttribute("ck")+"";
ck = (ck.equals("null"))?"":ck;
%>
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
    
    
    <div class="container" style="margin-top: 200px; margin-left: 150px;font-size: 15px;">
		<form action="<%=url%>/admin-ctr?action=add-book" class="form" method="post" name="form_id" id="form_id" enctype="multipart/form-data"> <!-- enctype="multipart/form-data": hỗ trợ upload file image -->
			<div class="row">
				<div class="col-sm-6">
					<div class="row">
					
						<div class="col-sm-6">
							<div class="mb-3">
							  <label for="title" class="form-label">Tiêu đề<span class="red">*</span></label>
							  <input type="text" class="form-control" id="title" name="title" value="${book.title}" required="required">
							</div>
						</div>
						<div class="col-sm-6">
							<div class="mb-3">
							  <label for="author" class="form-label">Tác giả<span class="red">*</span></label>
							  <input type="text" class="form-control" id="author" name="author" value="${book.author}" required="required">
							</div>
						</div>
						
					</div> 
					
					<div class="mb-3">
					  <label for="descr" class="form-label">Mô tả về sách</label>
					  <textarea class="form-control" id="descr" name="descr">${book.descr}</textarea>
					</div> 
					
					<div class="row">
					
						<div class="col-sm-6">
							<div class="mb-3">
							  <label for="rlsdate" class="form-label">Ngày phát hành<span class="red">*</span></label>
							  <input type="date" class="form-control" id="rlsdate" name="rlsdate" value="${book.rlsdate}" required="required">
							</div>
						</div>
						<div class="col-sm-6">
							<div class="mb-3">
							  <label for="page" class="form-label">Số trang</label>
							  <input type="number" class="form-control" id="page" name="page" value="${book.page}">
							</div>
						</div>
						
					</div> 
					
					<div class="mb-3">
					  <label for="category" class="form-label">Thể loại<span class="red">*</span></label>
					  <select class="form-select" aria-label="Default select example" id="category" name="category" required="required">
						  	<option value="" ></option>
						  	<option value="1" ${book.category.catid == 1 ? "selected":""}>Truyện cổ tích</option>
							<option value="2" ${book.category.catid == 2 ? "selected":""}>Truyện trinh thám</option>
						  	<option value="3" ${book.category.catid == 3 ? "selected":""}>Sách khoa học</option>
						  	<option value="4" ${book.category.catid == 4 ? "selected":""}>Truyện tranh</option>
					  </select>
					</div>
					
					<div class="row">
					
						<div class="col-sm-6">
							<div class="mb-3">
							  <label for="price" class="form-label">Giá sách (vnđ)<span class="red">*</span></label>
							  <input type="number" class="form-control" id="price" name="price" value="${book.price}" required="required">
							</div>
						</div>
						<div class="col-sm-6">
							<div class="mb-3">
							  <label for="sold" class="form-label">Đã bán</label>
							  <input type="number" class="form-control" id="sold" name="sold" value="0">
							</div>
						</div>
						
					</div> 
					<br>
					<div class="row">
						<div class="col-sm-6">
							<div class="mb-3">
							  	 <a class="btn btn-primary form-control" style="font-weight: 500;font-size: 15px;" href="http://localhost:8080/BaiTapLonbackup/home">Quay lại</a>
							</div>
						</div>
						<div class="col-sm-6">
							<div class="mb-3">
							  	<input class="btn btn-primary form-control" type="submit" class="form-control" value="Thêm" 
						  		name="submit" id="submit" />
							</div>
						</div>
					</div>
				</div>
				<div class="col-sm-6">
				
					<div class="mb-3">
						<input type="file" name="imageFile" id="imageFile" onchange="chooseFile(this)"
						accept="image/gif, image/jpeg, image/png">
					</div>
					<div class="mb-3">
						<img src="<%=url%>/${book.coverurl}" alt="khong co anh hien thi"/ id="image" height="200">
					</div>
					
					
				</div>
			</div>
		</form>
	
	</div>
   
	<c:if test="${book != null }">
		<script>
	      window.onload = function() {
	        setTimeout(function(){
	          alert('Bạn đã thêm sách mới thành công!');
	        }, 1000);  
	      }
	    </script>
	</c:if>
	
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