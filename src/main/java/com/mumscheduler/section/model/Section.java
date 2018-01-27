package com.mumscheduler.section.model;

import com.mumscheduler.block.model.Block;
import com.mumscheduler.course.model.Course;
import com.mumscheduler.faculty.model.Faculty;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.validation.constraints.NotNull;

@Entity
public class Section {

	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	@ManyToOne
	private Course course;
	@ManyToOne
	private Faculty faculty;
	@NotNull
	@ManyToOne
	private Block block;
	private Integer enrolledStudents;
	private Integer sectionNumber;
	
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

	public Block getBlock() {
		return block;
	}

	public void setBlock(Block block) {
		this.block = block;
	}

	public Integer getEnrolledStudents() {
		return enrolledStudents;
	}

	public void setEnrolledStudents(Integer enrolledStudents) {
		this.enrolledStudents = enrolledStudents;
	}
	
	public Integer getSectionNumber() {
		return sectionNumber;
	}

	public void setSectionNumber(Integer sectionNumber) {
		this.sectionNumber = sectionNumber;
	}
	
	@Override
	public String toString() {
		return String.format("%s - %s", course.toString(), block.toString());
	}
}
