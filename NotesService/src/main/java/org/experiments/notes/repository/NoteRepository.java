package org.experiments.notes.repository;

import org.experiments.notes.model.Note;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface NoteRepository extends MongoRepository<Note, String> {
    @Query("{name:'?0'}")
    Note findNoteByName(String name);

    @Query(value="{noteContent:'?0'", fields ="{'name: 1")
    List<Note> findAll(String noteContent);

    public long count();
}
