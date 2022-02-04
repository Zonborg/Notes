import { HttpClient } from '@angular/common/http';
import { Injectable } from '@angular/core';

import {Note} from './note';
import { environment } from '../environments/environment';


@Injectable()
export class NoteService {


    environmentName = environment.apiUrl;
    constructor(private http:HttpClient) {
    }

    getAllNotes(){
        try {
            const response: any = this.http.get(this.environmentName);
            return response;
        } catch (error){
            console.error("An error occurred ", error);
        }
    }

    getNote(noteId: string){
        try {
            const response: any = this.http.put(this.environmentName, noteId);
            console.log(response);
            return response;
        } catch (error){
            console.error("An error occurred ", error);
        }
    }

    addNote(note: Note){
        try {
            const response: any = this.http.post<Note>(this.environmentName, note);
            return response;
        } catch (error){
            console.error("An error occurred ", error);
        }

    }

    updateNote(note:Note){
        try {
            const response: any = this.http.put(this.environmentName, note);
            console.log(response);
            return response;
        } catch (error){
            console.error("An error occurred ", error);
        }
    }

    deleteNote(id:string){
        try {
            const response: any = this.http.delete<string>(this.environmentName+ id);
            return response;
        } catch (error){
            console.error("An error occurred ", error);
        }
    }


}