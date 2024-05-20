<%
String url = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
		+ request.getContextPath();
%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
   <html lang="en">
   <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/remixicon/3.5.0/remixicon.css" crossorigin=""> -->
      <link rel="stylesheet" href="<%=url %>/assets/css/signin.css">
      <title>VNP-ShopBook</title>
   </head>
   <body>
      <div class="login">
         <img src="<%=url %>/img/bg-login.jpg" alt="image" class="login__bg">

         <form action="<%=url %>/khach-hang?action=change-pass" class="login__form" method="POST" enctype="multipart/form-data">
            <h1 class="login__title">Change PassWord</h1>
			<%
				String baoLoi = request.getAttribute("baoLoi")+"";
				if(baoLoi.equals("null")){
					baoLoi = "";
				}
			%>
			<%if(baoLoi.equals("khong")) {%>
				<span>Thay đổi thành công hệ thống quay lại trang chủ sau 5s!</span>
				<script>
		         setTimeout(function(){
		            window.location.href = 'home';
		         }, 5000);
		      </script>
			<%}else{ %>
			<span><%=baoLoi%></span>
            <div class="login__inputs">
               <div class="login__box">
                  <input type="password" placeholder="Current Password" required class="login__input" name="curpass" id="curpass">
                  <i class="ri-mail-fill"></i>
               </div>

               <div class="login__box">
                  <input type="password" placeholder="New Password" required class="login__input" name="newpass" id="newpass">
                  <i class="ri-lock-2-fill"></i>
               </div>
               <div class="login__box">
                  <input type="password" placeholder="Confirm New Password" required class="login__input" name="confnewpass" id="confnewpass">
                  <i class="ri-lock-2-fill"></i>
               </div>
            </div>

            <button type="submit" class="login__button">Xac Nhan</button>
			<%} %>
         </form>
      </div>
   </body>
</html>