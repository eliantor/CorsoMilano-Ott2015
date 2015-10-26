package me.eliantor.notesmanager.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import me.eliantor.notesmanager.R;
import me.eliantor.notesmanager.model.Note;
import me.eliantor.notesmanager.ui.DetailsActivity;

/**
 * Created by aktor on 23/10/15.
 */
public class DetailsActivityWithFragments extends AppCompatActivity {
    private static final String NOTE_PARAM = "notesmanager:note_param";



    public static void start(Context starter,Note note){

        Intent intent = new Intent(starter,DetailsActivityWithFragments.class);
        intent.putExtra(NOTE_PARAM,note);

        starter.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_note_with_fragments);

        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DetailsFragment details =(DetailsFragment) getSupportFragmentManager().findFragmentById(R.id.FragmentDetails);
        Note note = getIntent().getParcelableExtra(NOTE_PARAM);
        details.setNote(note);
        details.setListener(new MeCallable() {
            @Override
            public void callMe() {
                DetailsActivityWithFragments.this.callMe();
            }
        });

    }

    public void callMe(){
        Log.wtf("LOG","CALL ME!!!!");
    }
}
