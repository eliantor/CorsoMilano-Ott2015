package me.eliantor.notesmanager.ui;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.TextView;

import java.util.Objects;

import me.eliantor.notesmanager.R;
import me.eliantor.notesmanager.model.Note;

/**
 * Created by aktor on 21/10/15.
 */
public class DetailsActivity extends AppCompatActivity {
    private static final String NOTE_PARAM = "notesmanager:note_param";

    public static void startImplicit(Context starter,Note note){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse("http://sisal.it/verify/scheda/dsroiwen12oe77y12"));


    }

    public static void start(Context starter,Note note){

        Intent intent = new Intent(starter,DetailsActivity.class);
        intent.putExtra(NOTE_PARAM,note);

        starter.startActivity(intent);
    }


    private TextView mTitle;
    private TextView mContent;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_note);

        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mTitle = (TextView)findViewById(R.id.note_title);
        mContent = (TextView)findViewById(R.id.note_body);

        Note note = getIntent().getParcelableExtra(NOTE_PARAM);

        mTitle.setText(note.getTitle());
        mContent.setText(note.getContent());

    }
}
