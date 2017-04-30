package com.patrickshim.univtodolist.tasks;

import com.patrickshim.univtodolist.repositories.TasksRepository;

import org.junit.Assert;
import org.junit.Test;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by patrickshim on 30/04/2017.
 */
public class TasksActivityPresenterTest {

    @Test
    public void shouldPassTasksToView() {

        // given : initialize condition
        TasksActivityView view = new MockView();
        TasksRepository tasksRepository = new MockTasksRepository(true);

        // when : the action that you want to test
        TasksActivityPresenter presenter = new TasksActivityPresenter(view, tasksRepository);
        presenter.loadTasks();

        // then : check the result. Did it work or not
        Assert.assertEquals(true, ((MockView) view).displayTasksWithTasksCalled);

    }

    @Test
    public void shouldHandleNoTasks() {
        // given
        TasksActivityView view = new MockView();
        TasksRepository tasksRepository = new MockTasksRepository(false);

        // when
        TasksActivityPresenter presenter = new TasksActivityPresenter(view, tasksRepository);
        presenter.loadTasks();

        // then
        Assert.assertEquals(true, ((MockView) view).displayTasksWithNoTasksCalled);
    }

    private class MockView implements TasksActivityView {

        boolean displayTasksWithTasksCalled;
        boolean displayTasksWithNoTasksCalled;


        @Override
        public void displayTasks(List<Task> taskList) {

            if (taskList.size() == 3) displayTasksWithTasksCalled = true;

        }

        @Override
        public void displayNoTasks() {
            displayTasksWithNoTasksCalled = true;
        }

    }

    private class MockTasksRepository implements TasksRepository {

        private boolean returnSomeTasks;

        public MockTasksRepository(boolean returnSomeTasks) {

            this.returnSomeTasks = returnSomeTasks;
        }

        @Override
        public List<Task> getTasks() {

            if (returnSomeTasks) {
                return Arrays.asList(new Task(), new Task(), new Task());
            } else {
                return Collections.emptyList();
            }
        }
    }
}
