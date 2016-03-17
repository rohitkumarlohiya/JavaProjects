package com.rohit;

import java.util.ArrayList;
import java.util.List;

import javax.jws.WebMethod;
import javax.jws.WebService;
import javax.jws.soap.SOAPBinding;
import javax.jws.soap.SOAPBinding.Style;

@WebService(name="TestMartCatalog",portName="TestMartCatalogPort",serviceName="TestMartCatalogService")
@SOAPBinding(style=Style.RPC)
public class ProductCatalogImpl /* implements ProductCatalog */{

	@WebMethod
	public /*List<String>*/String getProductCategories() {
		List<String> productCategories = new ArrayList<String>();
		productCategories.add("Book");
		productCategories.add("Movies");

		//return productCategories;
		return "testing";

	}

}
