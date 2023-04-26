package com.example.cutguruapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.CalendarView;

public class MainActivity extends AppCompatActivity {
private CalendarView calendarview;
private View menu;
private View massage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        calendarview=findViewById(R.id.calendarview);
        menu=findViewById(R.id.menu);
        massage=findViewById(R.id.massage);

        menu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MenuActivity.class);
                startActivity(intent);
            }
        });
        massage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, MassageActivity.class);
                startActivity(intent);
            }
        });
    }
}