package com.crud.tasks.service;

import com.crud.tasks.domain.Task;
import com.crud.tasks.repository.TaskRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;;

import static org.junit.Assert.*;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class DbServiceTest {

    @InjectMocks
    private DbService dbService;

    @Mock
    private TaskRepository taskRepository;

    @Test
    public void testGetAllTasks() {
        //Given
        List<Task> tasks = new ArrayList<>();
        tasks.add(new Task(1l, "Do test", "Testing"));
        tasks.add(new Task(2l, "Do test2", "Testing2"));

        when(dbService.getAllTasks()).thenReturn(tasks);

        //When
        List<Task> allTasks = taskRepository.findAll();

        //Then
        assertNotNull(allTasks);
        assertEquals(2, allTasks.size());
    }

    @Test
    public void testGetTask(){
        //Given
        Task task = new Task(2L, "Test2", "Testing 2");

        when(dbService.getTask(2L)).thenReturn(Optional.of(task));

        //When
        Optional<Task> theTask = taskRepository.findById(2L);

        //Then
        assertNotNull(theTask);
        assertEquals(2, theTask.get().getId(),0);
    }

    @Test
    public void testSaveTask(){
        //Given
        Task task = new Task(3L, "Test3", "Testing 3");

        when(dbService.saveTask(task)).thenReturn(task);

        //When
        Task savedTask = taskRepository.save(task);

        //Then
        assertEquals(3, savedTask.getId(),0);
        assertEquals("Test3", savedTask.getTitle());
    }

    @Test
    public void testDeleteTask(){
        //Given

        //When
        dbService.deleteTask(4l);

        //Then
        verify(taskRepository, times(1)).deleteById(anyLong());
    }
}