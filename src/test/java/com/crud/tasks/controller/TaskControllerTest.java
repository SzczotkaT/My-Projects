package com.crud.tasks.controller;

import com.crud.tasks.domain.Task;
import com.crud.tasks.domain.TaskDto;
import com.crud.tasks.mapper.TaskMapper;
import com.crud.tasks.service.DbService;

import com.google.gson.Gson;
import org.junit.Assert;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.web.util.NestedServletException;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


import static org.hamcrest.Matchers.any;
import static org.hamcrest.Matchers.empty;
import static org.hamcrest.Matchers.is;
import static org.hamcrest.collection.IsCollectionWithSize.hasSize;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

@RunWith(SpringRunner.class)
@WebMvcTest(TaskController.class)
public class TaskControllerTest {
    @Rule
    public final ExpectedException exception = ExpectedException.none();

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private DbService service;

    @MockBean
    private TaskMapper taskMapper;

    @Test
    public void shouldGetEmptyTaskList() throws Exception{
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();

        when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().is(200))
                .andExpect(jsonPath("$", hasSize(0)));
    }

    @Test
    public void shouldGetTaskList() throws Exception{
        //Given
        List<TaskDto> taskDtoList = new ArrayList<>();
        taskDtoList.add(new TaskDto(1L, "Task test", "test"));
        taskDtoList.add(new TaskDto(2L, "Task test2", "test2"));

        when(taskMapper.mapToTaskDtoList(service.getAllTasks())).thenReturn(taskDtoList);

        //When & Then
        mockMvc.perform(get("/v1/tasks").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(2)))
                .andExpect(jsonPath("$[0].id",is(1)))
                .andExpect(jsonPath("$[0].title", is("Task test")))
                .andExpect(jsonPath("$[1].id", is(2)))
                .andExpect(jsonPath("$[1].title", is("Task test2")));
    }

    @Test
    public void shouldGetTask() throws Exception {
        //Given
        Task task = new Task(1L, "Task test", "test");
        TaskDto taskDto = new TaskDto(1L, "Task test1", "Test1");
        Optional<Task> optionalTask = Optional.ofNullable(task);

        when(service.getTask(1L)).thenReturn(optionalTask);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(get("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.id", is(1)))
                .andExpect(jsonPath("$.title", is("Task test1")));
    }

    @Test
    public void shouldDeleteTask() throws Exception{
        //Given
        Task task = new Task(2L, "Task test", "test");
        TaskDto taskDto = new TaskDto(2L, "Task test2", "Test2");
        Optional<Task> optionalTask = Optional.ofNullable(task);

        when(service.getTask(2L)).thenReturn(optionalTask);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        //When & Then
        mockMvc.perform(delete("/v1/tasks/2").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
        verify(service, times(1)).deleteTask(anyLong());
    }

    @Test
    public void shouldThrowTaskNotFoundException() throws Exception {
        //Given

        //When & Then
        exception.expect(NestedServletException.class);
        mockMvc.perform(delete("/v1/tasks/1").contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    @Test
    public void shouldUpdateTask() throws Exception{
        //Given
        Task task = new Task(5L, "Test task", "Testing task");
        TaskDto taskDto = new TaskDto(5L, "Test 5", "Testing 5");

        when(service.saveTask(taskMapper.mapToTask(taskDto))).thenReturn(task);
        when(taskMapper.mapToTaskDto(task)).thenReturn(taskDto);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        //When & Then
        mockMvc.perform(put("/v1/tasks").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(jsonPath("$.id", is(5)))
                .andExpect(jsonPath("$.title", is("Test 5")))
                .andExpect(jsonPath("$.content", is("Testing 5")));
    }

    @Test
    public void shouldCreateTask() throws Exception{
        //Given
        Task task = new Task(6L, "test task 6", "testing 6");
        TaskDto taskDto = new TaskDto(6L, "Test task 6", "Testing 6");

        when(service.saveTask(taskMapper.mapToTask(taskDto))).thenReturn(task);

        Gson gson = new Gson();
        String jsonContent = gson.toJson(task);

        //When & then
        mockMvc.perform(post("/v1/tasks").contentType(MediaType.APPLICATION_JSON)
                .characterEncoding("UTF-8")
                .content(jsonContent))
                .andExpect(status().isOk());
        verify(service, times(1)).saveTask(anyObject());
    }
}