package me.eliantor.notesmanager.content;

import android.net.Uri;

/**
 * Created by aktor on 23/10/15.
 */
public final class NoteContract {
    private NoteContract() {
    }

    public static final String AUTHORITY = "me.eliantor.notesmanager";


    public static final class Note{
        private Note(){}

        static final String PATH = "notes";

        // content://me.eliantor.notesmanager/notes
        // content://me.eliantor.notesmanager/notes/1
        // content://me.eliantor.notesmanager/notes?title=dsada
        //


        public static final Uri CONTENT_URI = Uri.parse("content://"+AUTHORITY+"/"+PATH);

        public static final String TITLE = "title";
        public static final String CONTENT= "content";
        public static final String FAVOURITE = "favourite";
        public static final String ID = "_id";

    }
}
