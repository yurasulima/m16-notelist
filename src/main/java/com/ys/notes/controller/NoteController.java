package com.ys.notes.controller;

import com.ys.notes.model.Note;
import com.ys.notes.model.dto.NoteAddRequest;
import com.ys.notes.model.dto.NoteResponse;
import com.ys.notes.model.dto.NoteUpdateRequest;
import com.ys.notes.service.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api")
public class NoteController {


    @Autowired
    private final NoteService noteService;

    @GetMapping("/note")
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteService.listAll();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @DeleteMapping("/note/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        noteService.deleteById(id);
    }

    @PutMapping("/note/{id}")
    public NoteResponse updateNote(@PathVariable Long id, @RequestBody NoteUpdateRequest request) {
        return noteService.update(id, request);
    }

    @GetMapping("/note/{id}")
    public NoteResponse findById(@PathVariable Long id) {
        return noteService.getById(id);
    }

    @PostMapping("/note")
    @ResponseStatus(HttpStatus.CREATED)
    public NoteResponse createNew(@RequestBody NoteAddRequest request) {
        System.out.println("request = " + request);
        return noteService.create(request);
    }
}
