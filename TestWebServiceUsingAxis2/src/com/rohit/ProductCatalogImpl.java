package com.rohit;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebService;

@WebService
public class ProductCatalogImpl {

	public List<String> getProductCategories() {
		List<String> productCategories = new ArrayList<String>();
		productCategories.add("Book");
		productCategories.add("Movies");

		return productCategories;

	}

}
