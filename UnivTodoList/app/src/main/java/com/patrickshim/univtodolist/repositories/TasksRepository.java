package com.patrickshim.univtodolist.repositories;

import com.patrickshim.univtodolist.tasks.Task;

import java.util.List;

/**
 * Created by patrickshim on 30/04/2017.
 */

public interface TasksRepository {

    List<Task> getTasks();

    Task addTask(Task task);

    Task removeTask(Task task);
}
