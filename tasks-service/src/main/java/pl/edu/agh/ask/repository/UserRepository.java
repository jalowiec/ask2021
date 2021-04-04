package pl.edu.agh.ask.repository;

import org.springframework.data.repository.CrudRepository;
import pl.edu.agh.ask.domain.Task;
import pl.edu.agh.ask.domain.User;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Long> {

    @Override
    List<User> findAll();

    List<User> findUserByKeyCloakUserName(String keyCloakUserName);

    @Override
    User save(User user);

    Optional<User> findById(Long id);

    @Override
    long count();



}
