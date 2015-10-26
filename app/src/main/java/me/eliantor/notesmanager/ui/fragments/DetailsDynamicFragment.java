package me.eliantor.notesmanager.ui.fragments;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import me.eliantor.notesmanager.R;
import me.eliantor.notesmanager.model.Note;

;

/**
 * Created by aktor on 23/10/15.
 */
public class DetailsDynamicFragment extends Fragment {

    public static final String TAG = "DetailsDynamicFragment";
    private static final String ARG = "arg";
    private TextView title;
    private TextView body;
    private MeCallable mListener;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_details,container,false);

        title =(TextView)view.findViewById(R.id.note_title);
        title.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                tryToCallMe();
            }
        });
        body =(TextView)view.findViewById(R.id.note_body);

        setDataFromArguments();

        return view;
    }

    public void setListener(MeCallable callable){
        mListener = callable;
    }


    private void setDataFromArguments(){
        Bundle arguments = getArguments();
        Note note =arguments.getParcelable(ARG);
        setNote(note);
    }

    public void setNote(Note note) {
        title.setText(note.getTitle());
        body.setText(note.getContent());
    }

    private void tryToCallMe(){
        if (mListener != null){
            mListener.callMe();
        }
//        FragmentActivity activity = getActivity();
//        MeCallable  f = (MeCallable)activity;
//        f.callMe();
    }

    public static DetailsDynamicFragment create(Note note) {
        Bundle args = new Bundle();
        args.putParcelable(ARG, note);
        DetailsDynamicFragment f = new DetailsDynamicFragment();
        f.setArguments(args);
        return f;
    }
}
