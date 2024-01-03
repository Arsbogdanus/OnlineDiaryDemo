package com.deploy.war.demoapp.controller;

import com.deploy.war.demoapp.DemoAppValues;
import com.deploy.war.demoapp.models.Note;
import com.deploy.war.demoapp.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller

public class NewNoteController {

    @Autowired
    private NoteRepository noteRepository;

    @GetMapping("/newNote")
    public String newNoteGetMapping() {
        if (DemoAppValues.isLogGetIn()) {
            return "redirect:/login";
        } else {
            return "newNote";
        }
    }

    @PostMapping("/newNote")
    public String newNotePostMapping(@RequestParam String action, @RequestParam String theme, @RequestParam String content) {

        return switch (action) {
            case "mainButton" -> "redirect:/main";
            case "newNoteButton" -> "redirect:/newNote";
            case "exitButton" -> "redirect:/login";
            case "saveButton" -> {
                addNewNote(theme, content);
                yield "redirect:/main";
            }
            case "cancelButton" -> "redirect:/main";
            default -> "the petushka";
        };
    }

    private void addNewNote(String theme, String content) {
        noteRepository.save(new Note(theme, content, DemoAppValues.CURRENT_PERSON_ID));
    }
}