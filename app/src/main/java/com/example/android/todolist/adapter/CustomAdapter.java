package com.example.android.todolist.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.example.android.todolist.R;
import com.example.android.todolist.todo.Todo;

import java.util.ArrayList;

/**
 * Created by Abshafi on 11/22/2016.
 */
public class CustomAdapter extends ArrayAdapter<Todo> {

    ArrayList<Todo> arrayList;
    public CustomAdapter(Context context, int resource, ArrayList<Todo> arrayList) {
        super(context, resource, arrayList);
        this.arrayList = arrayList;
    }




    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        View rootView = convertView;
        if(convertView == null)
        {
            LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            rootView = inflater.inflate(R.layout.todolistsinglerow, null);

            Todo todo = arrayList.get(position);
            if(getCount() != 0)
            {
                TextView idTextView = (TextView) rootView.findViewById(R.id.singlerowdatetxt);
                TextView statusTextView = (TextView) rootView.findViewById(R.id.singlerowstatustxt);


                idTextView.setText(todo.getDateAndtime());
                if(todo.getTodoStatus().length() > 15)
                {
                    String trimmedStatus = todo.getTodoStatus().substring(0,15) + "....";
                    statusTextView.setText(trimmedStatus);
                }
                else {
                    statusTextView.setText(todo.getTodoStatus());
                }
            }
        }
        return rootView;
    }

    @Override
    public Todo getItem(int position) {
        return super.getItem(position);
    }

    @Override
    public int getCount() {
        return arrayList.size();
    }
}
