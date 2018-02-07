package com.mumscheduler.schedule.model;

import com.mumscheduler.entry.model.Entry;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToOne;

@Entity
public class Schedule {

	@Id
	@GeneratedValue
	private Long id;

	@OneToOne
	private Entry entry;
	
	@Enumerated(EnumType.STRING)
	private ScheduleStatus status;
	
	public Schedule() {}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Entry getEntry() {
		return entry;
	}

	public void setEntry(Entry entry) {
		this.entry = entry;
	}

	public ScheduleStatus getStatus() {
		return status;
	}

	public void setStatus(ScheduleStatus status) {
		this.status = status;
	}
	
	@Override
	public String toString() {
		return String.format("%s - %s", entry.toString(), status.toString());
	}
	
}
