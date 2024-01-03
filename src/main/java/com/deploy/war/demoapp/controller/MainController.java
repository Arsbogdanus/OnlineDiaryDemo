package com.deploy.war.demoapp.controller;

import com.deploy.war.demoapp.DemoAppValues;
import com.deploy.war.demoapp.models.Note;
import com.deploy.war.demoapp.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
public class MainController {

    private final NoteRepository noteRepository;

    @Autowired
    public MainController(NoteRepository noteRepository) {
        this.noteRepository = noteRepository;
    }

    @GetMapping("/main")
    public String showMainMenu(Model model) {
        Integer currentPersonId = DemoAppValues.CURRENT_PERSON_ID;
        if (DemoAppValues.isLogGetIn()) {
            return "redirect:/login";
        } else {
            List<Note> personNotes = noteRepository.findByPersonID(currentPersonId);
            model.addAttribute("notes", personNotes);
            return "main";
        }
    }

    @PostMapping("/main")
    public String mainPostMapping(@RequestParam String action, @RequestParam(name = "noteId", required = false) Long noteId) {
        return switch (action) {
            case "newNoteButton" -> "redirect:/newNote";
            case "exitButton" -> "redirect:/login";
            case "noteButton" -> "redirect:/viewNote/" + noteId;
            case "mainButton" -> "redirect:/main";
            default -> "the petushka";
        };
    }
}
