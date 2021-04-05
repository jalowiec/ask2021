package pl.edu.agh.ask.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.ask.controller.UserNotFoundException;
import pl.edu.agh.ask.domain.Task;
import pl.edu.agh.ask.domain.User;
import pl.edu.agh.ask.repository.TaskRepository;
import pl.edu.agh.ask.repository.UserRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public List<Task> getTasksByUserId(final long userId){
        return taskRepository.findTasksByUserId(userId);
    }

    public Optional<Task> getTaskById(final Long id){
        return taskRepository.findById(id);
    }

    public Task saveTask(final Task task){
        return  taskRepository.save(task);
    }

    public void deleteTask(final Long id){
        taskRepository.deleteById(id);}


    public List<User> getAllUsers(){
        return userRepository.findAll();
    }

    public User saveUser(final User user){
        return  userRepository.save(user);
    }

    public List<User> getUserByKeyClocUserName(final String keyCloakUserName){
        return userRepository.findUserByKeyCloakUserName(keyCloakUserName);
    }

    public Optional<User> getUserById(final Long id){
        return userRepository.findById(id);
    }

    public long getUserIdByKeyCloakUserName(String keyCloakUserName) throws UserNotFoundException {
        List<User>  users = getUserByKeyClocUserName(keyCloakUserName);
        if(users.size() > 0){
            return users.get(0).getId();
        }
        else throw new UserNotFoundException();
    }


}
