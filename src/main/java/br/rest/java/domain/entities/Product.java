package br.rest.java.domain.entities;

import java.text.DecimalFormat;
import java.util.UUID;

public class Product {
	private String id;	
	private String product_name;
	private double price;
	private int stock_quantity;
	private String manufacturer;
	
	public Product() {}
	
	public Product(String product_name, double price, int stock_quantity, String manufacturer) {
		this.product_name =  product_name;
		this.price = formatPrice(price) ;
		this.stock_quantity = stock_quantity;
		this.manufacturer = manufacturer;
		this.id = GenerateId();
		
		//todo: validations
	}
		
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getProduct_name() {
		return product_name;
	}
	public void setProduct_name(String product_name) {
		this.product_name = product_name;
	}
	public double getPrice() {
		return price;
	}
	public void setPrice(double price) {
		this.price = formatPrice(price);
	}	
	public int getStock_quantity() {
		return stock_quantity;
	}
	public void setStock_quantity(int stock_quantity) {
		this.stock_quantity = stock_quantity;
	}
	public String getManufacturer() {
		return manufacturer;
	}
	public void setManufacturer(String manufacturer) {
		this.manufacturer = manufacturer;
	}
	
	private String GenerateId() {
		return UUID.randomUUID()
				.toString();
				//.replace("-", "");
				//.substring(0, 7);
	}
	
	private double formatPrice(double price) {
		return Double.parseDouble(new DecimalFormat("###.##").format(price));
	}
}
