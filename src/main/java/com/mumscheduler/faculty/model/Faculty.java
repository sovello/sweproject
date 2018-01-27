package com.mumscheduler.faculty.model;

import com.mumscheduler.course.model.Course;
import com.mumscheduler.security.model.User;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.ManyToMany;

@Entity
public class Faculty extends User{

//	@Id
//	@GeneratedValue(strategy=GenerationType.AUTO)
//	private Long id;
//	
//	@NotNull
//	private String firstname;
//	private String lastname;
//	@Email
//	private String email;
	
	@ManyToMany(mappedBy="faculty")
	private Set<Course> courses;
	
	public Faculty() {}

//	public Faculty(String firstname, String lastname, String email) {
//		super();
//		this.firstname = firstname;
//		this.lastname = lastname;
//		this.email = email;
//	}
//
//	@Override
//	public Long getId() {
//		return id;
//	}
//
//	@Override
//	public void setId(Long id) {
//		this.id = id;
//	}
//
//	@Override
//	public String getFirstname() {
//		return firstname;
//	}
//
//	@Override
//	public void setFirstname(String firstname) {
//		this.firstname = firstname;
//	}
//
//	@Override
//	public String getLastname() {
//		return lastname;
//	}
//
//	@Override
//	public void setLastname(String lastname) {
//		this.lastname = lastname;
//	}
//
//	@Override
//	public String getEmail() {
//		return email;
//	}
//
//	@Override
//	public void setEmail(String email) {
//		this.email = email;
//	}

	public Set<Course> getCourses() {
		return courses;
	}

	public void setCourses(Set<Course> courses) {
		this.courses = courses;
	}
	
	
	
}
