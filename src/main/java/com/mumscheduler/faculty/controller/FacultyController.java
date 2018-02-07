package com.mumscheduler.faculty.controller;

import com.mumscheduler.block.service.BlockServiceInterface;
import com.mumscheduler.course.service.CourseServiceInterface;
import com.mumscheduler.faculty.model.Faculty;
import com.mumscheduler.faculty.service.FacultyServiceInterface;
import com.mumscheduler.faculty.validator.FacultyValidator;

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
public class FacultyController {

	@Autowired
	private FacultyServiceInterface facultyService;

	@Autowired
	private BlockServiceInterface blockService;

	@Autowired
	private CourseServiceInterface courseService;

	@Autowired
	private FacultyValidator facultyValidator;
	
//	@InitBinder
//	protected void initBinder(WebDataBinder binder) {
//		binder.addValidators(facultyValidator);
//	}
	
	/**
	 * change this when the URLs change this variable sets the current tab to active
	 * in the HTML
	 */
	private final String activeTab = "faculty";

	/**
	 * Display all the courses
	 * 
	 * @return
	 */
	@GetMapping("/faculty")
	public String facultyHome(Model model) {
		model.addAttribute("activeTab", this.activeTab);
		Iterable<Faculty> faculty = facultyService.getFacultyList();
		model.addAttribute("faculties", faculty);
		return "faculty/faculty-list";
	}

	/**
	 * Process creating a new course Return to the courses form after a course has
	 * been saved
	 * 
	 * @return
	 */
	@PostMapping("/faculty")
	public String createNewFaculty(@Valid @ModelAttribute("faculty") Faculty faculty, 
			BindingResult bindingResult, Model model) {
		facultyValidator.validate(faculty, bindingResult);
		if (bindingResult.hasErrors()) {
			model.addAttribute("activeTab", this.activeTab);
			model.addAttribute("faculty", faculty);
			model.addAttribute("allCourses", courseService.getCourseList());
			model.addAttribute("allBlocks", blockService.getBlockList());
			return "faculty/faculty-form";
		}
		facultyService.save(faculty);
		return "redirect:/faculty";
	}

	/**
	 * Display an empty form to create a new course
	 * 
	 * add all courses to the form, to be displayed in faculty courses preferences
	 * to further decouple this, we could call using a webservice
	 * 
	 * @return
	 */
	@GetMapping("/faculty/new")
	public String displayNewFacultyForm(Model model, @ModelAttribute("faculty") Faculty faculty) {
		model.addAttribute("activeTab", this.activeTab);
		model.addAttribute("allCourses", courseService.getCourseList());
		model.addAttribute("allBlocks", blockService.getBlockList());
		model.addAttribute("faculty", faculty);
		return "faculty/faculty-form";
	}

	/**
	 * Display a form pre-populated with the course details to edit add all courses
	 * to the form, to be displayed in faculty courses preferences to further
	 * decouple this, we could call using a web-service
	 * 
	 * @return
	 */
	@RequestMapping(value = "/faculty/{id}", method = RequestMethod.GET)
	public String displayEditFacultyForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("activeTab", this.activeTab);
		model.addAttribute("allCourses", courseService.getCourseList());
		model.addAttribute("faculty", facultyService.getFaculty(id));
		model.addAttribute("allBlocks", blockService.getBlockList());
		return "faculty/faculty-form";
	}

	/**
	 * Handle updating a course
	 * 
	 * @return
	 */
	@RequestMapping(value = "/faculty/{id}", method = RequestMethod.POST)
	public String updateFaculty() {
		return "redirect:/faculty-list";
	}
	
	/**
	 * display a faculty profile
	 * 
	 * @return
	 */
	@RequestMapping(value = "/faculty/{id}/profile", method = RequestMethod.GET)
	public String viewFacultyProfile(Model model, @PathVariable("id") Long id) {
		model.addAttribute("activeTab", this.activeTab);
		model.addAttribute("faculty", facultyService.getFaculty(id));
		return "faculty/faculty";
	}
}
