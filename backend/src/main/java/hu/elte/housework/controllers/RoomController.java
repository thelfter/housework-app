package hu.elte.housework.controllers;

import hu.elte.housework.entities.Room;
import hu.elte.housework.entities.User;
import hu.elte.housework.repositories.RoomRepository;
import hu.elte.housework.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class RoomController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private RoomRepository roomRepository;

    @GetMapping("/rooms")
    public ResponseEntity<Iterable<Room>> getAll() {
        Iterable<Room> rooms = roomRepository.findAll();
        return ResponseEntity.ok(rooms);
    }

    @GetMapping("/rooms/{id}")
    public ResponseEntity<Room> getRoom(@PathVariable Integer id) {
        Optional<Room> oRoom = roomRepository.findById(id);
        return oRoom.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Integer id, @RequestBody Room room) {
        Optional<Room> oRoom = roomRepository.findById(id);
        if (oRoom.isPresent()) {
            room.setId(id);
            return ResponseEntity.ok(roomRepository.save(room));
        }

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/rooms/{id}")
    public ResponseEntity<Room> updateRoom(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        Optional<Room> oRoom = roomRepository.findById(id);
        if (oRoom.isPresent()) {
            Room room = oRoom.get();

            for (Map.Entry<String, Object> entry : updates.entrySet()) {
                String key = entry.getKey();
                switch (key) {
                    case "name":
                        room.setName(entry.getValue().toString());
                        break;
                    case "owner":
                        Optional<User> oUser = userRepository.findByUsername(entry.getValue().toString());
                        if (oUser.isPresent()) {
                            User user = oUser.get();
                            room.setOwner(user);
                        } else {
                            return ResponseEntity.notFound().build();
                        }
                        break;
                }
            }
            return ResponseEntity.ok(roomRepository.save(room));
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/rooms/{id}")
    public ResponseEntity deleteRoom(@PathVariable Integer id) {
        Optional<Room> oRoom = roomRepository.findById(id);
        if (oRoom.isPresent()) {
            roomRepository.delete(oRoom.get());
            return ResponseEntity.ok().build();

        }

        return ResponseEntity.notFound().build();
    }
    
}
