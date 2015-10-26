package me.eliantor.notesmanager.content;

import android.content.ContentProvider;
import android.content.ContentUris;
import android.content.ContentValues;
import android.content.UriMatcher;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteQueryBuilder;
import android.net.Uri;
import android.os.Bundle;
import android.os.ParcelFileDescriptor;
import android.support.annotation.Nullable;
import android.util.Log;

import java.io.FileNotFoundException;

/**
 * Created by aktor on 23/10/15.
 */
public class NotesProvider extends ContentProvider {

    private static final int ALL_NOTES = 1;
    private static final int ONE_NOTE = 2;

    // content://dsdas/notes
    // content://dsdas/blah

    private static UriMatcher createRouter(){
        UriMatcher matcher = new UriMatcher(UriMatcher.NO_MATCH);
        matcher.addURI(NoteContract.AUTHORITY, NoteContract.Note.PATH,ALL_NOTES);

        matcher.addURI(NoteContract.AUTHORITY, NoteContract.Note.PATH+"/#",ONE_NOTE);
        return matcher;
    }

    private NotesDbHelper mDbHelper;
    private static final UriMatcher MATCHER = createRouter();

    @Override
    public boolean onCreate() {
        mDbHelper = new NotesDbHelper(getContext());
        return true;
    }


    @Nullable
    @Override
    public Cursor query(Uri uri, String[] projection,
                        String selection, String[] selectionArgs, String sortOrder) {
        int match = MATCHER.match(uri);
        SQLiteQueryBuilder sqb = new SQLiteQueryBuilder();

        switch (match){
            case ALL_NOTES:
                sqb.setTables(NoteContract.Note.PATH);
                break;
            case ONE_NOTE:
                sqb.setTables(NoteContract.Note.PATH);
                sqb.appendWhere(NoteContract.Note.ID+" = "
                    + ContentUris.parseId(uri));
                break;
            default:
                throw new UnsupportedOperationException("Wrong uri");
        }
        SQLiteDatabase db = mDbHelper.getReadableDatabase();
        Cursor notes = sqb.query(db, projection, selection, selectionArgs, sortOrder, null, null);
        if (notes != null ){
            notes.setNotificationUri(getContext().getContentResolver(),
                    NoteContract.Note.CONTENT_URI);
        }
        return notes;
    }


    @Nullable
    @Override
    public Bundle call(String method, String arg, Bundle extras) {
        return super.call(method, arg, extras);
    }

    @Nullable
    @Override
    public ParcelFileDescriptor openFile(Uri uri, String mode) throws FileNotFoundException {
        return super.openFile(uri, mode);
    }

    @Nullable
    @Override
    public Uri insert(Uri uri, ContentValues values) {
        if (MATCHER.match(uri)== ALL_NOTES) {
           // validate(values);
            SQLiteDatabase db = mDbHelper.getWritableDatabase();
//            try {
                long insert = db.insert(NoteContract.Note.PATH, null, values);
//            } catch (SQLiteConstraintException e){
//
//            }
            if (insert !=-1) {
                Uri ret = ContentUris.withAppendedId(NoteContract.Note.CONTENT_URI,
                        insert);
                getContext().getContentResolver()
                        .notifyChange(NoteContract.Note.CONTENT_URI,null,true);
                Log.d("SAVED","Saved note: "+ret);
                return ret;
            }
            return null;

        }
        return null;
    }

    @Override
    public int delete(Uri uri, String selection, String[] selectionArgs) {
        return 0;
    }

    @Override
    public int update(Uri uri, ContentValues values, String selection, String[] selectionArgs) {
        return 0;
    }

    @Nullable
    @Override
    public String getType(Uri uri) {
        return null;
    }



}
