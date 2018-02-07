package com.mumscheduler.schedule.repository;

import com.mumscheduler.entry.model.Entry;
import com.mumscheduler.schedule.model.Schedule;
import com.mumscheduler.schedule.model.ScheduleStatus;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("scheduleRepository")
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

	List<Schedule> findByStatus(ScheduleStatus status);

	List<Schedule> findByEntry(Entry entry);

}
