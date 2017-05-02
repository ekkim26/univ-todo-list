package com.patrickshim.univtodolist.repositories;

import com.patrickshim.univtodolist.tasks.Task;

import java.util.List;

import io.reactivex.Single;

/**
 * Created by patrickshim on 30/04/2017.
 */

public interface TasksRepository {
    
    Single<List<Task>> getTasks();

    Task addTask(Task task);

    Task removeTask(Task task);
}
