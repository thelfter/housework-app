package hu.elte.housework.controllers;

import hu.elte.housework.entities.TaskCategory;
import hu.elte.housework.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@RestController
@RequestMapping("/api")
public class CategoryController {
    @Autowired
    private CategoryRepository categoryRepository;

    @GetMapping("/category")
    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_OWNER"})
    public ResponseEntity<Iterable<TaskCategory>> getAll() {
        Iterable<TaskCategory> categories = categoryRepository.findAll();
        return ResponseEntity.ok(categories);
    }

    @Secured({"ROLE_USER", "ROLE_ADMIN", "ROLE_OWNER"})
    @GetMapping("/category/{id}")
    public ResponseEntity<TaskCategory> getTaskCategory(@PathVariable Integer id) {
        Optional<TaskCategory> oTaskCategory = categoryRepository.findById(id);
        return oTaskCategory.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());

    }

    @Secured({"ROLE_ADMIN"})
    @PutMapping("/category/{id}")
    public ResponseEntity<TaskCategory> updateTaskCategory(@PathVariable Integer id, @RequestBody TaskCategory TaskCategory){
        Optional<TaskCategory> oTaskCategory = categoryRepository.findById(id);
        if(oTaskCategory.isPresent()){
            TaskCategory.setId(id);
            return ResponseEntity.ok(categoryRepository.save(TaskCategory));
        }

        return ResponseEntity.notFound().build();
    }


    @Secured({"ROLE_ADMIN"})
    @DeleteMapping("category/{id}")
    public ResponseEntity deleteTaskCategory(@PathVariable Integer id) {
        Optional<TaskCategory> oTaskCategory = categoryRepository.findById(id);
        if (oTaskCategory.isPresent()) {
            categoryRepository.delete(oTaskCategory.get());
            return ResponseEntity.ok().build();

        }

        return ResponseEntity.notFound().build();
    }

}
