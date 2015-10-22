package me.eliantor.notesmanager.ui.lists.adapters;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;

import me.eliantor.notesmanager.R;
import me.eliantor.notesmanager.model.Note;
import me.eliantor.notesmanager.model.NoteRepository;
import me.eliantor.notesmanager.ui.CreateNoteActivity;
import me.eliantor.notesmanager.ui.DetailsActivity;

/**
 *
 * Created by aktor on 21/10/15.
 */
public class NoteListActivity extends AppCompatActivity {

    private static final int ADD_NOTE = 1;

    private ListNotesAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_list_activity);
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mAdapter = new ListNotesAdapter();
        
        NoteRepository repo = NoteRepository.getInstance();
        repo.fillRandomNotes(2);

        mAdapter.addAll(repo.getAllNotes());

        ListView list = (ListView)findViewById(R.id.list);
        list.setAdapter(mAdapter);


        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Note item = mAdapter.getItem(position);
                onNoteSelected(item);
            }
        });

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu, menu);

        return true;
    }


    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if (item.getItemId()==R.id.action_add_notes){
            startEditNoteActivity();
        }
        return super.onOptionsItemSelected(item);
    }



    private void startEditNoteActivity(){
        CreateNoteActivity.createNote(this,ADD_NOTE);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_NOTE){
            if (resultCode == RESULT_OK) {
                refreshAdapter();
            } else {
                Log.wtf("NOT_SAVED","NOT SAVED");
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void onNoteSelected(Note note){
//        Intent detailsActivity = new Intent(this,DetailsActivity.class);
//        detailsActivity.putExtra(DetailsActivity.NOTE_PARAM,note);
//        startActivity(detailsActivity);

        DetailsActivity.start(this,note);
    }

    private void refreshAdapter(){

        mAdapter.addAll(NoteRepository.getInstance().getAllNotes());
    }
}
