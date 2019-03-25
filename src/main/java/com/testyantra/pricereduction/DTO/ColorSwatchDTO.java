package com.testyantra.pricereduction.DTO;



import com.testyantra.pricereduction.utils.BasicColorToRGBConverter;

public class ColorSwatchDTO {
	private String color;
	private String rgbColor;
	private String skuid;
	
	public String getColor() {
		return color;
	}
	public String getRgbColor() {
		return rgbColor;
	}
	public String getSkuid() {
		return skuid;
	}
	public ColorSwatchDTO(String color, String basicColor) {
		super();
		this.color = color;
		this.rgbColor = BasicColorToRGBConverter.convert(basicColor);
		this.skuid = "kg";
	}
}

