package com.mumscheduler.section.controller;

import com.mumscheduler.block.service.BlockServiceInterface;
import com.mumscheduler.course.service.CourseServiceInterface;
import com.mumscheduler.faculty.service.FacultyServiceInterface;
import com.mumscheduler.section.model.Section;
import com.mumscheduler.section.service.SectionServiceInterface;

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
public class SectionController {

	@Autowired
	private SectionServiceInterface sectionService;
	
	@Autowired
	private CourseServiceInterface courseService;
	
	@Autowired
	private BlockServiceInterface blockService;
	
	@Autowired
	private FacultyServiceInterface facultyService;
	
	/**
	 * change this when the URLs change
	 * this variable sets the current tab to active in the HTML
	 */
	private final String activeTab = "sections";
	
	
	/**
	 * Display all the courses
	 * @return
	 */
	@GetMapping("/sections")
	public String sectionHome(Model model) {
		model.addAttribute("activeTab", this.activeTab);
		Iterable<Section> sections = sectionService.getSectionList();
		model.addAttribute("sections", sections);
		return "section/section-list";
	}
	
	/**
	 * Process creating a new course
	 * Return to the courses form after a course has been saved
	 * @return
	 */
	@PostMapping("/sections")
	public String createNewSection(@Valid @ModelAttribute("section") Section section, BindingResult bindingResult) {
		if(bindingResult.hasErrors()) {
			return "section/section-form";
		}
		sectionService.save(section);
		return "redirect:/sections";
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
	@GetMapping("/sections/new")
	public String displayNewSectionForm(Model model) {
		model.addAttribute("activeTab", this.activeTab);
		model.addAttribute("allCourses", courseService.getCourseList());
		model.addAttribute("allFaculty", facultyService.getFacultyList());
		model.addAttribute("allBlocks", blockService.getBlockList());
		model.addAttribute("section", new Section());
		return "section/section-form";
	}
	
	/**
	 * Display a form pre-populated with the course details to edit
	 * add all courses to the form, to be displayed in section courses 
	 * preferences
	 * to further decouple this, we could call using a web-service
	 * @return
	 */
	@RequestMapping(value="/sections/{id}", method=RequestMethod.GET)
	public String displayEditSectionForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("activeTab", this.activeTab);
		model.addAttribute("allCourses", courseService.getCourseList());
		model.addAttribute("allFaculty", facultyService.getFacultyList());
		model.addAttribute("allBlocks", blockService.getBlockList());
		model.addAttribute("section", sectionService.getSection(id));
		return "section/section-form";
	}
	
	/**
	 * Handle updating a course
	 * @return
	 */
	@RequestMapping(value="/sections/{id}", method=RequestMethod.POST)
	public String updateSection() {
		return "redirect:/sections";
	}
}
