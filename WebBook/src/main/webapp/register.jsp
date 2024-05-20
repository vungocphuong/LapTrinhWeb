<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>VNP-ShopBook</title>
<link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-9ndCyUaIbzAi2FUVXJi0CjmCapSmO7SnpJef0486qhLnuZ2cdeRhO02iuK6FUUVM" crossorigin="anonymous">
<script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.11.8/dist/umd/popper.min.js" integrity="sha384-I7E8VVD/ismYTF4hNIPjVp/Zjvgyol6VFvRkX/vR+Vc4jQkC+hVqc2pM8ODewa9r" crossorigin="anonymous"></script>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.3.0/dist/js/bootstrap.min.js" integrity="sha384-fbbOQedDUMZZ5KreZpsbe1LCZPVmfTnH7ois6mU1QK+m14rQ1l2bGBq41eYeM/fS" crossorigin="anonymous"></script>
<style>
	.red{
		color: red;
	}
</style>
</head>
<%
	String baoLoi = (request.getAttribute("baoLoi")+"").equals("null")?"":(request.getAttribute("baoLoi")+"");

	//các biên sau sinh ra để giữ lại các giá trị trong form khi nhập sai
	String username = request.getAttribute("username")+"";
	username = (username.equals("null"))?"":username;
	
	String email = request.getAttribute("email")+"";
	email = (email.equals("null"))?"":email;
	
	String name = request.getAttribute("name")+"";
	name = (name.equals("null"))?"":name;
	
	String gender= request.getAttribute("gender")+"";
	gender = (gender.equals("null"))?"":gender;
	
	String dob= request.getAttribute("dob")+"";
	dob = (dob.equals("null"))?"":dob;
	
	String phone = request.getAttribute("phone")+"";
	phone = (phone.equals("null"))?"":phone;
	
	String address = request.getAttribute("address")+"";
	address = (address.equals("null"))?"":address;
	
%>
<body>
	<div class="container">
		<br/>
		<div class="text-center"><h1>Đăng ký tài khoản</h1></div> <br/><br/>
		<div class="red" id="baoLoi">
			<%=baoLoi %>
		</div>
		<form class="form" action="register" method="post">
		<div class="row">
			<div class="col-sm-6">
				<div class="mb-3">
				  <label for="username" class="form-label">Tên đăng nhập <span class="red">*</span></label>
				  <input type="text" class="form-control" id="username" name="username" required="required" value="<%=username%>"> <!-- value="<tenDangNhap%> để lưu lại tenDangNhap khi form bị sai -->
				</div>
				<div class="mb-3">
				  <label for="pass" class="form-label">Mật khẩu <span class="red">*</span></label>
				  <input type="password" class="form-control" id="pass" name="pass" required="required" onkeyup="kiemTraMatKhau()">
				</div>
				<div class="mb-3">
				  <label for="repass" class="form-label">Nhập lại mật khẩu <span class="red">*</span> <span id="msg" class="red"></span></label>
				  <input type="password" class="form-control" id="repass" name="repass" required="required" onkeyup="kiemTraMatKhau()">
				</div>
				<div class="mb-3">
				  <label for="exampleFormControlInput1" class="form-label">Họ và tên</label>
				  <input type="text" class="form-control" id="name" name="name" value="<%=name%>">
				</div>
				<div class="mb-3">
						<label for="gender" class="form-label">Giới tính</label> <select
							class="form-control" id="gender" name="gender">
							<option></option>
							<option value="Nam" <%=(gender.equals("Nam"))?"selected='selected'":"" %> >Nam</option>
							<option value="Nữ" <%=(gender.equals("Nữ"))?"selected='selected'":"" %> >Nữ</option>
							<option value="Khác" <%=(gender.equals("Khác"))?"selected='selected'":"" %> >Khác</option>
						</select>
				</div>
				
			</div>
			<div class="col-sm-6">
				<div class="mb-3">
						<label for="dob" class="form-label">Ngày sinh</label> <input
							type="date" class="form-control" id="dob" name="dob" value="<%=dob%>">
				</div>
				<div class="mb-3">
				  <label for="address" class="form-label">Địa chỉ</label>
				  <input type="text" class="form-control" id="address" name="address" value="<%=address%>">
				</div> 
				
				<div class="mb-3">
				  <label for="phone" class="form-label">Điện thoại</label>
				  <input type="tel" class="form-control" id="phone" name="phone" value="<%=phone%>">
				</div>
				<div class="mb-3">
				  <label for="email" class="form-label">Email <span class="red">*</span></label>
				  <input type="email" class="form-control" id="email" name="email" required="required" value="<%=email%>">
				</div>
				<hr/>
				
				<div class="mb-3">
				  <label for="dongYDieuKhoan" class="form-label">Đồng ý với <a>điều khoản của công ty</a> <span class="red">*</span></label>
				  <input type="checkbox" class="form-check-input" id="dongYDieuKhoan" name="dongYDieuKhoan" required="required" onchange="xuLyChonDongY()">
				</div>
				
				<div class="mb-3">
				  <input class="btn btn-primary form-control" type="submit" class="form-control" value="Đăng ký" 
				  name="submit" id="submit" style="visibility: hidden;"/> <!-- nút Đăng ký hiện ra khi đã nhập đầy đủ thông tin -->
				</div>
			</div>
		</div>
		</form>
	</div>
</body>
<script>
	function kiemTraMatKhau(){
		pass = document.getElementById("pass").value;
		repass = document.getElementById("repass").value;
		if(pass!=repass){
			document.getElementById("msg").innerHTML = "Mật khẩu không khớp!";
			return false;
		}else{
			document.getElementById("msg").innerHTML = "";
			return true;
		}
	}
	function xuLyChonDongY(){ // phai click dong y dieu khoan moi hien ra submit "dang ky"
		dongYDieuKhoan = document.getElementById("dongYDieuKhoan");
		if(dongYDieuKhoan.checked == true){
			document.getElementById("submit").style.visibility = "visible";
		}else{
			document.getElementById("submit").style.visibility = "hidden";
		}
		
	}
</script>
</html>