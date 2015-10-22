package me.eliantor.notesmanager.model;


import android.content.Context;
import android.os.Parcel;
import android.os.Parcelable;
import android.text.format.DateFormat;

import java.util.Date;

/**
 * Created by aktor on 21/10/15.
 */
public class Note implements Parcelable{

    private String mTitle;
    private String mContent;
    private boolean mStarred;
    private Date mCreatedAt;

    public Note(String title,String content,boolean starred,Date created){
        this.mTitle = title;
        this.mContent = content;
        this.mStarred = starred;
        this.mCreatedAt = created;
    }

    public long getTimestamp(){
        return mCreatedAt.getTime();
    }

    public boolean isStarred(){
        return mStarred;
    }

    public Date getCreatedAt(){
        return mCreatedAt;
    }

    public String getTitle() {
        return mTitle;
    }


    public String getContent() {
        return mContent;
    }

    public String getFormattedDate(Context context){
        return DateFormat.getMediumDateFormat(context).format(mCreatedAt);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Note note = (Note) o;

        if (mStarred != note.mStarred) return false;
        if (!mTitle.equals(note.mTitle)) return false;
        if (!mContent.equals(note.mContent)) return false;
        return mCreatedAt.equals(note.mCreatedAt);

    }

    @Override
    public int hashCode() {
        int result = mTitle.hashCode();
        result = 31 * result + mContent.hashCode();
        result = 31 * result + (mStarred ? 1 : 0);
        result = 31 * result + mCreatedAt.hashCode();
        return result;
    }

    // parcelable
    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mTitle);
        dest.writeString(mContent);
        dest.writeByte((byte) (mStarred ? 1 : 0));
        dest.writeLong(mCreatedAt.getTime());
    }


    protected Note(Parcel in) {
        mTitle = in.readString();
        mContent = in.readString();
        mStarred = in.readByte() != 0;
        mCreatedAt = new Date(in.readLong());
    }

    public static final Creator<Note> CREATOR = new Creator<Note>() {
        @Override
        public Note createFromParcel(Parcel in) {
            return new Note(in);
        }

        @Override
        public Note[] newArray(int size) {
            return new Note[size];
        }
    };

}
