package com.mumscheduler.entry.controller;

import com.mumscheduler.block.service.BlockServiceInterface;
import com.mumscheduler.entry.model.Entry;
import com.mumscheduler.entry.service.EntryServiceInterface;

import java.time.LocalDate;

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
public class EntryController {

	@Autowired
	private EntryServiceInterface entryService;
	
	@Autowired
	private BlockServiceInterface blockService;
	
	/**
	 * change this when the URLs change
	 * this variable sets the current tab to active in the HTML
	 */
	private final String activeTab = "entries";
	
	/**
	 * Display all the entries
	 * @return
	 */
	@GetMapping("/entries")
	public String entriesHome(Model model) {
		model.addAttribute("activeTab", this.activeTab);
		model.addAttribute("entries", entryService.getEntryList());
		return "entry/entry-list";
	}
	
	
	/**
	 * Process creating a new entry
	 * Return to the entry form after a entry has been saved
	 * @return
	 */
	@PostMapping("/entries")
	public String createNewEntry(@Valid @ModelAttribute("entry") Entry entry, 
			BindingResult bindingResult, Model model) {
		if(bindingResult.hasErrors()) {
			//we display all the blocks here because they could be editing an old block
			model.addAttribute("allBlocks", blockService.getBlockList());
			return "entry/entry-form";
		}
		entryService.save(entry);
		return "redirect:/entries";
	}
	
	/**
	 * Display an empty form to create a new entry
	 * @return
	 */
	@GetMapping("/entries/new")
	public String displayNewEntryForm(Model model, @ModelAttribute("entry") Entry entry) {
		model.addAttribute("activeTab", this.activeTab);
		model.addAttribute("entry", entry);
		//only display future blocks
		model.addAttribute("allBlocks", blockService.getAvailableBlocks(LocalDate.now()));
		return "entry/entry-form";
	}
	
	/**
	 * Display a form pre-populated with the entry details to edit
	 * @return
	 */
	@RequestMapping(value="/entries/{id}", method=RequestMethod.GET)
	public String displayEditEntryForm(@PathVariable("id") Long id, Model model) {
		model.addAttribute("activeTab", this.activeTab);
		model.addAttribute("entry", entryService.getEntry(id));
		return "entry/entry-form";
	}
	
	/**
	 * Handle updating a entry
	 * @return
	 */
	@RequestMapping(value="/entries/{id}", method=RequestMethod.POST)
	public String updateEntry() {
		return "redirect:/entries";
	}
	
	/**
	 * Handle updating a course
	 * 
	 * @return
	 */
	@RequestMapping(value = "/entries/{id}/profile", method = RequestMethod.GET)
	public String viewStudentProfile(Model model, @PathVariable("id") Long id) {
		model.addAttribute("activeTab", this.activeTab);
		model.addAttribute("entry", entryService.getEntry(id));
		return "entry/entry";
	}
}
