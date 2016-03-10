package com.globallogic.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.globallogic.entity.Product;
import com.globallogic.service.IProductService;

@RestController
@RequestMapping("/data")
public class ProductController {
	@Autowired
	private IProductService productService;

	@RequestMapping(value = "/products", method = RequestMethod.GET)
	public List<Product> getProducts() {
		return productService.getProducts();
	}

	@RequestMapping(value = "/product/{id}", method = RequestMethod.GET)
	public Product getProductById(@PathVariable("id") int id) {
		return productService.getProductById(id);
	}

	@RequestMapping(value = "/addProduct", method = RequestMethod.POST)
	public Product addProduct(@PathVariable("product") Product product) {
		return productService.addProduct(product);
	}

	@RequestMapping(value = "/editProduct", method = RequestMethod.PUT)
	public Product editProduct(@PathVariable("product") Product product) {
		return productService.editProduct(product);
	}

	@RequestMapping(value = "/deleteProduct/{id}", method = RequestMethod.DELETE)
	public Product deleteProductById(@PathVariable("id") int id) {
		return productService.deleteProductById(id);
	}
}