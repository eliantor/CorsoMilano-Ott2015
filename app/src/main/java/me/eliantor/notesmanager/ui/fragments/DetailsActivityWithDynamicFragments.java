package me.eliantor.notesmanager.ui.fragments;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;

import me.eliantor.notesmanager.R;
import me.eliantor.notesmanager.model.Note;

/**
 * Created by aktor on 23/10/15.
 */
public class DetailsActivityWithDynamicFragments extends AppCompatActivity {
    private static final String NOTE_PARAM = "notesmanager:note_param";



    public static void start(Context starter,Note note){

        Intent intent = new Intent(starter,DetailsActivityWithDynamicFragments.class);
        intent.putExtra(NOTE_PARAM,note);

        starter.startActivity(intent);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_note_with_dynfragments);

        Toolbar toolbar =(Toolbar)findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        if (savedInstanceState == null) {

            Intent intent = getIntent();
            Note note = intent.getParcelableExtra(NOTE_PARAM);

            DetailsDynamicFragment fragment = DetailsDynamicFragment.create(note);
            getSupportFragmentManager()
                    .beginTransaction()
                    .add(R.id.content_host,fragment,DetailsDynamicFragment.TAG/*string*/)
                    .commit();
        }


    }

    public void callMe(){
        Log.wtf("LOG","CALL ME!!!!");
    }
}
