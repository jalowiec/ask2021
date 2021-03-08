package pl.edu.agh.ask.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;

import org.springframework.web.bind.annotation.*;
import pl.edu.agh.ask.domain.TaskDto;
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

    private List<TaskDto> getTasksByUserId(int userId){
        return taskMapper.mapToTaskDtoList(service.getTasksByUserId(userId));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<TaskDto> getAllTasks(){
        return taskMapper.mapToTaskDtoList(service.getAllTasks());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tasks", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto){
        taskDto.setUserId(1);
        service.saveTask(taskMapper.mapToTask(taskDto));
    }

/*
    @RequestMapping(method = RequestMethod.GET, value = "/tasks", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<TaskDto> getTasks(@AuthenticationPrincipal Jwt jwt){
        return getTasksByUserId(getUserIdFromKeycloakUser(jwt.getClaims().get("preferred_username").toString()));
    }

    @RequestMapping(method = RequestMethod.GET, value = "/tasks/{taskId}", produces= MediaType.APPLICATION_JSON_VALUE)
    public TaskDto getTask(@PathVariable Long taskId, @AuthenticationPrincipal Jwt jwt) throws TaskNotFoundException
    {
        Optional<Task> task = service.getTaskById(taskId);
        if(task.get().getUserId() == getUserIdFromKeycloakUser(jwt.getClaims().get("preferred_username").toString())){
            return taskMapper.mapToTaskDto(service.getTaskById(taskId).orElseThrow(TaskNotFoundException::new));
        }
        else {
            throw new TaskNotFoundException();
        }
    }

    @RequestMapping(method = RequestMethod.POST, value = "/tasks", consumes = APPLICATION_JSON_VALUE)
    public void createTask(@RequestBody TaskDto taskDto, @AuthenticationPrincipal Jwt jwt){
        taskDto.setUserId(getUserIdFromKeycloakUser(jwt.getClaims().get("preferred_username").toString()));
        service.saveTask(taskMapper.mapToTask(taskDto));
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/tasks/{taskId}")
    public void deleteTask(@PathVariable Long taskId, @AuthenticationPrincipal Jwt jwt) throws TaskNotFoundException {
        Optional<Task> task = service.getTaskById(taskId);
        if(task.get().getUserId() == getUserIdFromKeycloakUser(jwt.getClaims().get("preferred_username").toString())) {
            service.deleteTask(taskId);
        } else {
            throw new TaskNotFoundException();
        }
    }


    @RequestMapping(method = RequestMethod.PUT, value = "/tasks")
    public TaskDto updateTask(@RequestBody TaskDto taskDto, @AuthenticationPrincipal Jwt jwt) throws TaskNotFoundException {
        Long taskId = taskDto.getId();
        Optional<Task> task = service.getTaskById(taskId);
        if(task.isPresent()){
            if(task.get().getUserId() == getUserIdFromKeycloakUser(jwt.getClaims().get("preferred_username").toString())) {
                taskDto.setUserId(task.get().getUserId());
                return taskMapper.mapToTaskDto(service.saveTask(taskMapper.mapToTask(taskDto)));
            }
        }
        return null;
    }



    private int getUserIdFromKeycloakUser(String keyCloakUser){
        Map<String, Integer> map = new HashMap<>();
        map.put("tai1", 1);
        map.put("tai2", 2);
        map.put("tai3", 3);
        return map.get(keyCloakUser);
    }


 */

}
