package com.example.demo.product;

import com.sun.istack.NotNull;

public class Product {	
	@NotNull
	private Integer id;
	private String name;			//variables
	private Integer price;
	
	public  Integer getId() {
		return id;					//Getters
	}
	public void setId(Integer id) {
		
		this.id = id;				//Setter
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Integer getPrice() {
		return price;
	}
	public void setPrice(Integer price) {
		this.price = price;
	}
	
	@Override			// compiler that the element is meant to override an element declared in a superclass.
	public String toString() {
		return "Product [id=" + id + ", name=" + name + ", price=" + price + "]";
	}
	public Product(Integer id, String name, Integer price) {		//Constructer with Three Args
		super();
		this.id = id;					
		this.name = name;
		this.price = price;
	}
	public Product() {
		super();													//Constructer with ZERO args
	}

}
