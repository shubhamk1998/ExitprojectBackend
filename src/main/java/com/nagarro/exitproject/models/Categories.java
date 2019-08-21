package com.nagarro.exitproject.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;



@Entity
@Table(name="Categories")
public class Categories { 

	@Column(name = "Name")
	private String Name;

}
