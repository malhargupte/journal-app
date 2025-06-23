package com.guptem.journalApp.repository;

import com.guptem.journalApp.entities.JournalEntry;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JournalEntryRepo extends JpaRepository<JournalEntry, Long> {

}
