package com.mumscheduler.section.model;

import com.mumscheduler.course.model.Course;
import com.mumscheduler.faculty.model.Faculty;
import com.mumscheduler.student.model.Student;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.Transient;

@Entity
public class Section {

	@Id
	@GeneratedValue
	private Long id;
	
	@ManyToOne
	private Course course;
	@ManyToOne
	private Faculty faculty;
	
	@ManyToMany
	private Set<Student> students = new HashSet<Student>();
	
	@Transient
	private Integer remainingSeats;
	
	@Transient
	private Integer enrolledStudents;
	
	private Integer capacity;

	public Section() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Course getCourse() {
		return course;
	}

	public void setCourse(Course course) {
		this.course = course;
	}

	public Faculty getFaculty() {
		return faculty;
	}

	public void setFaculty(Faculty faculty) {
		this.faculty = faculty;
	}
	
	public Integer getCapacity() {
		return capacity;
	}

	public void setCapacity(Integer capacity) {
		this.capacity = capacity;
	}

	public Set<Student> getStudents() {
		return students;
	}

	public void setStudents(Set<Student> students) {
		this.students = students;
	}

	public Integer getRemainingSeats() {
		return capacity - students.size();
	}

	public void setRemainingSeats(Integer remainingSeats) {
		this.remainingSeats = remainingSeats;
	}
	
	public Integer getEnrolledStudents() {
		return students.size();
	}

	public void setEnrolledStudents(Integer enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}

	@Override
	public String toString() {
		return String.format("%s - %s", course.toString(), faculty.toString());
	}
}
