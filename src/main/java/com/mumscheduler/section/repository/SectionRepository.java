package com.mumscheduler.section.repository;

import com.mumscheduler.section.model.Section;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("sectionRepository")
public interface SectionRepository extends JpaRepository<Section, Long> {

}
