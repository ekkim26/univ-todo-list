package com.patrickshim.univtodolist.tasks;

import com.patrickshim.univtodolist.repositories.TasksRepository;

import java.util.List;

/**
 * Created by patrickshim on 30/04/2017.
 */

class TasksActivityPresenter {

    private TasksActivityView view;
    private TasksRepository tasksRepository;

    public TasksActivityPresenter(TasksActivityView view, TasksRepository tasksRepository) {
        this.view = view;
        this.tasksRepository = tasksRepository;
    }

    public void loadTasks() {
        List<Task> taskList = tasksRepository.getTasks();

        if (taskList.isEmpty()) view.displayNoTasks();
        else view.displayTasks(taskList);
    }
}
