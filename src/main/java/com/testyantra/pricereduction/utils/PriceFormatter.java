package com.testyantra.pricereduction.utils;

import java.text.MessageFormat;

public class PriceFormatter {
	public static String format(double price,String currency) {
		return CurrencyToSymbolConverter.convert(currency)
			+ (price >= 10 ? MessageFormat.format("{0,number,#}", price)
					: MessageFormat.format("{0,number,#.##}", price));
	}
	
/*	public static void main(String[] args) {
		System.out.println(format(10.0, "GBP"));
		System.out.println("son");
	}*/
}