package com.mumscheduler.entry.service;

import com.mumscheduler.entry.model.Entry;
import com.mumscheduler.entry.repository.EntryRepository;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service("entryService")
public class EntryService implements EntryServiceInterface {

	@Autowired
	private EntryRepository entryRepository;
	
	@Override
	public Entry save(Entry entry) {
		return entryRepository.save(entry);
	}

	@Override
	public Entry getEntry(Long id) {
		return entryRepository.getOne(id);
	}

	@Override
	public List<Entry> getEntryList() {
		return entryRepository.findAll();
	}

}
