package hu.elte.housework.repositories;

import hu.elte.housework.entities.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Integer> {
    Optional<Task> findByTaskName(String taskName);

    Iterable<Task> findAllByIsCompletedTrue();

    Iterable<Task> findAllByAvailableTrue();

}