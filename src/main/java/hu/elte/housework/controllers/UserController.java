package hu.elte.housework.controllers;

import hu.elte.housework.entities.Task;
import hu.elte.housework.entities.User;
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
public class UserController {
    @Autowired
    private UserRepository userRepository;

    @Autowired
    private TaskRepository taskRepository;

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
    @Secured({"ROLE_OWNER", "ROLE_ADMIN"})
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

    @PutMapping("/users/{id}/add-score")
    public ResponseEntity<User> addScore(@PathVariable Integer id, @RequestParam Integer scorePoint) {
        Optional<User> oUser = userRepository.findById(id);
        if (oUser.isPresent()) {
            User user = oUser.get();
            user.setScore(scorePoint);
            return ResponseEntity.ok(userRepository.save(user));
        }

        return ResponseEntity.notFound().build();
    }

    @PutMapping("/users/{id}/assigne")
    public ResponseEntity<Iterable<Task>> assigneTask(@PathVariable Integer id, @RequestBody List<Task> tasks) {
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
