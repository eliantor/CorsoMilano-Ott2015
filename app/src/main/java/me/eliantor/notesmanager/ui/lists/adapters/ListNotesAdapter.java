package me.eliantor.notesmanager.ui.lists.adapters;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import me.eliantor.notesmanager.R;
import me.eliantor.notesmanager.model.Note;

/**
 * Created by aktor on 21/10/15.
 */
class ListNotesAdapter extends BaseAdapter {

    private List<Note> mNotes;

    ListNotesAdapter(){
        mNotes = new ArrayList<>();
    }

    @Override
    public int getCount() {
        return mNotes.size();
    }

    @Override
    public Note getItem(int position) {
        return mNotes.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    private static class ViewHolder {
        private TextView mTitle;
        private TextView mDate;
        private View mItemView;

        private ViewHolder(View view){
            mItemView = view;
            mTitle = (TextView)view.findViewById(R.id.note_title);
            mDate = (TextView)view.findViewById(R.id.note_date);
        }

        public void bind(Note note){
            mTitle.setText(note.getTitle());
            mDate.setText(note.getFormattedDate(mItemView.getContext()));
        }
    }
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder;
        if (convertView == null) {
            LayoutInflater inflater = LayoutInflater.from(parent.getContext());
            convertView = inflater.inflate(R.layout.item_note, parent, false);
            holder = new ViewHolder(convertView);
            convertView.setTag(holder);
        } else {
            holder = (ViewHolder)convertView.getTag();
        }
        holder.bind(getItem(position));
//        bindNote(getItem(position),convertView);

        return convertView;
    }

    private void bindNote(Note note,View itemView){
        TextView title = (TextView)itemView.findViewById(R.id.note_title);
        TextView date = (TextView)itemView.findViewById(R.id.note_date);
        title.setText(note.getTitle());
        date.setText(note.getFormattedDate(itemView.getContext()));
    }

    public void addAll(List<Note> allNotes) {
        mNotes.clear();
        mNotes.addAll(allNotes);
        notifyDataSetChanged();
    }

}
