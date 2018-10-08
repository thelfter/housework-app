package hu.elte.housework.repositories;

import hu.elte.housework.entities.User;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface UserRepository extends CrudRepository<User, Integer> {
    public Optional<User> findByUsername(String username);
}
