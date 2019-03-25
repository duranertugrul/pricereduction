package com.testyantra.pricereduction.utils;

import java.util.Hashtable;

public class CurrencyToSymbolConverter{
	private static Hashtable<String, String> symbols = new Hashtable<>();
	static {
		symbols.put("GBP", "£");
	}
	
	public static String convert(String currency) {
		return symbols.containsKey(currency) ? symbols.get(currency): "UnMapped Currency:"+currency;
	}
}