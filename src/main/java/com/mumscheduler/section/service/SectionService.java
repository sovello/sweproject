package com.mumscheduler.section.service;

import com.mumscheduler.section.model.Section;
import com.mumscheduler.section.repository.SectionRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("sectionService")
public class SectionService implements SectionServiceInterface {

	@Autowired
	private SectionRepository sectionRepository;
	
	@Override
	public Section getSection(Long id) {
		return sectionRepository.getOne(id);
	}

	@Override
	public Section save(Section section) {
		return sectionRepository.save(section);
	}

	@Override
	public List<Section> getSectionList() {
		return sectionRepository.findAll();
	}

}
