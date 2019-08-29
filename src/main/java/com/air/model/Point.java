package com.air.model;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.annotation.JsonRootName;

@Component
@JsonRootName("User")
public class Point {

	private Integer id;
	private String name;
	private String title;
	private String department;

	public Point() {
	}

	public Point(Integer id, String name, String title, String department) {
		this.id = id;
		this.name = name;
		this.title = title;
		this.department = department;

	}

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

}
