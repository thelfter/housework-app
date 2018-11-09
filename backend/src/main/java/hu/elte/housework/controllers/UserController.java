package hu.elte.housework.controllers;

import hu.elte.housework.entities.Room;
import hu.elte.housework.entities.Task;
import hu.elte.housework.entities.User;
import hu.elte.housework.repositories.RoomRepository;
import hu.elte.housework.repositories.TaskRepository;
import hu.elte.housework.repositories.UserRepository;
import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private RoomRepository roomRepository;

    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<User> postUser(@RequestBody User user) {
        Optional<User> oUser = userRepository.findByUsername(user.getUsername());
        if (oUser.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        user.setId(null);
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        user.setRole(User.Role.ROLE_USER);
        return ResponseEntity.ok(userRepository.save(user));
    }

    @GetMapping("/users")
    public ResponseEntity<Iterable<User>> getAll() {
        Iterable<User> users = userRepository.findAll();
        return ResponseEntity.ok(users);
    }

    @GetMapping("/users/{id}")
    public ResponseEntity<User> getUser(@PathVariable Integer id) {
        Optional<User> oUser = userRepository.findById(id);
        return oUser.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody User user){
        Optional<User> oUser = userRepository.findById(id);
        if(oUser.isPresent()){
            user.setId(id);
            return ResponseEntity.ok(userRepository.save(user));
        }

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/users/{id}")
    public ResponseEntity<User> updateUser(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        Optional<User> oUser = userRepository.findById(id);

        if(oUser.isPresent()){
            User user = oUser.get();
            for(Map.Entry<String, Object> entry : updates.entrySet()) {
                String key = entry.getKey();
                switch (key) {
                    case "firstName":
                        user.setFirstName(entry.getValue().toString());
                        break;
                    case "lastName":
                        user.setLastName(entry.getValue().toString());
                        break;
                    case "room":
                        Optional<Room> oRoom = roomRepository.findById((Integer) entry.getValue());
                        if(oRoom.isPresent()){
                            user.setRoom(oRoom.get());
                            break;
                        } else {
                            return ResponseEntity.notFound().build();
                        }
                    case "role":
                        user.setRole(User.Role.valueOf(entry.getValue().toString()));
                        break;
                    case "score":
                        user.setScore((Integer) entry.getValue());
                        break;
                }
            }
            return ResponseEntity.ok(userRepository.save(user));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/users/{id}/set-to-owner")
    public ResponseEntity<User> setUserToOwner(@PathVariable Integer id){
        Optional<User> oUser = userRepository.findById(id);
        if(oUser.isPresent()){
            User user = oUser.get();
            user.setRole(User.Role.ROLE_OWNER);
            return ResponseEntity.ok(userRepository.save(user));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/users/{id}/add-score")
    public ResponseEntity<User> addScore(@PathVariable Integer id, @RequestParam Integer scorePoint) {
        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isPresent()) {
            User user = oUser.get();
            user.addScore(scorePoint);
            return ResponseEntity.ok(userRepository.save(user));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/users/{id}/assign")
    public ResponseEntity<Iterable<Task>> assignTask(@PathVariable Integer id, @RequestBody List<Task> tasks) {
        Optional<User> oUser = userRepository.findById(id);

        if (!oUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        for (Task task : tasks) {
            Optional<Task> oTask = taskRepository.findById(task.getId());
            if (!oTask.isPresent()) {
                continue;
            }

            oTask.get().setUser(oUser.get());
            taskRepository.save(oTask.get());
        }

        return ResponseEntity.ok(oUser.get().getTasks());


    }

    @DeleteMapping("users/{id}")
    public ResponseEntity deleteUser(@PathVariable Integer id) {
        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isPresent()) {
            userRepository.delete(oUser.get());
            return ResponseEntity.ok().build();

        }

        return ResponseEntity.notFound().build();
    }

}
