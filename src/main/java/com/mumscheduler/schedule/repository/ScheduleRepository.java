package com.mumscheduler.schedule.repository;

import com.mumscheduler.schedule.model.Schedule;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("scheduleRepository")
public interface ScheduleRepository extends JpaRepository<Schedule, Long> {

}
