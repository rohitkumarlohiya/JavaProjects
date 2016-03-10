package com.globallogic.service;

import java.util.List;

import com.globallogic.entity.Product;

public interface IProductService {

	public List<Product> getProducts();

	public Product getProductById(int id);

	public Product addProduct(Product product);

	public Product editProduct(Product product);

	public Product deleteProductById(int id);

}
