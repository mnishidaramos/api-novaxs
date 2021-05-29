package com.mnishida.apinovaxs.model;

import javax.persistence.*;
import java.util.Date;
import javax.persistence.Lob;

@Entity
@Table(name = "type")
public class Type {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long id;

	@Column(name = "name")
	private String name;

	//Construtores
	public Type() {}

	public Type (String name) {
		this.name = name;
	}

	//Get para id
	public Long getId() {
		return id;
	}

	//Get e set para name
	public String getName() {
		return name;
	}

	public void setName(String name){
		this.name = name;
	}
}
