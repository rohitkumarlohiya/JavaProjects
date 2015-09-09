package com.mscharhag.solr.document;

import java.util.List;

import org.apache.solr.client.solrj.beans.Field;

public class Product {

	@Field
	private String includes;
	@Field
	private Integer popularity;
	@Field
	private Double price;
	@Field
	private String priceC;
	@Field
	private String name;
	@Field
	private Double weight;
	@Field
	private String lastModified;
	@Field
	private String manu;
	@Field
	private String id;
	@Field
	private List<String> features;
	@Field
	private List<String> cat;

	public String getIncludes() {
		return includes;
	}

	public void setIncludes(String includes) {
		this.includes = includes;
	}

	public Integer getPopularity() {
		return popularity;
	}

	public void setPopularity(Integer popularity) {
		this.popularity = popularity;
	}

	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getPriceC() {
		return priceC;
	}

	public void setPriceC(String priceC) {
		this.priceC = priceC;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Double getWeight() {
		return weight;
	}

	public void setWeight(Double weight) {
		this.weight = weight;
	}

	public String getLastModified() {
		return lastModified;
	}

	public void setLastModified(String lastModified) {
		this.lastModified = lastModified;
	}

	public String getManu() {
		return manu;
	}

	public void setManu(String manu) {
		this.manu = manu;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public List<String> getFeatures() {
		return features;
	}

	public void setFeatures(List<String> features) {
		this.features = features;
	}

	public List<String> getCat() {
		return cat;
	}

	public void setCat(List<String> cat) {
		this.cat = cat;
	}

	@Override
	public String toString() {
		return "Product [includes=" + includes + ", popularity=" + popularity
				+ ", price=" + price + ", priceC=" + priceC + ", name=" + name
				+ ", weight=" + weight + ", lastModified=" + lastModified
				+ ", manu=" + manu + ", id=" + id + ", features=" + features
				+ ", cat=" + cat + "]";
	}

}
