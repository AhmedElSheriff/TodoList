package com.example.android.todolist.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.todolist.R;

public class addTodoActivity extends AppCompatActivity{

    String date;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_todo);

        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new addTodoActivityFragment()).commit();
        }
    }


}
