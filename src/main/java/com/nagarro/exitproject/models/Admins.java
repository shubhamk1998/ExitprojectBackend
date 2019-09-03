package com.nagarro.exitproject.models;

import org.json.simple.JSONObject;

import java.text.ParseException;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.fasterxml.jackson.databind.util.JSONPObject;

@Entity
@Table(name = "Admins")
public class Admins {

	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public void setName(String name) {
		Name = name;
	}

	public void setEmail(String email) {
		Email = email;
	}

	@Column(name = "Name")
	private String Name;

	@Id
	@Column(name = "Email")
	private String Email;
	
	
	@Column(name = "password")
	private String password;


	public Admins() {
	}

	public String getName() {
		return this.Name;
	}

	public String getEmail() {
		return this.Email;
	}


	public String toString() {
		JSONObject obj = new JSONObject();
		obj.put("Name", this.getName());
		obj.put("Email", this.getEmail());
		return obj.toString();
	}

}
