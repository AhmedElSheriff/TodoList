package com.example.android.todolist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.view.MenuItem;

import com.example.android.todolist.R;
import com.example.android.todolist.callback.PassData;

public class MainActivity extends AppCompatActivity implements PassData {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        if(savedInstanceState == null)
        {
            getSupportFragmentManager().beginTransaction().add(R.id.container, new MainActivityFragment()).commit();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        if (id == R.id.action_newTodo) {

            startActivity(new Intent(MainActivity.this,addTodoActivity.class));
        }

        if(id == R.id.action_refresh){
            Intent intent = getIntent();
            finish();
            startActivity(intent);
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public void onDataPass(String data) {

        Intent i = new Intent(MainActivity.this,detailedTodo.class);
        i.putExtra("detailedTodo", data);
        startActivity(i);
    }

}
