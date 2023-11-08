package ru.gb.notebook;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    Button button;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        init();
    }

    private void init(){
        button=findViewById(R.id.button);
        button.setOnClickListener(this:: onClick);
    }

    private void onClick(View view){
        Intent intent =new Intent(this,SecondActivity.class);
        startActivity(intent);
    }
}