package com.hostmdy.model;

import java.time.LocalDate;

public class History {

	private Long id;
	private String firstname;
	private String lastname;
	private String list;
	private LocalDate date;
	private Integer total;
	public History(String firstname, String lastname,String list, LocalDate date, Integer total) {
		super();
		this.firstname = firstname;
		this.lastname = lastname;
		this.list = list;
		this.date = date;
		this.total = total;
	}
	public History(Long id, String firstname, String lastname,String list, LocalDate date, Integer total) {
		super();
		this.id = id;
		this.firstname = firstname;
		this.lastname = lastname;
		this.list = list;
		this.date = date;
		this.total = total;
	}
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getFirstname() {
		return firstname;
	}
	public void setFirstname(String firstname) {
		this.firstname = firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public void setLastname(String lastname) {
		this.lastname = lastname;
	}
	public String getList() {
		return list;
	}
	public void setList(String list) {
		this.list = list;
	}
	public LocalDate getDate() {
		return date;
	}
	public void setDate(LocalDate date) {
		this.date = date;
	}
	public Integer getTotal() {
		return total;
	}
	public void setTotal(Integer total) {
		this.total = total;
	}
	
}
