package me.eliantor.notesmanager.model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Random;

/**
 * Created by aktor on 21/10/15.
 */
public class NoteRepository {

    private static final NoteRepository INSTANCE = new NoteRepository();

    private static final Random RAND = new Random();
    private static final String[] RANDOM_TEXTS ={
            "Nothing to say",
            "Remember the milk",
            "Buy a new phone",
            "Write the slides",
            "Buy the ticket",
            "Try to sleep",
            "Take a breath"
    };

    private List<Note> notes;

    public static NoteRepository getInstance(){
        return INSTANCE;
    }

    private NoteRepository(){
        notes = new ArrayList<>();
    }

    public void addNote(Note note){
        notes.add(note);
    }

    public void clearNotes(){
        notes.clear();
    }

    public void fillRandomNotes(int qty){
        clearNotes();
        for (int i = 0; i < qty; i++) {
            addRandomNote(notes,i);
        }
    }

    public void addNote(String title,String content){
        Note note = makeNote(title,content);
        addNote(note);
    }

    private Note makeNote(String title,String content){
        Note note = new Note(title,content,false,new Date(System.currentTimeMillis()));
        return note;
    }

    public List<Note> getAllNotes(){
        return Collections.unmodifiableList(notes);
    }

    public int count(){
        return notes.size();
    }

    public Note searchNote(String title){
        for (int i = 0; i < notes.size(); i++) {
            Note note = notes.get(i);
            if (note.getTitle().equals(title)){
                return note;
            }
        }
        return null;
    }

    private static void addRandomNote(List<Note> notes,int i){
        String text = RANDOM_TEXTS[RAND.nextInt(RANDOM_TEXTS.length)];
        Date now = new Date(System.currentTimeMillis());
        Note note = new Note(text,text,RAND.nextBoolean(),now);
        notes.add(note);
    }


}
