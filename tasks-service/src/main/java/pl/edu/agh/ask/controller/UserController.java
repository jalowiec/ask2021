package pl.edu.agh.ask.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import pl.edu.agh.ask.domain.TaskDto;
import pl.edu.agh.ask.domain.User;
import pl.edu.agh.ask.domain.UserDto;
import pl.edu.agh.ask.mapper.TaskMapper;
import pl.edu.agh.ask.mapper.UserMapper;
import pl.edu.agh.ask.service.DbService;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
public class UserController {


    @Autowired
    private DbService service;

    @Autowired
    private UserMapper userMapper;

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    private Optional<User> getUserByUserId(Long userId){
        return service.getUserById(userId);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/users", produces= MediaType.APPLICATION_JSON_VALUE)
    public List<UserDto> getAllUsers(){
        return userMapper.mapToUserDtoList(service.getAllUsers());
    }

    @RequestMapping(method = RequestMethod.POST, value = "/users", consumes = APPLICATION_JSON_VALUE)
    public void createUser(@RequestBody UserDto userDto){
        service.saveUser(userMapper.mapToUser(userDto));
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
