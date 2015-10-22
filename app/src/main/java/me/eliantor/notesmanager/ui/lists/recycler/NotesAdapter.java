package me.eliantor.notesmanager.ui.lists.recycler;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.eliantor.notesmanager.R;
import me.eliantor.notesmanager.model.Note;

/**
 * Created by aktor on 21/10/15.
 */
class NotesAdapter extends RecyclerView.Adapter<NotesAdapter.NotesViewHolder> {

    private final List<Note> mNotes;
    private final OnNoteSelectedListener mListener;

    NotesAdapter(OnNoteSelectedListener noteSelectedListener){
        mNotes = new ArrayList<>();
        mListener = noteSelectedListener;
    }



    @Override
    public NotesViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        return new NotesViewHolder(LayoutInflater.from(parent.getContext()).inflate(R.layout.item_note,parent,false));
    }

    @Override
    public void onBindViewHolder(NotesViewHolder holder, int position) {
        holder.bind(mNotes.get(position));
    }

    @Override
    public int getItemCount() {
        return mNotes.size();
    }

    public void addAll(List<Note> notes) {
        mNotes.clear();
        mNotes.addAll(notes);
        notifyDataSetChanged();
    }


    class NotesViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView mTitle;
        private TextView mDate;

        private Note boundedNote;

        public NotesViewHolder(View itemView) {
            super(itemView);
            mTitle = (TextView)itemView.findViewById(R.id.note_title);
            mDate = (TextView)itemView.findViewById(R.id.note_date);
            itemView.setOnClickListener(this);
        }

        void bind(Note note){
            boundedNote = note;
            if (boundedNote != null) {
                mTitle.setText(boundedNote.getTitle());
                mDate.setText(boundedNote.getFormattedDate(mDate.getContext()));
            }
        }

        @Override
        public void onClick(View v) {
            if (boundedNote != null){
                mListener.onNoteSelected(boundedNote);
            }
        }
    }
}
