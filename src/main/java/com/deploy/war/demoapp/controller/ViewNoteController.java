package com.deploy.war.demoapp.controller;


import com.deploy.war.demoapp.DemoAppValues;
import com.deploy.war.demoapp.models.Note;
import com.deploy.war.demoapp.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;


@Controller
public class ViewNoteController {
    @Autowired
    private NoteRepository noteRepository;
    private Long id;


    @GetMapping("/viewNote/{id}")
    public String viewNote(@PathVariable Long id, Model model) {
        if (DemoAppValues.isLogGetIn()) {
            return "redirect:/login";
        } else {
            this.id = id;
            Optional<Note> optionalNote = noteRepository.findById(id);
            if (optionalNote.isPresent()) {
                model.addAttribute("note", optionalNote.get());
                return "viewNote";
            } else {
                return "noteNotFound";
            }
        }
    }

    @PostMapping("/viewNote")
    public String viewNotePostMapping(@RequestParam String action, @RequestParam String theme, @RequestParam String content) {
        return switch (action) {
            case "mainButton" -> "redirect:/main";
            case "recreationButton" -> "redirect:/newNote";
            case "exitButton" -> "redirect:/login";
            case "updateButton" -> {
                updateNote(id, theme, content);
                yield "redirect:/main";
            }
            case "deleteButton" -> {
                deleteNote(id);
                yield "redirect:/main";
            }
            case "cancelButton" -> "redirect:/main";
            default -> "the petushka";
        };
    }

    private void updateNote(Long id, String newTheme, String newContent) {
        Note existingNote = noteRepository.myFindById(id);

        if (existingNote != null) {
            existingNote.setTheme(newTheme);
            existingNote.setContent(newContent);

            noteRepository.save(existingNote);
        }
    }

    public void deleteNote(Long id) {
        if (noteRepository.existsById(id)) {
            noteRepository.deleteById(id);
        }
    }
}
