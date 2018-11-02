package hu.elte.housework.controllers;

import hu.elte.housework.entities.Task;
import hu.elte.housework.entities.TaskCategory;
import hu.elte.housework.repositories.CategoryRepository;
import hu.elte.housework.repositories.TaskRepository;
import hu.elte.housework.repositories.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api")
public class TaskController {
    @Autowired
    private TaskRepository taskRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CategoryRepository categoryRepository;

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

    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_OWNER"})
    @GetMapping("/tasks")
    public ResponseEntity<Iterable<Task>> getAll() {
        Iterable<Task> tasks = taskRepository.findAll();
        return ResponseEntity.ok(tasks);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_OWNER"})
    @GetMapping("/tasks/{id}")
    public ResponseEntity<Task> getTask(@PathVariable Integer id) {
        Optional<Task> oTask = taskRepository.findById(id);
        return oTask.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

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

    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_OWNER"})
    @PutMapping("/tasks/{id}/finished")
    public ResponseEntity<Task> finishTask(@PathVariable Integer id) {
        Optional<Task> oTask = taskRepository.findById(id);
        if (oTask.isPresent()) {
            Task task = oTask.get();
            task.setIsCompleted(true);
            task.setUser(null);
            return ResponseEntity.ok(taskRepository.save(task));
        }

        return ResponseEntity.notFound().build();

    }

    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_OWNER"})
    @GetMapping("/tasks/{id}/categories")
    public ResponseEntity<List<TaskCategory>> getCategory(@PathVariable Integer id){
        Optional<Task> oTask = taskRepository.findById(id);
        if(oTask.isPresent()){
           return ResponseEntity.ok(oTask.get().getCategories());
        }

        return ResponseEntity.notFound().build();
    }

    @Secured({ "ROLE_OWNER", "ROLE_ADMIN" })
    @PutMapping("/tasks/{id}/categories")
    public ResponseEntity<List<TaskCategory>> putCategory(@PathVariable Integer id, @RequestBody List<TaskCategory> categories){
        Optional<Task> oTask = taskRepository.findById(id);
        if (!oTask.isPresent()) {
            return ResponseEntity.notFound().build();
        }

        if(categories.isEmpty()){
            oTask.get().getCategories().clear();
            taskRepository.save(oTask.get());
        }

        for (TaskCategory tc: categories) {
            Optional<TaskCategory> oCat  = categoryRepository.findById(tc.getId());
            if (!oCat.isPresent()) {
                continue;
            }

            if(!oTask.get().getCategories().contains(oCat.get())){
                oTask.get().getCategories().add(oCat.get());
            }
            taskRepository.save(oTask.get());
        }

        return ResponseEntity.ok(oTask.get().getCategories());
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
