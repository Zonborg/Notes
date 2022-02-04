package org.experiments.notes.exceptions;

public class NoteNotFoundException extends RuntimeException{

    NoteNotFoundException(String id){
        super("Could not find note " + id);
    }
}
