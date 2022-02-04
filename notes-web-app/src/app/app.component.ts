import { Component } from '@angular/core';
import { NoteService } from './note.service';
import { Note } from './note'


@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})


export class AppComponent {
  notes: any[] = [];
  constructor(private noteService: NoteService){

  }
  ngOnInit(){ 
    this.noteService.getAllNotes().subscribe((response: any) => {
      this.notes = response;
      console.log(this.notes)
    });

  }

  public addNote(){
    let newNote: Note = {id: "Note3", name: "Note3", noteContent: "Doctor Who, Death in Paradise"};
    this.noteService.addNote(newNote).subscribe((response: any) => {
      this.noteService.getAllNotes();
      return response;
    })
  }

  public deleteNote(noteId: string){
    this.noteService.deleteNote(noteId).subscribe((response: any) => {
      this.noteService.getAllNotes();
      return response;
    })
  }

  public updateNote(noteId: string){
    let newNote: Note = {id: noteId, name: "Books I want", noteContent: "To Kill a MockingBird, 1984"}
    this.noteService.updateNote(newNote).subscribe((response: any) => {
      this.noteService.getAllNotes();
      return response;
    });
  }

  title = 'My Notes Application';
  counter(i: number) {
    return new Array(i);
  }
}
