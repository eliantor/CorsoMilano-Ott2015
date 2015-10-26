package me.eliantor.notesmanager.ui.lists.recycler;

import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.CursorLoader;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import me.eliantor.notesmanager.R;
import me.eliantor.notesmanager.content.NoteContract;
import me.eliantor.notesmanager.model.Note;
import me.eliantor.notesmanager.model.NoteRepository;
import me.eliantor.notesmanager.ui.CreateNoteActivity;
import me.eliantor.notesmanager.ui.DetailsActivity;
import me.eliantor.notesmanager.ui.fragments.DetailsActivityWithDynamicFragments;
import me.eliantor.notesmanager.ui.fragments.DetailsActivityWithFragments;

/**
 * Created by aktor on 21/10/15.
 */
public class NoteListActivity extends AppCompatActivity implements OnNoteSelectedListener{

    private static final int ADD_NOTE = 1;

    private NotesAdapter mNotesAdapter;
    private LoaderManager.LoaderCallbacks<Cursor> callbacks = new LoaderManager.LoaderCallbacks<Cursor>() {
        @Override
        public Loader<Cursor> onCreateLoader(int id, Bundle args) {
            CursorLoader loader = new CursorLoader(NoteListActivity.this,
                    NoteContract.Note.CONTENT_URI,null,null,null,null);
            return loader;
        }

        @Override
        public void onLoadFinished(Loader<Cursor> loader, Cursor data) {
            List<Note> notes = new ArrayList<>();
            while (data.moveToNext()){
                Note note = convertRow(data);
                notes.add(note);
            }
            mNotesAdapter.addAll(notes);
        }

        private Note convertRow(Cursor data) {
            return new Note(data.getString(data.getColumnIndex(NoteContract.Note.TITLE)),
                    null,false,new Date(System.currentTimeMillis()));
        }

        @Override
        public void onLoaderReset(Loader<Cursor> loader) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.note_recycler_activity);
        Toolbar toolbar =(Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        mNotesAdapter = new NotesAdapter(this);

        NoteRepository repo = NoteRepository.getInstance();
        repo.fillRandomNotes(10);

        mNotesAdapter.addAll(repo.getAllNotes());

        RecyclerView list = (RecyclerView)findViewById(R.id.recycler);

        LinearLayoutManager linearList  =
                new LinearLayoutManager(this,
                        LinearLayoutManager.VERTICAL,
                        false);
        list.setLayoutManager(linearList);

        int space = getResources().getDimensionPixelSize(R.dimen.small_pad);
        SpaceItemDecorator spacing = new SpaceItemDecorator(space);
        list.addItemDecoration(spacing);

        list.setAdapter(mNotesAdapter);

        getSupportLoaderManager().initLoader(R.id.LOAD_NOTES,null,callbacks);
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.list_menu,menu);
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
        Intent intent = new Intent(this,CreateNoteActivity.class);
        startActivityForResult(intent,ADD_NOTE);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == ADD_NOTE){
            if (resultCode == RESULT_OK) {
                refreshAdapter();
            }
        } else {
            super.onActivityResult(requestCode, resultCode, data);
        }
    }

    private void refreshAdapter(){
        mNotesAdapter.addAll(NoteRepository.getInstance().getAllNotes());
    }

    @Override
    public void onNoteSelected(Note note) {
        DetailsActivityWithDynamicFragments.start(this, note);
    }
}
