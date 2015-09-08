package com.mscharhag.solr.controller;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.mscharhag.solr.document.Book;
import com.mscharhag.solr.repository.BookRepository;

@RestController
public class RootController {

	@Autowired
	private BookRepository bookRepository;

	List<String> strs = Arrays.asList("ad", "a", "asd");

	@RequestMapping("/")
	public List<Book> home() {
		
		List<Book> books = bookRepository.findByName("Island");
		return books;
		// return "Hello World...!!!";
	}
}
