package com.mumscheduler.entry.repository;

import com.mumscheduler.entry.model.Entry;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository("entryRepository")
public interface EntryRepository extends JpaRepository<Entry, Long> {

}
