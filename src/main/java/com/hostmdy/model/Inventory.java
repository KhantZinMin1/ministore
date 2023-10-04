package com.hostmdy.model;

public class Inventory {
	
	private Long id;
	private String name;
	private Integer price;
	private String description;
	private String image;
	private String category;
	private Integer instock; 
	
	public Inventory(String name, Integer price, String description, String image, String category, Integer instock) {
		super();
		this.name = name;
		this.price = price;
		this.description = description;
		this.image = image;
		this.category = category;
		this.instock = instock;
	}
	
	public Inventory(Long id, String name, Integer price, String description, String image, String category, Integer instock) {
		super();
		this.id = id;
		this.name = name;
		this.price = price;
		this.description = description;
		this.image = image;
		this.category = category;
		this.instock = instock;
	}
	
	public String getImage() {
		return image;
	}

	public void setImage(String image) {
		this.image = image;
	}

	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Integer getInstock() {
		return instock;
	}

	public void setInstock(Integer instock) {
		this.instock = instock;
	}

	@Override
	public String toString() {
		return "Inventory [id=" + id + ", name=" + name + ", price=" + price + ", description=" + description + "]";
	}

}
