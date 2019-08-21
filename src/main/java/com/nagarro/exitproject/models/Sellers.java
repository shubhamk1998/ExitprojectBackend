package com.nagarro.exitproject.models;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;


@Entity
@Table(name="Sellers")
public class Sellers {

	@Id
    @Column(name = "Email")
    private String email;
    
    @Column(name = "Username")
    private String userName;

    @Column(name = "CompanyName")
    private String companyName;
    
    @Column(name = "Address")
    private String address;

    @Column(name = "Mobile")
    private String mobile;
    
    @Column(name = "Password")
    private String password;
    
    @Column(name = "Status")
    private String status;
    
    public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	
    
    
    
    
    
}
