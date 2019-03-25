package com.testyantra.pricereduction.model;

public class Product {

	private String productId;
	private String title;

	private ColorSwatch[] colorSwatches;
	private Price price;

	public Price getPrice() {
		return price;
	}

	public void setPrice(Price price) {
		this.price = price;
	}

	public ColorSwatch[] getColorSwatches() {
		return colorSwatches;
	}

	public void setColorSwatches(ColorSwatch[] colorSwatches) {
		this.colorSwatches = colorSwatches;
	}

	public String getProductId() {
		return productId;
	}

	public void setProductId(String productId) {
		this.productId = productId;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

}
