package com.crud.tasks.mapper;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.assertEquals;

public class TaskMapperTestSuite {
    TaskMapper taskMapper = new TaskMapper();

    @Test
    public void testMappToTask(){
        //Given
        TaskDto taskDto = new TaskDto(1l,"Do test", "testing");

        //When
        Task mappedToTask = taskMapper.mapToTask(taskDto);

        //Then
        assertEquals(1l, mappedToTask.getId(),0);
        assertEquals("Do test", mappedToTask.getTitle());
    }

    @Test
    public void testMappToTaskDto(){
        //Given
        Task task = new Task(2l,"Do test2", "testing");

        //When
        TaskDto mappedToTaskDto = taskMapper.mapToTaskDto(task);

        //Then
        assertEquals(2l, mappedToTaskDto.getId(),0);
        assertEquals("Do test2", mappedToTaskDto.getTitle());
    }

    @Test
    public void testMapToTaskDtoList(){
        //Given
        List<Task> taskList = new ArrayList<>();
        taskList.add(new Task(3l,"Do test3", "testing"));

        //When
        List<TaskDto> mappedToTaskDtoList = taskMapper.mapToTaskDtoList(taskList);

        //Then
        assertEquals(1, mappedToTaskDtoList.size());
        assertEquals(3l, mappedToTaskDtoList.get(0).getId(),0);
    }
}
