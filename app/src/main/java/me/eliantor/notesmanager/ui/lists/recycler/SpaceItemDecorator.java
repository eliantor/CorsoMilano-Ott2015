package me.eliantor.notesmanager.ui.lists.recycler;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by aktor on 21/10/15.
 */
public class SpaceItemDecorator extends RecyclerView.ItemDecoration {

    private final int space;

    public SpaceItemDecorator(int space){
        this.space = space;
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        outRect.left = outRect.right =outRect.bottom = space;
        if (parent.getChildAdapterPosition(view)==0){
            outRect.top = space;
        }
    }
}
