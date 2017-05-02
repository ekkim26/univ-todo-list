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
        try{

            List<Task> taskList = tasksRepository.getTasks();

            if (taskList.isEmpty()) view.displayNoTasks();
            else view.displayTasks(taskList);

        } catch(Exception e) {
            view.displayError();
        }
    }

    public void saveTask(Task task) {
        Task savedTask = tasksRepository.addTask(task);

        if (savedTask != null) {
            view.addTask(task);
        } else {
            view.addNoTask();
        }
    }

    public void deleteTask(Task task) {
        Task removedTask = tasksRepository.removeTask(task);

        if (removedTask != null) {
            view.removeTask(task);
        } else {
            view.removeNoTask();
        }

    }
}
