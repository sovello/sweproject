package com.mumscheduler.schedule.model;

public enum ScheduleStatus {
	DRAFT("Draft"),
	FINALIZED("Finalized"),
	PUBLISHED("Published");
	
	private final String status;
	
	ScheduleStatus(String status){
		this.status = status;
	}
	
	@Override
	public String toString() {
		return this.status;
	}
}
