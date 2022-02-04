package org.experiments.notes.NotesService;

import org.experiments.notes.controller.NotesController;
import org.experiments.notes.model.Note;
import org.experiments.notes.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.mongodb.repository.config.EnableMongoRepositories;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

@SpringBootApplication
@EnableMongoRepositories(basePackageClasses =  NoteRepository.class)
@ComponentScan(basePackageClasses = NotesController.class)
@Service
public class NotesServiceApplication implements CommandLineRunner {

	@Autowired
	NoteRepository noteRepository;

	public static void main(String[] args){
		SpringApplication.run(NotesServiceApplication.class, args);
	}

	public void createNotes(){
		noteRepository.save(new Note("Note1", "Note1", "To Kill a Mockingbird, 1984, Brave New World"));
		noteRepository.save(new Note("Note2", "Note2", "Star Wars, Star Trek"));

	}

	public void showAllNotes() {
		noteRepository.findAll().forEach(note-> System.out.println(getNoteDetails(note)));
	}

	public void getNoteByName(String noteId){
		System.out.println("Getting note by name: " + noteId);
		Note note = noteRepository.findNoteById(noteId);
		System.out.println(getNoteDetails(note));
	}

	public void findNoteCount() {
		long count = noteRepository.count();
		System.out.println("Number of documents in collection: " + count);
	}

	public String getNoteDetails(Note note){
		System.out.println("Note Id: " + note.getId() +
				"\nNote Name: " + note.getName() +
				"\nNote Content: " + note.getNoteContent());

		return "";
	}

	public void updateNoteName(String name){
		String newName = "New name";

		List<Note> list = noteRepository.findAll(name);

		list.forEach(note -> {
			note.setName(newName);
		});

		List<Note> notesUpdated = noteRepository.saveAll(list);

		if(notesUpdated != null){
			System.out.println("Successfully updated " + notesUpdated.size() + " items.");
		}
	}

	public void deleteNote(String id){
		noteRepository.deleteById(id);
		System.out.println("Note with id " + id + " deleted.");
	}

	@Override
	public void run(String... args) throws Exception {
		createNotes();
		getNoteByName("Note1");
	}

}
