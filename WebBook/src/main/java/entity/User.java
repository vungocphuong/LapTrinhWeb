package entity;

import java.sql.Date;

public class User {
	private int uid;
	private String username, password, email, name,gender, phone, address,verificationCode,photoPath;
	private  boolean isAdmin,authenticationStatus;
	private Date dob,authenticationCodeLifetime;
	
	public User() {
	}
	

	public User(int uid, String username, String password, String email, String name, String gender, String phone,
			String address, String verificationCode, String photoPath, boolean isAdmin, boolean authenticationStatus,
			Date dob, Date authenticationCodeLifetime) {
		super();
		this.uid = uid;
		this.username = username;
		this.password = password;
		this.email = email;
		this.name = name;
		this.gender = gender;
		this.phone = phone;
		this.address = address;
		this.verificationCode = verificationCode;
		this.photoPath = photoPath;
		this.isAdmin = isAdmin;
		this.authenticationStatus = authenticationStatus;
		this.dob = dob;
		this.authenticationCodeLifetime = authenticationCodeLifetime;
	}

	public int getUid() {
		return uid;
	}

	public void setUid(int uid) {
		this.uid = uid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
	
	
	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public Date getDob() {
		return dob;
	}

	public void setDob(Date dob) {
		this.dob = dob;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	
	public String getVerificationCode() {
		return verificationCode;
	}

	public void setVerificationCode(String verificationCode) {
		this.verificationCode = verificationCode;
	}

	public String getPhotoPath() {
		return photoPath;
	}

	public void setPhotoPath(String photoPath) {
		this.photoPath = photoPath;
	}

	public Date getAuthenticationCodeLifetime() {
		return authenticationCodeLifetime;
	}

	public void setAuthenticationCodeLifetime(Date authenticationCodeLifetime) {
		this.authenticationCodeLifetime = authenticationCodeLifetime;
	}

	

	public boolean isAuthenticationStatus() {
		return authenticationStatus;
	}

	public void setAuthenticationStatus(boolean authenticationStatus) {
		this.authenticationStatus = authenticationStatus;
	}

	@Override
	public String toString() {
		return "User [uid=" + uid + ", username=" + username + ", password=" + password + ", email=" + email + ", name="
				+ name + ", phone=" + phone + ", address=" + address + ", isAdmin=" + isAdmin + "]";
	}
	
	 
}
