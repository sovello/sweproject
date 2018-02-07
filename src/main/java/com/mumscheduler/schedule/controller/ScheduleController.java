package com.mumscheduler.schedule.controller;

import com.mumscheduler.block.service.BlockServiceInterface;
import com.mumscheduler.entry.service.EntryServiceInterface;
import com.mumscheduler.schedule.model.Schedule;
import com.mumscheduler.schedule.service.ScheduleServiceInterface;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

@Controller
public class ScheduleController {

	@Autowired
	private EntryServiceInterface entryService;
	
	@Autowired
	private ScheduleServiceInterface scheduleService;
	
	@Autowired
	private BlockServiceInterface blockService;
	
	/**
	 * change this when the URLs change
	 * this variable sets the current tab to active in the HTML
	 */
	private final String activeTab = "schedules";
	
	/**
	 * Display all the courses
	 * @return
	 */
	@GetMapping("/schedules")
	public String sectionHome(Model model) {
		model.addAttribute("activeTab", this.activeTab);
		Iterable<Schedule> schedules = scheduleService.getScheduleList();
		model.addAttribute("schedules", schedules);
		return "schedule/schedule-list";
	}
	
	/**
	 * Process creating a new course
	 * Return to the courses form after a course has been saved
	 * @return
	 */
	@PostMapping("/schedules")
	public String createNewSchedule(@Valid @ModelAttribute("schedule") Schedule schedule, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "schedule/schedule-form";
		}
		scheduleService.generateSchedule(schedule.getEntry());
		scheduleService.save(schedule);
		return "redirect:/schedules";
	}
	
	/**
	 * Display an empty form to create a new course
	 * 
	 * add all courses to the form, to be displayed in section courses 
	 * preferences
	 * to further decouple this, we could call using a webservice
	 * 
	 * @return
	 */
	@GetMapping("/schedules/new")
	public String displayNewSectionForm(Model model, @ModelAttribute("schedule") Schedule schedule) {
		model.addAttribute("activeTab", this.activeTab);
		model.addAttribute("allEntries", entryService.getEntryList());
		model.addAttribute("schedule", schedule);
		return "schedule/schedule-form";
	}
	
	/**
	 * Display a form pre-populated with the course details to edit
	 * add all courses to the form, to be displayed in section courses 
	 * preferences
	 * to further decouple this, we could call using a web-service
	 * @return
	 */
	@RequestMapping(value="/schedules/{id}", method=RequestMethod.GET)
	public String displayEditSectionForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("activeTab", this.activeTab);
		model.addAttribute("allBlocks", blockService.getBlockList());
		model.addAttribute("allEntries", entryService.getEntryList());
		model.addAttribute("schedule", scheduleService.getSchedule(id));
		return "schedule/schedule-form";
	}
	
	/**
	 * Handle updating a course
	 * @return
	 */
	@RequestMapping(value="/schedules/{id}", method=RequestMethod.POST)
	public String updateSection() {
		return "redirect:/schedules";
	}
}
