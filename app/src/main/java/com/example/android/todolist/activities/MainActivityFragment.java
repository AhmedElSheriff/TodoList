package com.example.android.todolist.activities;

import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ListView;

import com.example.android.todolist.R;
import com.example.android.todolist.adapter.CustomAdapter;
import com.example.android.todolist.callback.PassData;
import com.example.android.todolist.database.TodoSQLHelper;
import com.example.android.todolist.todo.Todo;

import java.util.ArrayList;

/**
 * A placeholder fragment containing a simple view.
 */
public class MainActivityFragment extends Fragment {

    public MainActivityFragment() {
    }

    private PassData dataPasser;

    private ArrayList<Todo> arrayList;
    private TodoSQLHelper db;
    private ListView listView;
    private CustomAdapter adapter;
    private Todo todo;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_main, container, false);

        db = new TodoSQLHelper(getContext());
        listView = (ListView) rootView.findViewById(R.id.mainlistview);
        arrayList = new ArrayList<>();
        arrayList = db.getAllData();
        adapter = new CustomAdapter(getContext(),R.layout.todolistsinglerow,arrayList);
        listView.setAdapter(adapter);
        listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                todo = arrayList.get(position);
                String detailedTodo = todo.getTodoStatus();
                passData(detailedTodo);
            }
        });

       registerForContextMenu(listView);

        return rootView;
    }

    public void passData (String data)
    {
        dataPasser.onDataPass(data);
    }


    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        dataPasser = (PassData) context;
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        if(v.getId() == R.id.mainlistview)
        {
            MenuInflater inflater = getActivity().getMenuInflater();
            inflater.inflate(R.menu.menu_long_option, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(MenuItem item) {

        TodoSQLHelper db = new TodoSQLHelper(getContext());
        AdapterView.AdapterContextMenuInfo info = (AdapterView.AdapterContextMenuInfo) item.getMenuInfo();
        int position = info.position;
        switch (item.getItemId())
        {
            case R.id.action_delete:
                Todo todo = arrayList.get(position);
                db.deleteRow(todo.getDateAndtime());
                refresh();
                return true;
            default:
                return super.onContextItemSelected(item);
        }



    }

    private void refresh()
    {
        Intent intent = getActivity().getIntent();
        getActivity().finish();
        startActivity(intent);
    }


}
