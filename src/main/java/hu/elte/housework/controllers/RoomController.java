package hu.elte.housework.controllers;

import hu.elte.housework.entities.Room;
import hu.elte.housework.entities.Task;
import hu.elte.housework.entities.User;
import hu.elte.housework.repositories.RoomRepository;
import hu.elte.housework.repositories.TaskRepository;
import hu.elte.housework.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class RoomController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/rooms")
    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_OWNER"})
    public ResponseEntity<Iterable<Room>> getAll() {
        Iterable<Room> rooms = roomRepository.findAll();
        return ResponseEntity.ok(rooms);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_OWNER"})
    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoom(@PathVariable Integer id) {
        Optional<Room> oRoom = roomRepository.findById(id);
        return oRoom.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Integer id, @RequestBody Room room){
        Optional<Room> oRoom = roomRepository.findById(id);
        if(oRoom.isPresent()){
            room.setId(id);
            return ResponseEntity.ok(roomRepository.save(room));
        }

        return ResponseEntity.notFound().build();
    }


    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("rooms/{id}")
    public ResponseEntity deleteRoom(@PathVariable Integer id) {
        Optional<Room> oRoom = roomRepository.findById(id);
        if (oRoom.isPresent()) {
            roomRepository.delete(oRoom.get());
            return ResponseEntity.ok().build();

        }

        return ResponseEntity.notFound().build();
    }

}
