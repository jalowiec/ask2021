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
    private TaskRepository repository;

    public List<Task> getAllTasks(){
        return repository.findAll();
    }

    public List<Task> getTasksByUserId(final int userId){
        return repository.findTasksByUserId(userId);
    }

    public Optional<Task> getTaskById(final Long id){
        return repository.findById(id);
    }

    public Task saveTask(final Task task){
        return  repository.save(task);
    }

    public void deleteTask(final Long id){repository.deleteById(id);}

}
