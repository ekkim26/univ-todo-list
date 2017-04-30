package com.patrickshim.univtodolist.tasks;

import java.util.List;

/**
 * Created by patrickshim on 30/04/2017.
 */

public interface TasksActivityView {

    void displayTasks(List<Task> taskList);

    void displayNoTasks();

}
