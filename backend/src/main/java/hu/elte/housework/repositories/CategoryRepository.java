package hu.elte.housework.repositories;

import hu.elte.housework.entities.TaskCategory;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<TaskCategory, Integer> {
}
