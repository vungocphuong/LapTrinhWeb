<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="./assets/css/authen.css">
</head>
<body>
	<%
	
	String username = (String)session.getAttribute("username");
	
	String email = request.getAttribute("email")+"";
	email = (email.equals("null"))?"":email;
	
	String name = request.getAttribute("name")+"";
	name = (name.equals("null"))?"":name;
	
	String msgError = request.getAttribute("msgError")+"";
	msgError = (msgError.equals("null"))?"":msgError;
	
	String msgSuccess = request.getAttribute("msgSuccess")+"";
	msgSuccess = (msgSuccess.equals("null"))?"":msgSuccess;
	%>
	<%if(msgError == "" && msgSuccess == ""){ %>
	<section>
	    <div class="container">
	      <h1 class="title">Nhập mã xác thực gửi đến gmail đăng kí</h1>
	      <form id="otp-form">
	        <input type="hidden" name="username" id="username" value="<%=username %>"/>
	        <input type="text" class="otp-input" maxlength="1">
	        <input type="text" class="otp-input" maxlength="1">
	        <input type="text" class="otp-input" maxlength="1">
	        <input type="text" class="otp-input" maxlength="1">
	        <input type="text" class="otp-input" maxlength="1">
	        <input type="text" class="otp-input" maxlength="1">
	       
	      </form>
	      <button id="verify-btn" onclick="check()">Verify</button>
	    </div>
	  </section>
  
  
    <script  src="./assets/js/authen.js"></script>
    <%}else if(msgSuccess != ""){ %>
    	<div id='card' class="animated fadeIn">
	    <div id='upper-side'>
	      <?xml version="1.0" encoding="utf-8"?>
	        <!-- Generator: Adobe Illustrator 17.1.0, SVG Export Plug-In . SVG Version: 6.00 Build 0)  -->
	        <!DOCTYPE svg PUBLIC "-//W3C//DTD SVG 1.1//EN" "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd">
	        <svg version="1.1" id="checkmark" xmlns="http://www.w3.org/2000/svg" xmlns:xlink="http://www.w3.org/1999/xlink" x="0px" y="0px" xml:space="preserve">
	          <path d="M131.583,92.152l-0.026-0.041c-0.713-1.118-2.197-1.447-3.316-0.734l-31.782,20.257l-4.74-12.65
	    c-0.483-1.29-1.882-1.958-3.124-1.493l-0.045,0.017c-1.242,0.465-1.857,1.888-1.374,3.178l5.763,15.382
	    c0.131,0.351,0.334,0.65,0.579,0.898c0.028,0.029,0.06,0.052,0.089,0.08c0.08,0.073,0.159,0.147,0.246,0.209
	    c0.071,0.051,0.147,0.091,0.222,0.133c0.058,0.033,0.115,0.069,0.175,0.097c0.081,0.037,0.165,0.063,0.249,0.091
	    c0.065,0.022,0.128,0.047,0.195,0.063c0.079,0.019,0.159,0.026,0.239,0.037c0.074,0.01,0.147,0.024,0.221,0.027
	    c0.097,0.004,0.194-0.006,0.292-0.014c0.055-0.005,0.109-0.003,0.163-0.012c0.323-0.048,0.641-0.16,0.933-0.346l34.305-21.865
	    C131.967,94.755,132.296,93.271,131.583,92.152z" />
	          <circle fill="none" stroke="#ffffff" stroke-width="5" stroke-miterlimit="10" cx="109.486" cy="104.353" r="32.53" />
	        </svg>
	        <h3 id='status'>
	        Success
	      </h3>
	    </div>
	    <div id='lower-side'>
	      <p id='message'>
	       	<%=msgSuccess %>
	      </p>
	      <!-- <a href="#" id="contBtn">Continue</a> -->
	    </div>
	  </div>
	  <script>
         setTimeout(function(){
            window.location.href = 'home';
         }, 5000);
      </script>
    <%}else if(msgError != ""){ %>
    	
    	<div id='card' class="animated fadeIn">
	    <div id='upper-side'>
	      <?xml version="1.0" encoding="utf-8"?>
	        <!-- Generator: Adobe Illustrator 17.1.0, SVG Export Plug-In . SVG Version: 6.00 Build 0)  -->
	        <!DOCTYPE svg PUBLIC "-//W3C//DTD SVG 1.1//EN" "http://www.w3.org/Graphics/SVG/1.1/DTD/svg11.dtd">
	        <svg xmlns="http://www.w3.org/2000/svg" width="24" height="24" viewBox="0 0 24 24">
	          <path d="M11 15h2v2h-2zm0-8h2v6h-2zm.99-5C6.47 2 2 6.48 2 12s4.47 10 9.99 10C17.52 22 22 17.52 22 12S17.52 2 11.99 2zM12 20c-4.42 0-8-3.58-8-8s3.58-8 8-8 8 3.58 8 8-3.58 8-8 8z"/>
	        </svg>
	        <h3 id='status'>
	        Error
	      </h3>
	    </div>
	    <div id='lower-side'>
	      <p id='message'>
	        <%=msgError%>
	      </p>
	      <a href="authenticate.jsp" id="contBtn">Thử lại</a>
	      
	    </div>
	  </div>
    <%} %>
    
</body>
<script>
    function check(){
      const inputs = document.querySelectorAll('.otp-input');
      const input1 = document.querySelector('#username');

      const username = input1.value;
      const values = [];

      for (const input of inputs) {
        values.push(input.value);
      }
      verificationCode = values.join('');
      window.location.href = 'authenticate?username='+username+'&verificationCode='+verificationCode;
    }
  </script>
</html>