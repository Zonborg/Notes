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
    @GetMapping("/notes/{id}")
    public Note getNote(@PathVariable String noteName){
        return noteRepository.findNoteByName(noteName);
    }

    @CrossOrigin
    @PutMapping("notes/{id}")
    public Note updateNote(@RequestBody Note newNote, @PathVariable String noteId){
        System.out.println("Update Method called");
        return noteRepository.findById(noteId).map(note ->{
            note.setName(newNote.getName());
            note.setNoteContent(newNote.getNoteContent());
            return noteRepository.save(note);
        }).orElseGet(() -> {
            newNote.setId(noteId);
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
