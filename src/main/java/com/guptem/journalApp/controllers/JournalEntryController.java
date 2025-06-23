package com.guptem.journalApp.controllers;

import com.guptem.journalApp.entities.JournalEntry;
import com.guptem.journalApp.services.JournalEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping(path = "/journal")
public class JournalEntryController {

    @Autowired
    private JournalEntryService journalEntryService;

    private Map<Long, JournalEntry> journalEntries = new HashMap<>();

    @GetMapping
    public List<JournalEntry> getAllEntries() {
        return journalEntryService.getAllEntries();
    }

    @PostMapping
    public ResponseEntity<JournalEntry> createMapping(@RequestBody JournalEntry myEntry) {
        JournalEntry savedEntry = journalEntryService.saveEntry(myEntry);
        return new ResponseEntity<>(savedEntry, HttpStatus.CREATED);
    }

    @GetMapping(path = "/getId/{myId}")
    public ResponseEntity<JournalEntry> getJournalEntryById(@PathVariable Long myId) {
        return new ResponseEntity<>(journalEntryService.getEntryById(myId), HttpStatus.OK);
    }


    @DeleteMapping(path = "/delete/{myId}")
    public ResponseEntity<?> deleteJournalEntry(@PathVariable Long myId) {
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PutMapping(path = "/update/id/{myId}")
    public ResponseEntity<?> updateJournalEntryById(@PathVariable Long myId,
                                               @RequestBody JournalEntry journalEntry)  {
        return new ResponseEntity<>(journalEntry, HttpStatus.OK);

    }
}
