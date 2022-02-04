package org.experiments.notes.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

@Document("notes")
public class Note {

    @Id
    private String id;

    private String name;
    private String noteContent;

    public Note(String id, String name, String noteContent){
        super();
        this.id = id;
        this.name = name;
        this.noteContent = noteContent;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getNoteContent() {
        return noteContent;
    }

    public void setNoteContent(String noteContent) {
        this.noteContent = noteContent;
    }
}
