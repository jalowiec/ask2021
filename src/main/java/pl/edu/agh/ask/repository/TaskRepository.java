package pl.edu.agh.ask.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.agh.ask.domain.Task;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Long> {
    @Override
    List<Task> findAll();

    List<Task> findTasksByUserId(String userId);

    @Override
    Task save(Task task);

    Optional<Task> findById(Long id);

    @Override
    long count();
}
