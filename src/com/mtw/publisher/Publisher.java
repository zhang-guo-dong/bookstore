package com.mtw.publisher;

public class Publisher {
	private Long pubid;
	private String name;
	private String contact;
	private String phone;
	
	public Publisher(Long pubid, String name, String contact, String phone) {
		super();
		this.pubid = pubid;
		this.name = name;
		this.contact = contact;
		this.phone = phone;
	}
	
	public Publisher() {
		super();
		// TODO Auto-generated constructor stub
	}
	
	
	@Override
	public String toString() {
		return "Publisher [pubid=" + pubid + ", name=" + name + ", contact=" + contact + ", phone=" + phone + "]";
	}
	
	public Long getPubid() {
		return pubid;
	}
	public void setPubid(long pubid) {
		this.pubid = pubid;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getContact() {
		return contact;
	}
	public void setContact(String contact) {
		this.contact = contact;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	
	
}
