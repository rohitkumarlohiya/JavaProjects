package com.mscharhag.solr;

import org.apache.solr.client.solrj.SolrServer;
import org.apache.solr.client.solrj.impl.HttpSolrServer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.solr.core.SolrTemplate;
import org.springframework.data.solr.repository.config.EnableSolrRepositories;

@SpringBootApplication
@ComponentScan
@EnableSolrRepositories("com.mscharhag.solr.repository")
public class Application {
	
	public static void main(String[] args) {
		SpringApplication.run(Application.class, args);
	}

	@Bean
	public SolrServer solrServer() {
		//return new HttpSolrServer("http://localhost:8983/solr");
		//return new HttpSolrServer("http://solr.r12expert.com:8983/solr/db");
		return new HttpSolrServer("http://localhost:8983/solr/person");
	}

	@Bean
	public SolrTemplate solrTemplate(SolrServer server) throws Exception {
		return new SolrTemplate(server);
	}
}
