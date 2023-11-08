package ru.gb.notebook;


import android.os.Parcel;
import android.os.Parcelable;

import androidx.annotation.NonNull;

import java.io.Serializable;

public class ListItemSecondActivity implements Parcelable, Serializable {
    private String date;
    private String time;
    private String note;



    public ListItemSecondActivity(String date, String time, String note) {
        this.date = date;
        this.time = time;
        this.note = note;
    }

    public ListItemSecondActivity(Parcel parcel) {
        date=parcel.readString();
        time=parcel.readString();
        note= parcel.readString();
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(@NonNull Parcel parcel, int i) {
        parcel.writeString(date);
        parcel.writeString(time);
        parcel.writeString(note);
    }

    public static final Creator<ListItemSecondActivity> CREATOR = new Creator<ListItemSecondActivity>() {
        @Override
        public ListItemSecondActivity createFromParcel(Parcel parcel) {
            return new ListItemSecondActivity(parcel);
        }

        @Override
        public ListItemSecondActivity[] newArray(int i) {
            return new ListItemSecondActivity[i];
        }
    };


}