package ru.gb.notebook;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

public class AdapterSecond extends ArrayAdapter<ListItemSecondActivity> {

    List<ListItemSecondActivity> listItemSecondActivity;
    LayoutInflater layoutInflater;

    public AdapterSecond(@NonNull Context context, int resource, @NonNull List<ListItemSecondActivity> objects) {
        super(context, resource, objects);
        this.listItemSecondActivity=  objects;
        layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        if(convertView == null){
            convertView= layoutInflater.inflate(R.layout.item_secomd_activity,parent,false);
        }
        ListItemSecondActivity item =listItemSecondActivity.get(position);
        TextView textDate = convertView.findViewById(R.id.date);
        TextView textTime = convertView.findViewById(R.id.time);
        TextView textNote = convertView.findViewById(R.id.note);
        textDate.setText(item.getDate());
        textTime.setText(item.getTime());
        textNote.setText(item.getNote());



        return convertView;
    }
}
