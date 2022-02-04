package org.experiments.notes.controller;

import org.experiments.notes.model.Note;
import org.experiments.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class NotesController {

    @Autowired
    private final NoteRepository noteRepository;

    public NotesController(NoteRepository noteRepository){
        this.noteRepository = noteRepository;
    }

    @CrossOrigin
    @GetMapping("/notes")
    public List<Note> all(){
        System.out.println("Get all method called");
        return noteRepository.findAll();
    }

    @CrossOrigin
    @PostMapping("/notes")
    public Note addNote(@RequestBody Note newNote){
        System.out.println("Add note method called");
        return noteRepository.save(newNote);
    }

    @CrossOrigin
    @GetMapping("/notes/{noteId}")
    public Note getNote(@PathVariable String noteId){
        System.out.println("Get note method called");
        return noteRepository.findNoteById(noteId);
    }

    @CrossOrigin
    @PutMapping("/notes")
    public Note updateNote(@RequestBody Note newNote){
        System.out.println("Update Method called");
        return noteRepository.findById(newNote.getId()).map(note ->{
            note.setName(newNote.getName());
            note.setNoteContent(newNote.getNoteContent());
            return noteRepository.save(note);
        }).orElseGet(() -> {
            newNote.setId(newNote.getId());
            return noteRepository.save(newNote);
        });
    }

    @CrossOrigin
    @DeleteMapping("/notes/{noteId}")
    public void deleteNote(@PathVariable String noteId){
        System.out.println("Delete method called");
        noteRepository.deleteById(noteId);
    }
}
