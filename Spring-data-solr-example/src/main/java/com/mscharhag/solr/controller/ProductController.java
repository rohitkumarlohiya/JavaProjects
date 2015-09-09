package com.mscharhag.solr.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mscharhag.solr.document.Product;
import com.mscharhag.solr.repository.ProductRepository;


@RestController
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductRepository productRepository;

	@RequestMapping("{name}")
	public List<Product> search(@PathVariable("name") String name) {

		List<Product> products = productRepository.findByName(name);
		return products;
	}
}
