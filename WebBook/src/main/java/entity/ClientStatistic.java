package entity;

import java.sql.Date;

public class ClientStatistic extends User{
	private int revenue;

	public ClientStatistic() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ClientStatistic(int uid, String username, String password, String email, String name, String gender,
			String phone, String address, String verificationCode, String photoPath, boolean isAdmin,
			boolean authenticationStatus, Date dob, Date authenticationCodeLifetime, int revenue) {
		super(uid, username, password, email, name, gender, phone, address, verificationCode, photoPath, isAdmin,
				authenticationStatus, dob, authenticationCodeLifetime);
		// TODO Auto-generated constructor stub
		this.revenue = revenue;
	}

	public int getRevenue() {
		return revenue;
	}

	public void setRevenue(int revenue) {
		this.revenue = revenue;
	}

	@Override
	public String toString() {
		return "UserStatistic [revenue=" + revenue + ", getUid()=" + getUid() + ", getUsername()=" + getUsername()
				+ ", getPassword()=" + getPassword() + ", getEmail()=" + getEmail() + ", getName()=" + getName()
				+ ", getGender()=" + getGender() + ", getDob()=" + getDob() + ", getPhone()=" + getPhone()
				+ ", getAddress()=" + getAddress() + ", isAdmin()=" + isAdmin() + ", getVerificationCode()="
				+ getVerificationCode() + ", getPhotoPath()=" + getPhotoPath() + ", getAuthenticationCodeLifetime()="
				+ getAuthenticationCodeLifetime() + ", isAuthenticationStatus()=" + isAuthenticationStatus()
				+ ", toString()=" + super.toString() + ", getClass()=" + getClass() + ", hashCode()=" + hashCode()
				+ "]";
	}
	
	
	
}
