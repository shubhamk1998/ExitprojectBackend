package com.nagarro.exitproject.models;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;



@Entity
@Table(name="Categories")
public class Categories { 

	@Id
	@Column(name = "Name")
	private String Name;

}
