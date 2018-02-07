package com.mumscheduler.schedule.service;

import com.mumscheduler.block.model.Block;
import com.mumscheduler.course.model.Course;
import com.mumscheduler.course.service.CourseServiceInterface;
import com.mumscheduler.entry.model.Entry;
import com.mumscheduler.entry.service.EntryServiceInterface;
import com.mumscheduler.faculty.model.Faculty;
import com.mumscheduler.schedule.model.Schedule;
import com.mumscheduler.schedule.model.ScheduleStatus;
import com.mumscheduler.schedule.repository.ScheduleRepository;
import com.mumscheduler.section.model.Section;
import com.mumscheduler.section.service.SectionServiceInterface;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("scheduleService")
public class ScheduleService implements ScheduleServiceInterface {

	@Autowired
	private ScheduleRepository scheduleRepository;

	@Autowired
	private EntryServiceInterface entryService;
	
	@Autowired
	private SectionServiceInterface sectionService;
	
	@Autowired
	private CourseServiceInterface courseService;

	@Override
	public Entry generateSchedule(Entry entry) {
		Set<Block> entryBlocks = entry.getBlocks();
		List<Block> assignedBlocks = new ArrayList<>();
		List<Course> availableCourses = courseService.getCourseList();
		//keep track of all courses assigned so far
		List<Course> assignedCourses = new ArrayList<>();
		for(Block block: entryBlocks) {
			List<Course> blockCourses = new ArrayList<>();
			//all faculty assigned to this block -to avoid double assignment
			List<Faculty> assignedFaculty = new ArrayList<>();
			Integer sectionsForThisBlock = 3; //this should be somewhat dynamic
			
			//create required number of sections
			for(int i = 0; i < sectionsForThisBlock; i++) {
				Section section = new Section();
				Course course = availableCourses.get(i);
				//determine how many sections for one course
				//if at all this course can have multiple sections, then
				
				Set<Course> prerequisites = course.getPrerequisites();
				//course has prerequisites?
				int prereq = prerequisites.size();
				if( prereq == 0 && !assignedCourses.contains(course)) {
					section.setCourse(course);
					assignedCourses.add(course); //add this course
					sectionService.save(section);
				}
				if(prereq > 0) { //schedule the prerequisites first
					for(Course p : prerequisites) {
						if(!assignedCourses.contains(p)) {
							section.setCourse(course);
							assignedCourses.add(course);
							sectionService.save(section);
						}
					}
				}
				//assign faculty
				
			}
		}
		return entryService.save(entry);
	}
	
	@Override
	public Schedule save(Schedule schedule) {
		return scheduleRepository.save(schedule);
	}

	@Override
	public Schedule getSchedule(Long id) {
		return scheduleRepository.getOne(id);
	}

	@Override
	public List<Schedule> getSchedule(ScheduleStatus status) {
		return scheduleRepository.findByStatus(status);
	}

	@Override
	public List<Schedule> getSchedule(Entry entry) {
		return scheduleRepository.findByEntry(entry);
	}

	@Override
	public Iterable<Schedule> getScheduleList() {
		return scheduleRepository.findAll();
	}
	
}
