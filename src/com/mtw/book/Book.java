 package com.mtw.book;

import java.util.Date;
import java.util.List;

import com.mtw.author.Author;
import com.mtw.publisher.Publisher;

public class Book {
	private String isbn;
	
	private String title;
	
	private Date pubdate;
	
	private Long pubid;
	
	private Float cost;
	
	private Float retail;
	
	private String category;
	
	private Publisher publisher;
	
	private List<Author>authors;

	public List<Author> getAuthors() {
		return authors;
	}

	public void setAuthors(List<Author> authors) {
		this.authors = authors;
	}

	public Book(String isbn, String title, Date pubdate, Long pubid, Float cost, Float retail, String category,
			Publisher publisher) {
		super();
		this.isbn = isbn;
		this.title = title;
		this.pubdate = pubdate;
		this.pubid = pubid;
		this.cost = cost;
		this.retail = retail;
		this.category = category;
		this.publisher = publisher;
	}

	public Book() {
		super();
		// TODO Auto-generated constructor stub
	}

	public String getIsbn() {
		return isbn;
	}

	public void setIsbn(String isbn) {
		this.isbn = isbn;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public Date getPubdate() {
		return pubdate;
	}

	public void setPubdate(Date pubdate) {
		this.pubdate = pubdate;
	}

	public Long getPubid() {
		return pubid;
	}

	public void setPubid(Long pubid) {
		this.pubid = pubid;
	}

	public Float getCost() {
		return cost;
	}

	public void setCost(Float cost) {
		this.cost = cost;
	}

	public Float getRetail() {
		return retail;
	}

	public void setRetail(Float retail) {
		this.retail = retail;
	}

	public String getCategory() {
		return category;
	}

	public void setCategory(String category) {
		this.category = category;
	}

	public Publisher getPublisher() {
		return publisher;
	}

	public void setPublisher(Publisher publisher) {
		this.publisher = publisher;
	}

	@Override
	public String toString() {
		return "Book [isbn=" + isbn + ", title=" + title + ", pubdate=" + pubdate + ", pubid=" + pubid + ", cost="
				+ cost + ", retail=" + retail + ", category=" + category + ", publisher=" + publisher + ", authors="
				+ authors + "]";
	}

	
	
	
}
