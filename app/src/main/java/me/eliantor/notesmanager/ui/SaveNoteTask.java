package me.eliantor.notesmanager.ui;

import android.content.Context;
import android.net.Uri;
import android.os.AsyncTask;

import me.eliantor.notesmanager.model.Note;

/**
 * Created by aktor on 23/10/15.
 */
public class SaveNoteTask extends AsyncTask<Note,Void,Uri> {

    public interface OnCompleteSave {
        public void onSaved(Uri uri);
    }

    private Context context;
    private OnCompleteSave saveListener;

    SaveNoteTask(Context context,OnCompleteSave saveListener){
        this.context = context.getApplicationContext();
        this.saveListener = saveListener;
    }

    @Override
    protected Uri doInBackground(Note... params) {
        return DbAccess.saveNote(context,params[0]);
    }

    @Override
    protected void onPostExecute(Uri uri) {
        super.onPostExecute(uri);
        saveListener.onSaved(uri);

    }
}
