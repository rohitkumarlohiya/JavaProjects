package com.mscharhag.solr.repository;

import java.util.List;

import org.springframework.data.solr.repository.SolrCrudRepository;

import com.mscharhag.solr.document.Product;

public interface ProductRepository extends SolrCrudRepository<Product, String> {

	List<Product> findByName(String name);
	
	/*Page<Product> findByNameOrDescription(@Boost(2) String name, String description, Pageable pageable);

	@Query("name:?0")
	@Facet(fields = { "cat_txt" }, limit = 5)
	FacetPage<Product> findByNameAndFacetOnCategories(String name, Pageable page);*/
	
	/*@Highlight(prefix = "<highlight>", postfix = "</highlight>")
	HighlightPage<Product> findByDescription(String description, Pageable pageable);*/
	
}
