package ru.gb.notebook;

import android.app.Dialog;
import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.MenuItemCompat;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class SecondActivity extends AppCompatActivity {

    ListView listView;
    ArrayList<ListItemSecondActivity> records;
    Button buttonAdd;
    Dialog dialog;
    AdapterSecond adapterSecond;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.second_activity);
        init();
        adapterSecond = new AdapterSecond(this, R.layout.item_secomd_activity, records);
        listView.setAdapter(adapterSecond);
        listView.setOnItemClickListener(this::clickListView);
        registerForContextMenu(listView);
    }

    private void init() {
        listView = findViewById(R.id.line);
        buttonAdd = findViewById(R.id.add_record);
        buttonAdd.setOnClickListener(this::clickAdd);
        FileHandler fileHandler=new FileHandler(this);
        records= (ArrayList<ListItemSecondActivity>) fileHandler.read();
        if(records==null){
            records = new ArrayList<>();
            Log.d("save","Данные не прочитаны");
        }

    }

    private void clickListView(AdapterView adapterView, View view, int position, long id) {
        TextView textView = view.findViewById(R.id.line);
        Toast.makeText(this, textView.getText(), Toast.LENGTH_SHORT).show();
    }

    private void clickAdd(View view) {
        dialog = new Dialog(this);
        dialog.setContentView(R.layout.dialog);
        dialog.show();
        Button buttonOk = dialog.findViewById(R.id.dialog_ok);
        buttonOk.setOnClickListener(v -> clickCreateRecord());
        Button buttonCancel = dialog.findViewById(R.id.dialog_cancel);
        buttonCancel.setOnClickListener(v -> clickCloseDialog());
    }

    private void clickCreateRecord() {
        EditText editTextDate = dialog.findViewById(R.id.dialog_date);
        EditText editTextTime = dialog.findViewById(R.id.dialog_time);
        EditText editTextNote = dialog.findViewById(R.id.dialog_note);
        String date = editTextDate.getText().toString();
        String time = editTextTime.getText().toString();
        String note = editTextNote.getText().toString();
        ListItemSecondActivity item = new ListItemSecondActivity(date, time, note);
        records.add(item);
        clickCloseDialog();
        adapterSecond.notifyDataSetChanged();
    }

    private void clickCloseDialog() {
        dialog.dismiss();
    }

    @Override
    protected void onSaveInstanceState(@NonNull Bundle outState) {
        super.onSaveInstanceState(outState);
        //outState.putParcelableArrayList("info",records);
        String[] dates = new String[records.size()];
        String[] times = new String[records.size()];
        String[] notes = new String[records.size()];
        for (int i = 0; i < records.size(); i++) {
            dates[i] = records.get(i).getDate();
            times[i] = records.get(i).getTime();
            notes[i] = records.get(i).getNote();
        }
        outState.putStringArray("dates", dates);
        outState.putStringArray("times", times);
        outState.putStringArray("notes", notes);
    }

    @Override
    protected void onRestoreInstanceState(@NonNull Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.TIRAMISU) {
            records = savedInstanceState.getParcelableArrayList("info", ListItemSecondActivity.class);
            adapterSecond.notifyDataSetChanged();
        }
        String[] dates = savedInstanceState.getStringArray("dates");
        String[] times = savedInstanceState.getStringArray("times");
        String[] notes = savedInstanceState.getStringArray("notes");
        for (int d = 0; d < dates.length; d++) {
            ListItemSecondActivity itemSecondActivity = new ListItemSecondActivity(dates[d], times[d], notes[d]);
            records.add(itemSecondActivity);
        }
        adapterSecond.notifyDataSetChanged();
    }

    @Override
    protected void onStop() {
        super.onStop();
        FileHandler fileHandler= new FileHandler(this);
        fileHandler.save(records);
        Log.d("save","Данные сохраненны" );
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);
        MenuInflater menuInflater = getMenuInflater();
        menuInflater.inflate(R.menu.context_menu,menu);
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        if(item.getItemId()==R.id.menu_edit){

        }else if(item.getItemId()==R.id.menu_delete){
            menuDelete(item);
        }
        return true;
    }

    private void menuDelete(MenuItem item){
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int index = info.position;
        records.remove(index);
        adapterSecond.notifyDataSetChanged();
    }
}
