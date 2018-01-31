package com.mumscheduler.section.service;

import com.mumscheduler.section.model.Section;

import java.util.List;

public interface SectionServiceInterface {
	
	Section getSection(Long id);
	Section save(Section section);
	List<Section> getSectionList();
}
