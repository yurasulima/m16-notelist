package com.ys.notes.controllers;

import com.ys.notes.models.Note;
import com.ys.notes.services.NoteService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.RedirectView;


@RequiredArgsConstructor
@RestController
@RequestMapping("/note")
public class NoteController {


    @Autowired
    private final NoteService noteService;


    @GetMapping("/list")
    public ModelAndView list() {
        ModelAndView model = new ModelAndView("note/all");
        model.addObject("notes", noteService.listAll());
        return model;
    }

    @PostMapping(value = "/delete")
    public RedirectView delete(@RequestParam(name = "id") long id) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/note/list");
        noteService.deleteById(id);
        return redirectView;
    }

    @GetMapping(value = "/edit")
    public ModelAndView editPage(Model model, @RequestParam(name = "id") long id) {
        Note note = noteService.getById(id);
        model.addAttribute("note", note);
        return new ModelAndView("/note/edit");
    }

    @PostMapping(value = "/edit")
    public RedirectView edit(@ModelAttribute Note note) {
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/note/list");
        noteService.update(note);
        return redirectView;
    }


    @GetMapping(value = "/create")
    public ModelAndView create() {
        return new ModelAndView("/note/create");
    }

    @PostMapping(value = "/create")
    public RedirectView create(@ModelAttribute Note note) {
        noteService.add(note);
        RedirectView redirectView = new RedirectView();
        redirectView.setUrl("/note/list");
        return redirectView;
    }

}
