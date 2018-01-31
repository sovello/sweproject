package com.mumscheduler.schedule.service;

import com.mumscheduler.block.model.Block;
import com.mumscheduler.schedule.model.Schedule;
import com.mumscheduler.schedule.model.ScheduleStatus;
import com.mumscheduler.schedule.repository.ScheduleRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("scheduleService")
public class ScheduleService implements ScheduleServiceInterface {

	@Autowired
	private ScheduleRepository scheduleRepository;
	@Override
	public Schedule save(Schedule schedule) {
		return scheduleRepository.save(schedule);
	}

	@Override
	public Schedule getSchedule(Long id) {
		return scheduleRepository.getOne(id);
	}

	@Override
	public List<Schedule> getScheduleList() {
		return scheduleRepository.findAll();
	}

	@Override
	public void changeScheduleStatus(Long scheduleId, ScheduleStatus status) {
		Schedule schedule = scheduleRepository.getOne(scheduleId);
		schedule.setStatus(status);
		scheduleRepository.save(schedule);
	}

	@Override
	public void addBlock(Long scheduleId, Block block) {
		Schedule schedule = scheduleRepository.getOne(scheduleId);
		schedule.getBlocks().add(block);
		scheduleRepository.save(schedule);
	}

	
}
