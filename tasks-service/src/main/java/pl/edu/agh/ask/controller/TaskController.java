package pl.edu.agh.ask.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.web.bind.annotation.*;
import pl.edu.agh.ask.domain.Task;
import pl.edu.agh.ask.domain.TaskDto;
import pl.edu.agh.ask.domain.User;
import pl.edu.agh.ask.domain.UserDto;
import pl.edu.agh.ask.mapper.TaskMapper;
import pl.edu.agh.ask.service.DbService;

import java.util.*;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class TaskController {


    @Autowired
    private DbService service;

    @Autowired
    private TaskMapper taskMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());


    @RequestMapping(method = RequestMethod.GET, value = "/tasks", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<TaskDto> getTasks(@AuthenticationPrincipal Jwt jwt) throws UserNotFoundException {
        String keyCloakUserName = jwt.getClaims().get("preferred_username").toString();
        long userId = service.getUserIdByKeyCloakUserName(keyCloakUserName);
        return taskMapper.mapToTaskDtoList(service.getTasksByUserId(userId));
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tasks", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto, @AuthenticationPrincipal Jwt jwt) throws UserNotFoundException {
        String keyCloakUserName = jwt.getClaims().get("preferred_username").toString();
        long userId = service.getUserIdByKeyCloakUserName(keyCloakUserName);
        taskDto.setUserId(userId);
        service.saveTask(taskMapper.mapToTask(taskDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{taskId}")
    public void deleteTask(@PathVariable Long taskId, @AuthenticationPrincipal Jwt jwt) throws TaskNotFoundException, UserNotFoundException {
        Optional<Task> task = service.getTaskById(taskId);
        String keyCloakUserName = jwt.getClaims().get("preferred_username").toString();
        long userId = service.getUserIdByKeyCloakUserName(keyCloakUserName);
        if(task.get().getUserId() == userId) {
            service.deleteTask(taskId);
        } else {
            throw new TaskNotFoundException();
        }
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/tasks")
    public TaskDto updateTask(@RequestBody TaskDto taskDto, @AuthenticationPrincipal Jwt jwt) throws TaskNotFoundException, UserNotFoundException {
        Long taskId = taskDto.getId();
        Optional<Task> task = service.getTaskById(taskId);
        if(task.isPresent()){
            String keyCloakUserName = jwt.getClaims().get("preferred_username").toString();
            long userId = service.getUserIdByKeyCloakUserName(keyCloakUserName);
            if(task.get().getUserId() == userId) {
                taskDto.setUserId(task.get().getUserId());
                return taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)));
            }
        }
        throw new TaskNotFoundException();
    }

}
