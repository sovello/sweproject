package com.mumscheduler.schedule.model;

import com.mumscheduler.block.model.Block;
import com.mumscheduler.entry.model.Entry;

import java.util.Set;

import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;

@Entity
public class Schedule {

	@Id
	@GeneratedValue
	private Long id;
	
	@OneToMany
	private Set<Block> blocks;
	
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

	public Set<Block> getBlocks() {
		return blocks;
	}

	public void setBlocks(Set<Block> blocks) {
		this.blocks = blocks;
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
	
}
