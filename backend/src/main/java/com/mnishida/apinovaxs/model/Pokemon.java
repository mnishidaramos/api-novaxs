package com.mnishida.apinovaxs.model;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
import com.mnishida.apinovaxs.model.Type;

@Entity
@Table(name = "pokemon")
public class Pokemon {

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	private long num;

	@Column(name = "name")
	private String name;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "pokemon_type", joinColumns = @JoinColumn(name = "type_id", referencedColumnName = "id"), 
      inverseJoinColumns = @JoinColumn(name = "pokemon_num", referencedColumnName = "num"))
	private List<Type> types;

  @ManyToMany(cascade = CascadeType.ALL)
  @JoinTable(name = "r_pokemon_evol", joinColumns = @JoinColumn(name = "prev_num", referencedColumnName = "num"), 
      inverseJoinColumns = @JoinColumn(name = "next_num", referencedColumnName = "num"))
	private List<Pokemon> next_evol;

  @ManyToMany(mappedBy = "next_evol")
	private List<Pokemon> prev_evol;

	//Construtores
	public Pokemon() {}

	public Pokemon (Long num, 
  String name, 
  List<Type> types, 
  List<Pokemon> next_evol) {
		this.num = num;
		this.name = name;
		this.types = types;
		this.next_evol = next_evol;
	}

	//Get para codigo
	public Long getNum() {
		return num;
	}

	//Get e set para nome
	public String getName() {
		return name;
	}

	public void setName(String name){
		this.name = name;
	}

	//Get e set para type
	public List<Type> getTypes() {
		return types;
	}

	public void setTypes(List<Type> types){
		this.types = types;
	}

	//Get e set para next_evol
	public List<Pokemon> getNext_evol() {
		return next_evol;
	}

	public void setNext_evol(List<Pokemon> next_evol){
		this.next_evol = next_evol;
	}

	//Get e set para prev_evol
	public List<Pokemon> getPrev_evol() {
		return prev_evol;
	}

	public void setPrev_evol(List<Pokemon> prev_evol){
		this.prev_evol = prev_evol;
	}

}
