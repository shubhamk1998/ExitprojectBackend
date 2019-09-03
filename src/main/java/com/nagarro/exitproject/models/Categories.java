package com.nagarro.exitproject.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.json.simple.JSONObject;



@Entity
@Table(name="Categories")
public class Categories { 

	@Id
	@Column(name = "Name")
	private String Name;

	
	public String toString() {
		JSONObject obj = new JSONObject();
		obj.put("Name", this.getName());
		return obj.toString();
	}


	public String getName() {
		return Name;
	}


	public void setName(String name) {
		Name = name;
	}
}
