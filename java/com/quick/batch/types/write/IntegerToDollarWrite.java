package com.quick.batch.types.write;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class IntegerToDollarWrite {
	
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private int id;
	private int number;
	private String doallarString;

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getDoallarString() {
		return doallarString;
	}

	public void setDoallarString(String doallarString) {
		this.doallarString = doallarString;
	}

	@Override
	public String toString() {
		return "IntegerToDollarWrite [id=" + id + ", number=" + number + ", doallarString=" + doallarString + "]";
	}

}