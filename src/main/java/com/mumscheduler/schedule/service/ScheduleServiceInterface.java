package com.mumscheduler.schedule.service;

import com.mumscheduler.block.model.Block;
import com.mumscheduler.schedule.model.Schedule;
import com.mumscheduler.schedule.model.ScheduleStatus;

import java.util.List;

public interface ScheduleServiceInterface {

	Schedule save(Schedule schedule);
	Schedule getSchedule(Long id);
	List<Schedule> getScheduleList();
	void changeScheduleStatus(Long scheduleId, ScheduleStatus status);
	void addBlock(Long scheduleId, Block block);
}
