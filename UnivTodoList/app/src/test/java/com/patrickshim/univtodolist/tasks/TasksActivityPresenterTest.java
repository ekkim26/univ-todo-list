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
        TasksRepository tasksRepository = new MockTasksRepository(true, true, true);

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
        TasksRepository tasksRepository = new MockTasksRepository(false, true, true);

        // when
        TasksActivityPresenter presenter = new TasksActivityPresenter(view, tasksRepository);
        presenter.loadTasks();

        // then
        Assert.assertEquals(true, ((MockView) view).displayTasksWithNoTasksCalled);
    }

    @Test
    public void shouldAddTaskToRepo() {

        TasksActivityView view = new MockView();
        TasksRepository tasksRepository = new MockTasksRepository(true, true, true);

        TasksActivityPresenter presenter = new TasksActivityPresenter(view, tasksRepository);
        presenter.saveTask(new Task());

        Assert.assertEquals(true, ((MockView)view).addTaskCalled);

    }

    @Test
    public void shouldHandleAddNoTaskToRepo() {

        TasksActivityView view = new MockView();
        TasksRepository tasksRepository = new MockTasksRepository(true, false, true);

        TasksActivityPresenter presenter = new TasksActivityPresenter(view, tasksRepository);
        presenter.saveTask(new Task());

        Assert.assertEquals(true, ((MockView)view).addNoTaskCalled);

    }

    @Test
    public void shouldRemoveTaskFromRepo() {

        TasksActivityView view = new MockView();
        TasksRepository tasksRepository = new MockTasksRepository(true, true, true);

        TasksActivityPresenter presenter = new TasksActivityPresenter(view, tasksRepository);
        presenter.deleteTask(new Task());

        Assert.assertEquals(true, ((MockView)view).removeTaskCalled);

    }

    @Test
    public void shouldHandleRemoveNoTaskFromRepo() {

        TasksActivityView view = new MockView();
        TasksRepository tasksRepository = new MockTasksRepository(true, true, false);

        TasksActivityPresenter presenter = new TasksActivityPresenter(view, tasksRepository);
        presenter.deleteTask(new Task());

        Assert.assertEquals(true, ((MockView)view).removeNoTaskCalled);

    }

    private class MockView implements TasksActivityView {

        boolean displayTasksWithTasksCalled;
        boolean displayTasksWithNoTasksCalled;
        boolean addTaskCalled;
        boolean addNoTaskCalled;
        boolean removeTaskCalled;
        boolean removeNoTaskCalled;

        @Override
        public void displayTasks(List<Task> taskList) {

            if (taskList.size() == 3) displayTasksWithTasksCalled = true;

        }

        @Override
        public void displayNoTasks() {
            displayTasksWithNoTasksCalled = true;
        }


        @Override
        public void addTask(Task task) {
            addTaskCalled = true;
        }

        @Override
        public void addNoTask() {
            addNoTaskCalled = true;
        }

        @Override
        public void removeTask(Task task) {
            removeTaskCalled = true;
        }

        @Override
        public void removeNoTask() {
            removeNoTaskCalled = true;
        }

    }

    private class MockTasksRepository implements TasksRepository {

        private boolean returnSomeTasks;
        private boolean returnSavedTask;
        private boolean returnRemovedTask;

        public MockTasksRepository(boolean returnSomeTasks, boolean returnSavedTask, boolean returnRemovedTask) {

            this.returnSomeTasks = returnSomeTasks;
            this.returnSavedTask = returnSavedTask;
            this.returnRemovedTask = returnRemovedTask;
        }

        @Override
        public List<Task> getTasks() {

            if (returnSomeTasks) {
                return Arrays.asList(new Task(), new Task(), new Task());
            } else {
                return Collections.emptyList();
            }
        }

        @Override
        public Task addTask(Task task) {

            if (returnSavedTask) {
                return task;
            } else {
                return null;
            }
        }

        @Override
        public Task removeTask(Task task) {

            if (returnRemovedTask) {
                return task;
            } else {
                return null;
            }
        }
    }
}
