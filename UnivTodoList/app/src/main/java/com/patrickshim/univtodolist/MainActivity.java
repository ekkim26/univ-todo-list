package com.patrickshim.univtodolist;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.Date;

public class MainActivity extends AppCompatActivity {

    ListView listview;
    Button addTodo;
    EditText todoEditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        listview = (ListView)findViewById(R.id.todoListView);
        todoEditText = (EditText)findViewById(R.id.todoEditText);

        final ArrayList<Task> taskList = new ArrayList<>();
        taskList.add(new Task("할 일 1", new Date()));
        taskList.add(new Task("할 일 2", new Date()));
        taskList.add(new Task("할 일 3", new Date()));

        final TaskAdapter taskAdapter = new TaskAdapter(this, android.R.layout.simple_list_item_1, taskList);
        listview.setAdapter(taskAdapter);

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {

                taskList.remove(position);
                taskAdapter.notifyDataSetChanged();

                return false;
            }
        });


        addTodo = (Button)findViewById(R.id.addTodo);
        addTodo.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String task = todoEditText.getText().toString();
                if (task.length() > 0) {
                    taskList.add(new Task(task, new Date()));
                    taskAdapter.notifyDataSetChanged();
                    todoEditText.setText("");
                }
            }
        });

    }
}
