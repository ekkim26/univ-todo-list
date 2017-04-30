package com.patrickshim.univtodolist.tasks;

import android.content.Context;
import android.support.annotation.LayoutRes;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import com.patrickshim.univtodolist.R;

import java.util.List;

/**
 * Created by patrickshim on 27/04/2017.
 */

public class TaskAdapter extends ArrayAdapter<Task> {

    public TaskAdapter(@NonNull Context context, @LayoutRes int resource, @NonNull List<Task> objects) {
        super(context, resource, objects);
    }

    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        View v = convertView;

        if (v == null) {
            LayoutInflater vi = (LayoutInflater)getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            v = vi.inflate(R.layout.task_row, null);
        }

        Task task = getItem(position);
        if (task != null) {

            TextView taskText = (TextView)v.findViewById(R.id.taskText);
            if (taskText != null) {
                taskText.setText(task.getText());
            }

            TextView taskDate = (TextView)v.findViewById(R.id.taskDate);
            if (taskDate != null) {
                taskDate.setText(task.getDate().toString());
            }

        }

        return v;

    }
}
