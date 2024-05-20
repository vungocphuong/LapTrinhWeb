<!DOCTYPE html>
   <html lang="en">
   <head>
      <meta charset="UTF-8">
      <meta name="viewport" content="width=device-width, initial-scale=1.0">
      <!-- <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/remixicon/3.5.0/remixicon.css" crossorigin=""> -->
      <link rel="stylesheet" href="assets/css/signin.css">
      <title>Login Store book-VNP</title>
   </head>
   <body>
      <div class="login">
         <img src="img/bg-login.jpg" alt="image" class="login__bg">

         <form action="login" class="login__form" method="POST">
            <h1 class="login__title">Login</h1>
			<%
				String baoLoi = request.getAttribute("baoLoi")+"";
				if(baoLoi.equals("null")){
					baoLoi = "";
				}
			%>
			<span><%=baoLoi%></span>
            <div class="login__inputs">
               <div class="login__box">
                  <input type="text" placeholder="Username" required class="login__input" name="username" id="username">
                  <i class="ri-mail-fill"></i>
               </div>

               <div class="login__box">
                  <input type="password" placeholder="Password" required class="login__input" name="pass" id="pass">
                  <i class="ri-lock-2-fill"></i>
               </div>
            </div>

            <div class="login__check">
               <div class="login__check-box">
                  <input type="checkbox" class="login__check-input" id="user-check">
                  <label for="user-check" class="login__check-label">Remember me</label>
               </div>

               <a href="#" class="login__forgot">Forgot Password?</a>
            </div>

            <button type="submit" class="login__button">Login</button>

            <div class="login__register">
               Don't have an account? <a href="register.jsp">Register</a>
            </div>
         </form>
      </div>
   </body>
</html>