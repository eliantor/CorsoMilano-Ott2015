package me.eliantor.notesmanager.ui;

import android.content.ContentResolver;
import android.content.ContentValues;
import android.content.Context;
import android.content.UriMatcher;
import android.database.sqlite.SQLiteConstraintException;
import android.net.Uri;

import me.eliantor.notesmanager.content.NoteContract;
import me.eliantor.notesmanager.model.Note;

/**
 * Created by aktor on 23/10/15.
 */
public class DbAccess {

    static Uri saveNote(Context context,Note note) {
        ContentResolver resolver = context.getContentResolver();
        Uri savedNote = resolver.insert(NoteContract.Note.CONTENT_URI, valuesFromNote(note));
        return savedNote;
    }


    private static ContentValues valuesFromNote(Note note){
        ContentValues v = new ContentValues();
        v.put(NoteContract.Note.TITLE,note.getTitle());
        v.put(NoteContract.Note.CONTENT,note.getContent());
        v.put(NoteContract.Note.FAVOURITE,note.isStarred());
        return v;
    }
}
