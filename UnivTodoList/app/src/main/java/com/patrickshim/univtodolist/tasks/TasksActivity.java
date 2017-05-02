package com.patrickshim.univtodolist.tasks;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.patrickshim.univtodolist.R;
import com.patrickshim.univtodolist.repositories.impl.DatabaseTasksRepository;

import java.util.Date;
import java.util.List;

public class TasksActivity extends AppCompatActivity implements TasksActivityView {

    private static final String TAG = TasksActivity.class.getSimpleName();

    ListView listview;
    Button addTodo;
    EditText todoEditText;
    TaskAdapter taskAdapter;
    private TasksActivityPresenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tasks);

        listview = (ListView)findViewById(R.id.todoListView);
        todoEditText = (EditText)findViewById(R.id.todoEditText);

        presenter = new TasksActivityPresenter(this, new DatabaseTasksRepository(getApplication()));

        presenter.loadTasks();

        listview.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                presenter.deleteTask((Task)parent.getItemAtPosition(position));
                return false;
            }
        });


        addTodo = (Button)findViewById(R.id.addTodo);
        addTodo.setOnClickListener(new View.OnClickListener() {

            private final int MIN_TASK_LENGTH = 1;

            @Override
            public void onClick(View v) {
                String task = todoEditText.getText().toString();
                if (task.length() >= MIN_TASK_LENGTH) {
                    presenter.saveTask(new Task(task, new Date()));
                } else {
                    Toast.makeText(getApplicationContext(), "할 일을 입력하세요!", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }

    @Override
    public void displayTasks(List<Task> taskList) {
        Log.d(TAG, "displayTasks: found some tasks");
        taskAdapter = new TaskAdapter(this, android.R.layout.simple_list_item_1, taskList);
        listview.setAdapter(taskAdapter);
    }

    @Override
    public void displayNoTasks() {
        Log.d(TAG, "displayNoTasks: found no tasks");
    }

    @Override
    public void displayError() {
        Toast.makeText(this, "Error accessing data", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void addTask(Task task) {
        taskAdapter.add(task);
        taskAdapter.notifyDataSetChanged();
        todoEditText.setText("");
    }

    @Override
    public void addNoTask() {
        Toast.makeText(this, "할 일 추가 도중 에러가 발생했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void removeTask(Task task) {
        taskAdapter.remove(task);
        taskAdapter.notifyDataSetChanged();
    }

    @Override
    public void removeNoTask() {
        Toast.makeText(this, "할 일 삭제 도중 에러가 발생했습니다. 다시 시도해주세요.", Toast.LENGTH_SHORT).show();
    }
}
