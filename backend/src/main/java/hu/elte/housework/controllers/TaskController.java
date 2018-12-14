package hu.elte.housework.controllers;

import hu.elte.housework.entities.Task;
import hu.elte.housework.repositories.TaskRepository;
import hu.elte.housework.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Map;
import java.util.Optional;

@CrossOrigin
@RestController
@RequestMapping("/api")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

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
        return oTask.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @PutMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Integer id, @RequestBody Task task) {
        Optional<Task> oTask = taskRepository.findById(id);
        if (oTask.isPresent()) {
            task.setId(id);
            return ResponseEntity.ok(taskRepository.save(task));
        }

        return ResponseEntity.notFound().build();
    }

    @PatchMapping("/tasks/{id}")
    public ResponseEntity<Task> updateTask(@PathVariable Integer id, @RequestBody Map<String, Object> updates) {
        Optional<Task> oTask = taskRepository.findById(id);

        if (oTask.isPresent()) {
            Task task = oTask.get();

            for (Map.Entry<String, Object> entry : updates.entrySet()) {
                String key = entry.getKey();
                switch (key) {
                    case "taskName":
                        task.setTaskName(entry.getValue().toString());
                        break;
                    case "taskDescription":
                        task.setTaskDescription(entry.getValue().toString());
                        break;
                    case "score":
                        task.setScore((Integer) entry.getValue());
                        break;
                    case "isCompleted":
                        task.setIsCompleted((Boolean) entry.getValue());
                        break;
                }
            }
            return ResponseEntity.ok(taskRepository.save(task));
        }
        return ResponseEntity.notFound().build();
    }

    @PutMapping("/tasks/{id}/finished")
    public ResponseEntity<Task> finishTask(@PathVariable Integer id) {
        Optional<Task> oTask = taskRepository.findById(id);
        if (oTask.isPresent()) {
            Task task = oTask.get();
            task.setIsCompleted(true);
            return ResponseEntity.ok(taskRepository.save(task));
        }

        return ResponseEntity.notFound().build();

    }

    @DeleteMapping("tasks/{id}")
    public ResponseEntity deleteTask(@PathVariable Integer id) {
        Optional<Task> oTask = taskRepository.findById(id);
        if (oTask.isPresent()) {
            taskRepository.delete(oTask.get());
            return ResponseEntity.ok().build();

        }

        return ResponseEntity.notFound().build();
    }


    @GetMapping("tasks/completed")
    public ResponseEntity<Iterable<Task>> getAllCompleted() {
        Iterable<Task> completed = taskRepository.findAllByIsCompletedTrue();
        return ResponseEntity.ok(completed);
    }
}
