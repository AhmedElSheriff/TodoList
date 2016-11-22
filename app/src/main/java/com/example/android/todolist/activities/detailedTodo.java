package com.example.android.todolist.activities;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.example.android.todolist.R;

public class detailedTodo extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detailed_todo);
        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().add(R.id.container,new detailedTodoFragment()).commit();
        }
    }



}
