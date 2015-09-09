package com.mscharhag.solr.controller;

import org.apache.solr.client.solrj.SolrQuery;
import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.response.QueryResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.google.gson.Gson;

@RestController
@RequestMapping("/solrj/product")
public class ProductControllerUsingSolrj {

	@Autowired
	private SolrServer server;

	@RequestMapping("{name}")
	public String search(@PathVariable("name") String name) {

		return doSimpleTextSearch(name);
	}

	public String doSimpleTextSearch(String text) {
		try {
			SolrQuery solrQuery = new SolrQuery();
			solrQuery.setQuery(text);
			QueryResponse response = server.query(solrQuery);
			Gson gson = new Gson();
			String results = gson.toJson(response.getResults());
			return results;
		} catch (org.apache.solr.client.solrj.SolrServerException e) {
		}
		return null;
	}
}
