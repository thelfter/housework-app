package hu.elte.housework.controllers;

import hu.elte.housework.entities.Room;
import hu.elte.housework.entities.Task;
import hu.elte.housework.entities.User;
import hu.elte.housework.repositories.RoomRepository;
import hu.elte.housework.repositories.TaskRepository;
import hu.elte.housework.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.Map;
import java.util.Optional;

@CrossOrigin
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

    @PostMapping("/login")
    public ResponseEntity<User> login(@RequestBody String username) {
        Optional<User> oUser = userRepository.findByUsername(username);
        if (!oUser.isPresent()) {
            return ResponseEntity.status(401).build();
        }
        return ResponseEntity.ok(oUser.get());
    }

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
                        user.setFullName(entry.getValue().toString());
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
                }
            }
            return ResponseEntity.ok(userRepository.save(user));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/users/{id}/add-score")
    public ResponseEntity<User> addScore(@PathVariable Integer id, @RequestParam Integer scorePoint) {
        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isPresent()) {
            User user = oUser.get();

            if(scorePoint > 0) {
                user.addScore(scorePoint);
                return ResponseEntity.ok(userRepository.save(user));
            }

            return ResponseEntity.badRequest().build();
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/users/{id}/assign/{taskId}")
    public ResponseEntity<User> assignTask(@PathVariable Integer id, @PathVariable Integer taskId) {
        Optional<User> oUser = userRepository.findById(id);
        Optional<Task> oTask = taskRepository.findById(taskId);

        if (!oUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        if(!oTask.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        oTask.get().setUser(oUser.get());
        oTask.get().setAvailable(false);
        //userRepository.save(oUser.get());
        taskRepository.save(oTask.get());

        return ResponseEntity.ok(oUser.get());
    }

    @PutMapping("/users/{id}/unassign/{taskId}")
    public ResponseEntity<User> unassignTask(@PathVariable Integer id, @PathVariable Integer taskId) {
        Optional<User> oUser = userRepository.findById(id);
        Optional<Task> oTask = taskRepository.findById(taskId);

        if (!oUser.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        if(!oTask.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        Task task = oTask.get();
        task.setUser(null);
        task.setAvailable(true);
        task.setIsCompleted(false);
        taskRepository.save(task);

        return ResponseEntity.ok(oUser.get());
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
