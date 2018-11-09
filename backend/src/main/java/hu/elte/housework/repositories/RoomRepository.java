package hu.elte.housework.repositories;

import hu.elte.housework.entities.Room;
import org.springframework.data.repository.CrudRepository;

public interface RoomRepository extends CrudRepository<Room, Integer> {
}
