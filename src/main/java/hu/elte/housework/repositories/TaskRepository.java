package hu.elte.housework.repositories;

import hu.elte.housework.entities.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Integer> {
    public Optional<Task> findByTaskName(String taskName);
}
