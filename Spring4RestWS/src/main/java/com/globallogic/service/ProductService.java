package com.globallogic.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.globallogic.entity.Product;

@Component
public class ProductService implements IProductService {

	private static int noOfProducts = 0;
	private List<Product> products = new ArrayList<Product>();

	@Override
	public List<Product> getProducts() {
		return products;
	}

	@Override
	public Product getProductById(int id) {
		return products.get((int) id);
	}

	@Override
	public Product addProduct(Product product) {

		product.setId(++noOfProducts);
		products.add(product);
		return product;
	}

	@Override
	public Product editProduct(Product product) {

		Product editProduct = products.get(product.getId());
		BeanUtils.copyProperties(product, editProduct, "id");
		return editProduct;
	}

	@Override
	public Product deleteProductById(int id) {
		Product product = products.remove(id);
		return product;
	}
}
