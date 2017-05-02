package com.patrickshim.univtodolist.tasks;

import com.patrickshim.univtodolist.repositories.TasksRepository;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnit;
import org.mockito.junit.MockitoRule;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import io.reactivex.Single;

import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

/**
 * Created by patrickshim on 30/04/2017.
 */

public class TasksActivityPresenterTest {

    @Rule
    public MockitoRule mockitoRule = MockitoJUnit.rule();

    @Mock
    TasksRepository tasksRepository;

    @Mock
    TasksActivityView view;


    private TasksActivityPresenter presenter;

    final List<Task> MANY_BOOKS = Arrays.asList(new Task(), new Task(), new Task());
    final Task ONE_BOOK = new Task();
    final List<Task> EMPTY_LIST = Collections.EMPTY_LIST;

    @Before
    public void setUp() {

        presenter = new TasksActivityPresenter(view, tasksRepository);

    }

    @Test public void shouldPassTasksToView() {

        when(tasksRepository.getTasks()).thenReturn(Single.just(MANY_BOOKS));

        presenter.loadTasks();

        verify(view).displayTasks(MANY_BOOKS);

    }

    @Test public void shouldHandleNoTasks() {

        when(tasksRepository.getTasks()).thenReturn(Single.just(EMPTY_LIST));

        presenter.loadTasks();

        verify(view).displayNoTasks();
    }

    @Test public void shouldHandleError() {

        when(tasksRepository.getTasks()).thenReturn(Single.<List<Task>>error(new Throwable("get task error")));

        presenter.loadTasks();

        verify(view).displayError();

    }

    @Test public void shouldAddTaskToRepo() {

        when(tasksRepository.addTask(ONE_BOOK)).thenReturn(ONE_BOOK);

        presenter.saveTask(ONE_BOOK);

        verify(view).addTask(ONE_BOOK);

    }

    @Test public void shouldHandleAddNoTaskToRepo() {

        when(tasksRepository.addTask(ONE_BOOK)).thenReturn(null);

        presenter.saveTask(ONE_BOOK);

        verify(view).addNoTask();

    }

    @Test public void shouldRemoveTaskFromRepo() {

        when(tasksRepository.removeTask(ONE_BOOK)).thenReturn(ONE_BOOK);

        presenter.deleteTask(ONE_BOOK);

        verify(view).removeTask(ONE_BOOK);

    }

    @Test public void shouldHandleRemoveNoTaskFromRepo() {

        when(tasksRepository.removeTask(ONE_BOOK)).thenReturn(null);

        presenter.deleteTask(ONE_BOOK);

        verify(view).removeNoTask();

    }
}
