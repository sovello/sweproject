package com.mumscheduler.schedule.service;

import com.mumscheduler.entry.model.Entry;
import com.mumscheduler.schedule.model.Schedule;
import com.mumscheduler.schedule.model.ScheduleStatus;

import java.util.List;

public interface ScheduleServiceInterface {
	Schedule save(Schedule schedule);
	Schedule getSchedule(Long id);
	List<Schedule> getSchedule(ScheduleStatus status);
	List<Schedule> getSchedule(Entry entry);
	Entry generateSchedule(Entry entry);
	Iterable<Schedule> getScheduleList();
}
