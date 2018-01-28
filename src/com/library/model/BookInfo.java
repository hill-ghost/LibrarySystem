package com.library.model;

import java.sql.Date;

public class BookInfo {
	private String id;
	private String typeId;
	private String writer;
	private String translator;
	private String publisher;
	private Date date;
	private String name;
	private String num;
	private Double price;
	private String rest;
	
	public Double getPrice() {
		return price;
	}

	public void setPrice(Double price) {
		this.price = price;
	}

	public String getId() {
		return id;
	}
	
	public void setId(String id) {
		this.id = id;
	}
	
	public String getTypeId() {
		return typeId;
	}
	
	public void setTypeId(String typeId) {
		this.typeId = typeId;
	}
	
	public String getWriter() {
		return writer;
	}
	
	public void setWriter(String writer) {
		this.writer = writer;
	}
	
	public String getTranslator() {
		return translator;
	}
	
	public void setTranslator(String translator) {
		this.translator = translator;
	}
	
	public String getPublisher() {
		return publisher;
	}
	
	public void setPublisher(String publisher) {
		this.publisher = publisher;
	}
	
	public Date getDate() {
		return date;
	}
	
	public void setDate(Date date) {
		this.date = date;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getNum() {
		return num;
	}
	
	public void setNum(String num) {
		this.num = num;
	}
	
	public String getRest() {
		return rest;
	}
	
	public void setRest(String rest) {
		this.rest = rest;
	}
}
