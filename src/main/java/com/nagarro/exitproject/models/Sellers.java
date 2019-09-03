package com.nagarro.exitproject.models;



import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.simple.JSONObject;


@Entity
@Table(name="Sellers")
public class Sellers {

	@Id
 	@Column(name="SellerId")
 	@GeneratedValue(strategy=GenerationType.AUTO) 
 	private long sellerId;
 	
 	public long getSellerId() {
		return this.sellerId;
	}
	
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
    
    @Column(name = "Created")
    private String created;
    

	public String getCreated() {
		return created;
	}

	public void setCreated(String created) {
		this.created = created;
	}

	@SuppressWarnings("unchecked")
	public String toString() {
		JSONObject obj = new JSONObject();
		obj.put("ID", this.getSellerId() );
		obj.put("Email", this.getEmail() );
		obj.put("userName",this.getUserName());
		obj.put("CompanyName",this.getCompanyName());
		obj.put("Created",this.getCreated() );

		obj.put("Mobile",this.getMobile());
		obj.put("Status", this.getStatus());

		return obj.toString();
	}
    
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
