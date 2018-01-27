package com.mumscheduler.entry.service;

import com.mumscheduler.entry.model.Entry;

import java.util.List;

public interface EntryServiceInterface {

	public Entry save(Entry entry);
	public Entry getEntry(Long id);
	public List<Entry> getEntryList();
}
