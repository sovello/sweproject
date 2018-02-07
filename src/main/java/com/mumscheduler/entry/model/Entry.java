package com.mumscheduler.entry.model;

import com.mumscheduler.block.model.Block;

import java.time.LocalDate;
import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Transient;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

@Entity
public class Entry {

	@Id
	@GeneratedValue
	private Long id;
	private String name;
	private Integer mppNumber;
	private Integer fppNumber;
	//required
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate startdate;
	//required
	@DateTimeFormat(pattern = "yyyy-MM-dd")
	@JsonFormat(pattern = "yyyy-MM-dd")
	private LocalDate enddate;
	@Transient
	private Integer totalStudents;
	
	@ManyToMany
	private Set<Block> blocks;
	
	public Entry() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Integer getMppNumber() {
		return mppNumber;
	}

	public void setMppNumber(Integer mppNumber) {
		this.mppNumber = mppNumber;
	}

	public Integer getFppNumber() {
		return fppNumber;
	}

	public void setFppNumber(Integer fppNumber) {
		this.fppNumber = fppNumber;
	}

	public LocalDate getStartdate() {
		return startdate;
	}

	public void setStartdate(LocalDate startdate) {
		this.startdate = startdate;
	}

	public LocalDate getEnddate() {
		return enddate;
	}

	public void setEnddate(LocalDate enddate) {
		this.enddate = enddate;
	}

	public Set<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(Set<Block> blocks) {
		this.blocks = blocks;
	}

	public Integer getTotalStudents() {
		return fppNumber + mppNumber;
	}

	public void setTotalStudents(Integer totalStudents) {
		this.totalStudents = totalStudents;
	}
	
	@Override
	public String toString() {
		return String.format("%s, %s", name, startdate.toString());
	}
	
	@Override
	public boolean equals(Object obj) {
		if( obj == null) return false;
		if( this.getClass() != obj.getClass()) return false;
		Entry e = (Entry) obj;
		return name.equals(e.getName()) && startdate.equals(e.getStartdate());
	}
	
	/**
	 * for some reason this method will case a NullPointerException when loading the entry form
	@Override
	public int hashCode() {
		return 7 * 3 + month.hashCode() + (year.hashCode() * 2);
	}
	*/
}
