package com.patrickshim.univtodolist.repositories.impl;

import android.content.Context;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.patrickshim.univtodolist.DatabaseHelper;
import com.patrickshim.univtodolist.repositories.TasksRepository;
import com.patrickshim.univtodolist.tasks.Task;

import java.sql.SQLException;
import java.util.List;
import java.util.concurrent.Callable;

import io.reactivex.Single;

/**
 * Created by patrickshim on 30/04/2017.
 */

public class DatabaseTasksRepository implements TasksRepository {

    private DatabaseHelper databaseHelper;

    public DatabaseTasksRepository(Context context) {

        databaseHelper = OpenHelperManager.getHelper(context, DatabaseHelper.class);

    }


    @Override
    public Single<List<Task>> getTasks() {

        return Single.fromCallable(new Callable<List<Task>>() {
            @Override
            public List<Task> call() throws Exception {

                try {
                    return databaseHelper.getTaskDao().queryBuilder()
                            .orderBy("createdAt", true)
                            .query();
                } catch (Exception e) {
                    throw new RuntimeException("get task error");
                }
            }
        });

    }

    @Override
    public Task addTask(Task task) {

        try {
            databaseHelper.getTaskDao()
                    .create(task);
            return task;
        } catch (SQLException e) {
            return null;
        }
    }

    @Override
    public Task removeTask(Task task) {

        try {
            databaseHelper.getTaskDao()
                    .delete(task);
            return task;
        } catch(SQLException e) {
            return null;
        }
    }
}