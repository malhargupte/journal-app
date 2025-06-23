package com.guptem.journalApp.services;

import com.guptem.journalApp.entities.JournalEntry;
import com.guptem.journalApp.repository.JournalEntryRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class JournalEntryService {

    @Autowired
    private JournalEntryRepo journalEntryRepo;

    public JournalEntry saveEntry(JournalEntry journalEntry) {
        journalEntry.setDate(LocalDate.now());
        return journalEntryRepo.save(journalEntry);
    }

    public List<JournalEntry> getAllEntries() {
        return journalEntryRepo.findAll();
    }

    public JournalEntry getEntryById(Long myId) {
        return journalEntryRepo.findById(myId)
                .orElseThrow(() -> new IllegalArgumentException("No journal entry found with id: " + myId));
    }

    public void deleteById(Long myId) {
        journalEntryRepo.deleteById(myId);
    }

    public JournalEntry updateJournalById(Long myId, JournalEntry updatedEntry) {

        JournalEntry oldEntry = journalEntryRepo.findById(myId)
                .orElseThrow(() -> new IllegalArgumentException("No journal oldEntry found with id: " + myId));

        if (oldEntry != null) {
            oldEntry.setTitle(updatedEntry.getTitle() != null && !updatedEntry.getTitle().equals("") ?
                    updatedEntry.getTitle() : oldEntry.getTitle());
            oldEntry.setContent(updatedEntry.getContent() != null && !updatedEntry.getContent().equals("") ?
                    updatedEntry.getContent() : oldEntry.getContent());
            // Preserve the original date - don't update it
            // oldEntry.setDate() is not called, keeping the original date
        }

        return journalEntryRepo.save(oldEntry);
    }
}

