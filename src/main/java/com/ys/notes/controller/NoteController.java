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
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;
import java.util.List;

@RequiredArgsConstructor
@RestController
public class NoteController {


    @Autowired
    private final NoteService noteService;

    @GetMapping("/api/note")
    public ResponseEntity<List<Note>> getAllNotes() {
        List<Note> notes = noteService.listAll();
        return new ResponseEntity<>(notes, HttpStatus.OK);
    }

    @DeleteMapping("/api/note/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable Long id) {
        noteService.deleteById(id);
    }

    @PutMapping("/api/note/{id}")
    public NoteResponse updateNote(@PathVariable Long id, @RequestBody NoteUpdateRequest request) {
        return noteService.update(id, request);
    }

    @GetMapping("/api/note/{id}")
    public NoteResponse findById(@PathVariable Long id) {
        return noteService.getById(id);
    }

    @PostMapping("/api/note")
    @ResponseStatus(HttpStatus.CREATED)
    public NoteResponse createNew(@RequestBody NoteAddRequest request) {
        System.out.println("request = " + request);
        return noteService.create(request);
    }

    @GetMapping("/note/list")
    public ModelAndView list() {
        ModelAndView model = new ModelAndView("note/all");
        model.addObject("notes", noteService.listAll());
        return model;
    }

    @PostMapping(value = "/note/delete")
    public RedirectView delete(@RequestParam(name = "id") long id) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/note/list");
        noteService.deleteById(id);
        return redirectView;
    }

    @GetMapping(value = "/note/edit")
    public ModelAndView editPage(Model model, @RequestParam(name = "id") long id) {
        Note note = noteService.getById(id);
        model.addAttribute("note", note);
        return new ModelAndView("/note/edit");
    }

    @PostMapping(value = "/note/edit")
    public RedirectView edit(@ModelAttribute Note note) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/note/list");
        noteService.update(note);
        return redirectView;
    }


    @GetMapping(value = "/note/create")
    public ModelAndView create() {
        return new ModelAndView("/note/create");
    }

    @PostMapping(value = "/note/create")
    public RedirectView create(@ModelAttribute Note note) {
        noteService.add(note);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/note/list");
        return redirectView;
    }

}
