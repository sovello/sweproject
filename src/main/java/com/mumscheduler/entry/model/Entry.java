package com.mumscheduler.entry.model;

import com.mumscheduler.block.model.Block;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

@Entity
public class Entry {

	@Id
	@GeneratedValue
	private Long id;
	@NotNull
	private String month;
	@NotNull
	private Integer year;
	private Integer mppStudents;
	private Integer fppStudents;
	private Integer totalStudents;
	
	@OneToMany
	private Set<Block> blocks;
	public Entry() {}

	public Entry(String month, Integer year, Integer mppStudents, Integer fppStudents,
			Integer totalStudents) {
		this.month = month;
		this.year = year;
		this.mppStudents = mppStudents;
		this.fppStudents = fppStudents;
		this.totalStudents = mppStudents + fppStudents;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getMonth() {
		return month;
	}

	public void setMonth(String month) {
		this.month = month;
	}

	public Integer getYear() {
		return year;
	}

	public void setYear(Integer year) {
		this.year = year;
	}

	public Integer getMppStudents() {
		return mppStudents;
	}

	public void setMppStudents(Integer mppStudents) {
		this.mppStudents = mppStudents;
	}

	public Integer getFppStudents() {
		return fppStudents;
	}

	public void setFppStudents(Integer fppStudents) {
		this.fppStudents = fppStudents;
	}

	public Integer getTotalStudents() {
		return totalStudents;
	}

	public void setTotalStudents(Integer totalStudents) {
		this.totalStudents = totalStudents;
	}
	
	@Override
	public String toString() {
		return String.format("%s %d", month, year);
	}
	
	@Override
	public boolean equals(Object obj) {
		if( obj == null) return false;
		if( this.getClass() != obj.getClass()) return false;
		Entry e = (Entry) obj;
		boolean monthCompare = month.equals(e.getMonth());
		if(monthCompare) {
			return year.equals(e.getYear());
		}
		return monthCompare;
	}
	
	/**
	 * for some reason this method will case a NullPointerException when loading the entry form
	@Override
	public int hashCode() {
		return 7 * 3 + month.hashCode() + (year.hashCode() * 2);
	}
	*/
}
