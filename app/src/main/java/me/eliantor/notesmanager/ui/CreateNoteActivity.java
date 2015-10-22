package me.eliantor.notesmanager.ui;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

import me.eliantor.notesmanager.R;
import me.eliantor.notesmanager.model.NoteRepository;
import me.eliantor.notesmanager.ui.lists.adapters.NoteListActivity;

/**
 *
 * Created by aktor on 21/10/15.
 */
public class CreateNoteActivity extends AppCompatActivity {

    private static final String TAG = "logtag";

    private static final String SAVED_TEXT = "saved_text";

    private EditText mTitleTextView;
    private EditText mContentTextView;
    private TextView mOutput;

    private String noteTitle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.create_note_activity);

        mTitleTextView = (EditText)findViewById(R.id.input_title);
        mContentTextView=(EditText)findViewById(R.id.input_content);
        mOutput= (TextView)findViewById(R.id.output_title);

        findViewById(R.id.show_note).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Editable text = mTitleTextView.getText();
//                displayNote(text.toString());
//                createNote();
                saveNote();
            }
        });



        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {
            Log.wtf(TAG,"started");
        } else {
            String text = savedInstanceState.getString(SAVED_TEXT);
            if (!TextUtils.isEmpty(text)){
                displayNote(text);
            }
        }
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        outState.putString(SAVED_TEXT, noteTitle);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.create_note_menu, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId() == R.id.action_create_note) {
            createNote();
            return true;
        }
        return super.onOptionsItemSelected(item);
    }


    private void createNote(){
        String title = mTitleTextView.getText().toString();
        Snackbar.make(findViewById(android.R.id.content),title,Snackbar.LENGTH_LONG).show();
    }

    private void saveNote(){
        String title = mTitleTextView.getText().toString();
        String content = mContentTextView.getText().toString();
        NoteRepository.getInstance().addNote(title,content);
        setResult(RESULT_OK);
        finish();
    }

    private void displayNote(String title){
        noteTitle = title;
        mOutput.setText(noteTitle);
    }

    public static void createNote(Activity a,int request) {
        Intent intent = new Intent(a,CreateNoteActivity.class);
        a.startActivityForResult(intent, request);
    }
}
