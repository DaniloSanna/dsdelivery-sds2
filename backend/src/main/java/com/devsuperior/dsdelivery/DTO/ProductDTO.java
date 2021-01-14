package com.devsuperior.dsdelivery.DTO;

import java.io.Serializable;

import com.devsuperior.dsdelivery.entities.Product;

public class ProductDTO implements Serializable{
	
	private static final long serialVersionUID = 1L;
	private long id;
	private String name, description, imageUri;
	private Double price;
	
	public ProductDTO() {}

	public ProductDTO(long id, String name, String description, String imageUri, Double price) {
		this.id = id;
		this.name = name;
		this.description = description;
		this.imageUri = imageUri;
		this.price = price;
	}
	
	public ProductDTO(Product entity) {		
		id = entity.getId();
		name = entity.getName();
		description = entity.getDescription();
		imageUri = entity.getImageUri();
		price = entity.getPrice();
	}

	public long getId() {
		return id;
	}

	public void setId(long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getImageUri() {
		return imageUri;
	}

	public void setImageUri(String imageUri) {
		this.imageUri = imageUri;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}
	
	
}
