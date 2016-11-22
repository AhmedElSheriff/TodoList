package com.example.android.todolist.activities;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.android.todolist.R;
import com.example.android.todolist.database.TodoSQLHelper;
import com.example.android.todolist.todo.Todo;

import java.text.SimpleDateFormat;
import java.util.Calendar;

/**
 * A placeholder fragment containing a simple view.
 */
public class addTodoActivityFragment extends Fragment {

    public addTodoActivityFragment() {
    }

    private EditText todoTextView;
    private Button addBtn;
    private Button deleteBtn;
    private TodoSQLHelper db;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View rootView = inflater.inflate(R.layout.fragment_add_todo, container, false);

        todoTextView = (EditText) rootView.findViewById(R.id.addtodoname);
        addBtn = (Button) rootView.findViewById(R.id.addtodobtn);
        deleteBtn = (Button) rootView.findViewById(R.id.deletealldata);

        db = new TodoSQLHelper(getContext());

        Calendar calendar = Calendar.getInstance();
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        final String date = dateFormat.format(calendar.getTime());

        addBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Please Wait");
                progressDialog.show();
                Todo todo = new Todo();

                if(todoTextView.getText().toString() != null) {
                    todo.setDateAndtime(date);
                    todo.setTodoStatus(todoTextView.getText().toString());
                }


                InsertThroughAsyncTask insertThroughAsyncTask = new InsertThroughAsyncTask(todo);
                insertThroughAsyncTask.execute();

                progressDialog.dismiss();
                startActivity(new Intent(getActivity(),MainActivity.class));
            }
        });

        deleteBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                db.deleteall();
            }
        });

        return rootView;
    }

    public class InsertThroughAsyncTask extends AsyncTask<Void,Void,Boolean>
    {

        Todo todo;
        public InsertThroughAsyncTask(Todo todo)
        {
            this.todo = todo;
        }

        @Override
        protected Boolean doInBackground(Void... params) {

            boolean flag;

            if (db.insertToDB(todo)) {

                flag = true;
            }
            else
            {
                flag = false;
            }

            return flag;
        }

        @Override
        protected void onPostExecute(Boolean b) {
            if(b)
            {
                Toast.makeText(getContext(),"A new Todo has been added!",Toast.LENGTH_SHORT).show();
            }
            else
            {
                Toast.makeText(getContext(),"This Todo is already stored",Toast.LENGTH_SHORT).show();
            }
        }
    }

}
