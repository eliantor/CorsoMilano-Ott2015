package me.eliantor.notesmanager.content;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by aktor on 23/10/15.
 */
class NotesDbHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "db_note.db";
    private static final int DB_VERSION = 3;

    public NotesDbHelper(Context context) {
        super(context, DB_NAME, null, DB_VERSION);
    }

    @Override
    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    @Override
    public void onConfigure(SQLiteDatabase db) {
        super.onConfigure(db);
    }


    @Override
    public void onCreate(SQLiteDatabase db) {
        db.execSQL(CREATE_NOTE_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL(DROP_TABLE);
        onCreate(db);
    }

    @Override
    public void onDowngrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        super.onDowngrade(db, oldVersion, newVersion);
    }

    private static final String DROP_TABLE = "DROP TABLE "+ NoteContract.Note.PATH;

    private static final String CREATE_NOTE_TABLE = "CREATE TABLE IF NOT EXISTS "+
            NoteContract.Note.PATH+" ("+
             NoteContract.Note.ID + " INTEGER PRIMARY KEY AUTOINCREMENT, "+
            NoteContract.Note.TITLE+ " TEXT,"+
            NoteContract.Note.CONTENT+ " TEXT, "+
            NoteContract.Note.FAVOURITE+ " INTEGER )";
}
