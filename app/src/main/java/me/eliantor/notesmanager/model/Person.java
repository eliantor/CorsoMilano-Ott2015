package me.eliantor.notesmanager.model;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * Created by aktor on 23/10/15.
 */
public class Person implements Parcelable{

    private String name;
    private String surname;
    private Note note;

    public Person(String name, String surname,Note note) {
        this.name = name;
        this.surname = surname;
        this.note = note;
    }


    protected Person(Parcel in) {
        name = in.readString();
        surname = in.readString();
        note = in.readParcelable(Note.class.getClassLoader());
    }

    public static final Creator<Person> CREATOR = new Creator<Person>() {
        @Override
        public Person createFromParcel(Parcel in) {
            return new Person(in);
        }

        @Override
        public Person[] newArray(int size) {

            return new Person[size];
        }
    };

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(name);
        dest.writeString(surname);
        dest.writeParcelable(note, flags);
    }
}
