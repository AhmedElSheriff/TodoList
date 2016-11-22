package com.example.android.todolist.activities;

import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.android.todolist.R;

/**
 * A placeholder fragment containing a simple view.
 */
public class detailedTodoFragment extends Fragment {

    private TextView detailedText;

    public detailedTodoFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_detailed_todo, container, false);

        detailedText = (TextView) rootView.findViewById(R.id.detailedTodo);

        Intent i = getActivity().getIntent();
        String detailedTodo = i.getStringExtra("detailedTodo");
        detailedText.setText(detailedTodo);
        return rootView;
    }
}
