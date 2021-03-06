package pl.edu.agh.ask.service;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pl.edu.agh.ask.domain.Task;
import pl.edu.agh.ask.repository.TaskRepository;

import java.util.List;
import java.util.Optional;

@Service
public class DbService {

    @Autowired
    private TaskRepository taskRepository;

    public List<Task> getAllTasks(){
        return taskRepository.findAll();
    }

    public List<Task> getTasksByUserId(final String userId){
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
}
