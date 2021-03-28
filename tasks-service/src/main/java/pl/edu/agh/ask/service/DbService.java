package pl.edu.agh.ask.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
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
    private UserRepository userRepository;

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public List<Task> getTasksByUserId(final int userId){
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

    public User saveUser(final User user){
        return  userRepository.save(user);
    }

    public List<User> getUserByKeyClockId(final int keyClockId){
        return userRepository.findUserByKeyClockId(keyClockId);
    }

    public Optional<User> getUserById(final Long id){
        return userRepository.findById(id);
    }



}
