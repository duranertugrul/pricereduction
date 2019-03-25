package com.testyantra.pricereduction.controllers;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.testyantra.pricereduction.DTO.ColorSwatchDTO;
import com.testyantra.pricereduction.DTO.ProductDTO;
import com.testyantra.pricereduction.enumarations.LabelType;
import com.testyantra.pricereduction.model.Product;
import com.testyantra.pricereduction.model.ProductList;
import com.testyantra.pricereduction.model.ColorSwatch;



@RestController
public class PriceCalculatorController {

	@GetMapping("/products")
	public Object listProducts(@RequestParam(required = false, defaultValue = "ShowWasNow") LabelType labelType)
			throws JsonParseException, JsonMappingException, IOException {

		List<ProductDTO> hasPrice = Arrays.stream(deserializeProducts().getProducts())
				.filter(p -> p.getPrice().getDiscount() > 0).map(p -> createProductDTO(p, labelType))
				.collect(Collectors.toList());

		return hasPrice;
	}

	private ProductDTO createProductDTO(Product product, LabelType labelType) {

		List<ColorSwatchDTO> colorSwatchesDTO = Arrays.stream(product.getColorSwatches())
				.map(c -> createColorSwatchDTO(c)).collect(Collectors.toList());

		ProductDTO dto = new ProductDTO(product.getProductId(), product.getTitle(), product.getPrice().getWas(), product.getPrice().nowPrice(),
				product.getPrice().getThen1(), product.getPrice().getThen2(), product.getPrice().getCurrency(), labelType,
				colorSwatchesDTO);
		return dto;
	}

	private ColorSwatchDTO createColorSwatchDTO(ColorSwatch p) {
		return new ColorSwatchDTO(p.getColor(),p.getBasicColor());
	}

	private ProductList deserializeProducts() throws IOException {
		/*
		 * NOTE: JSON HAS SOME ERRORS, in some item "now" is has properties which is not
		 * supposed to e
		 **/

		// RestTemplate template = new RestTemplate();
		/*
		 * Rootobject rootObject = res.
		 * ("https://jl-nonprod-syst.apigee.net/v1/categories/600001506/products?key=2ALHCAAs6ikGRBoy6eTHA58RaG097Fma",
		 * Rootobject.class);
		 */

		
		byte[] encoded = Files.readAllBytes(Paths.get("products.json")); //couldnt reach last night sometimes so put in a file
		String jsonAsString = new String(encoded);

		//correct errors in json ...for now couldn't find other another solution
		jsonAsString = jsonAsString.replaceAll("\\{\"from\":\"55.00\",\"to\":\"100.00\"\\}", "\"55\"");
		jsonAsString = jsonAsString.replaceAll("\\{\"from\":\"59.00\",\"to\":\"68.00\"\\}", "\"59\"");

	 	
		ObjectMapper mapper = new ObjectMapper().configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES, false);
	  	return mapper.readValue(jsonAsString, ProductList.class);
	}

}








 
