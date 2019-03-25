package com.testyantra.pricereduction.utils;

import java.util.Hashtable;

public class BasicColorToRGBConverter{
	private static Hashtable<String, String> colors = new Hashtable<>();
	static {
		colors.put("white", "FFFFFF");
	}
	
	public static String convert(String basicColor) {
		return colors.contains(basicColor) ? colors.get(basicColor):"UnMapped Color:"+basicColor;
	}
}