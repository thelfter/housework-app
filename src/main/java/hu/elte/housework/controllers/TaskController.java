package hu.elte.housework.controllers;

import hu.elte.housework.entities.Task;
import hu.elte.housework.repositories.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @Secured({ "ROLE_OWNER", "ROLE_ADMIN" })
    @PostMapping("/tasks")
    public ResponseEntity<Task> postTask(@RequestBody Task task) {
        Optional<Task> oTask = taskRepository.findByTaskName(task.getTaskName());
        if (oTask.isPresent()) {
            return ResponseEntity.badRequest().build();
        }
        task.setId(null);
        return ResponseEntity.ok(taskRepository.save(task));
    }

    @GetMapping("/tasks")
    public ResponseEntity<Iterable<Task>> getAll() {
        Iterable<Task> tasks = taskRepository.findAll();
        return ResponseEntity.ok(tasks);
    }

    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Integer id) {
        Optional<Task> oTask = taskRepository.findById(id);
        if (oTask.isPresent()) {
            return ResponseEntity.ok(oTask.get());
        }

        return ResponseEntity.notFound().build();
    }

    @Secured({ "ROLE_OWNER", "ROLE_ADMIN" })
    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Integer id, @RequestBody Task task){
        Optional<Task> oTask = taskRepository.findById(id);
        if(oTask.isPresent()){
            task.setId(id);
            return ResponseEntity.ok(taskRepository.save(task));
        }

        return ResponseEntity.notFound().build();
    }

    @Secured({ "ROLE_OWNER", "ROLE_ADMIN" })
    @DeleteMapping("tasks/{id}")
    public ResponseEntity deleteTask(@PathVariable Integer id) {
        Optional<Task> oTask = taskRepository.findById(id);
        if (oTask.isPresent()) {
            taskRepository.delete(oTask.get());
            return ResponseEntity.ok().build();

        }

        return ResponseEntity.notFound().build();
    }
}
