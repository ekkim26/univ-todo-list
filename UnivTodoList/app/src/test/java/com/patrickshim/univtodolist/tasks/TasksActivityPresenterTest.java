package com.patrickshim.univtodolist.tasks;

import com.patrickshim.univtodolist.repositories.TasksRepository;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Created by patrickshim on 30/04/2017.
 */

@RunWith(MockitoJUnitRunner.class)
public class TasksActivityPresenterTest {

    @Mock
    TasksRepository tasksRepository;

    @Mock
    TasksActivityView view;


    private TasksActivityPresenter presenter;

    final List<Task> MANY_BOOKS = Arrays.asList(new Task(), new Task(), new Task());
    final Task ONE_BOOK = new Task();

    @Before
    public void setUp() {

        presenter = new TasksActivityPresenter(view, tasksRepository);

    }

    @Test
    public void shouldPassTasksToView() {

        Mockito.when(tasksRepository.getTasks()).thenReturn(MANY_BOOKS);

        presenter.loadTasks();

        Mockito.verify(view).displayTasks(MANY_BOOKS);

    }

    @Test
    public void shouldHandleNoTasks() {

        Mockito.when(tasksRepository.getTasks()).thenReturn(Collections.EMPTY_LIST);

        presenter.loadTasks();

        Mockito.verify(view).displayNoTasks();
    }

    @Test
    public void shouldAddTaskToRepo() {

        Mockito.when(tasksRepository.addTask(ONE_BOOK)).thenReturn(ONE_BOOK);

        presenter.saveTask(ONE_BOOK);

        Mockito.verify(view).addTask(ONE_BOOK);

    }

    @Test
    public void shouldHandleAddNoTaskToRepo() {

        Mockito.when(tasksRepository.addTask(ONE_BOOK)).thenReturn(null);

        presenter.saveTask(ONE_BOOK);


        Mockito.verify(view).addNoTask();

    }

    @Test
    public void shouldRemoveTaskFromRepo() {

        Mockito.when(tasksRepository.removeTask(ONE_BOOK)).thenReturn(ONE_BOOK);

        presenter.deleteTask(ONE_BOOK);

        Mockito.verify(view).removeTask(ONE_BOOK);

    }

    @Test
    public void shouldHandleRemoveNoTaskFromRepo() {

        Mockito.when(tasksRepository.removeTask(ONE_BOOK)).thenReturn(null);

        presenter.deleteTask(ONE_BOOK);

        Mockito.verify(view).removeNoTask();

    }
}
