package hu.elte.housework.repositories;

import hu.elte.housework.entities.Task;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface TaskRepository extends CrudRepository<Task, Integer> {
    Optional<Task> findByTaskName(String taskName);

    Optional<Task> findTasksByScoreGreaterThan(Integer number);

    Optional<Task> findTasksByIsCompletedIsFalse();

}
