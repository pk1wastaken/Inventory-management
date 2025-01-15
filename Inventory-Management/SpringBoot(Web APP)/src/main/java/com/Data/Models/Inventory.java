package com.Data.Models;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
@Entity
public class Inventory {

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", productname=" + productname + ", price=" + price + ", marginprice="
				+ marginprice + ", type=" + type + "]";
	}

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long id;
	private String productname;
	private String price;
	private String marginprice;
	private String type;


	public long getId() {
		return id;
	}
	public void setId(long id) {
		this.id = id;
	}

	public String getProductname() {
		return productname;
	}

	public void setProductname(String productname) {
		this.productname = productname;
	}

	public String getPrice() {
		return price;
	}

	public void setPrice(String price) {
		this.price = price;
	}

	public String getMarginprice() {
		return marginprice;
	}

	public void setMarginprice(String marginprice) {
		this.marginprice = marginprice;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	

}
