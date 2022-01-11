package com.many.exercise.controller;

import com.many.exercise.entity.Category;
import com.many.exercise.repository.CategoryRepository;
import com.many.exercise.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/category")
public class CategoryController {

    @Autowired
    CategoryService categoryService;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getCategories() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Category> categories = categoryService.findAll();
            map.put("data", categories);
            map.put("status", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "Not found data");
            map.put("status", false);
        }
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<Map<String, Object>> saveCategory(@RequestBody Category category) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Category saved = categoryService.save(category);
            map.put("data", saved);
            map.put("status", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "Create Failed");
            map.put("status", false);
        }
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateCategory(@PathVariable("id") long id, @RequestBody Category category) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Category findById = categoryService.findById(id);
            if(findById.getId()>0){
                findById.setName(category.getName());
                Category updated = categoryService.save(findById);
                map.put("data", updated);
                map.put("status", true);
            }else{
                map.put("error", "Not found category");
                map.put("status", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "Update Failed");
            map.put("status", false);
        }
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<Map<String, Object>> deleteCategory(@PathVariable("id") long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Category findById = categoryService.findById(id);
            if(findById.getId()>0){
                Boolean deleted = categoryService.deleteById(findById.getId());
                map.put("status", deleted);
            }else{
                map.put("error", "Not found category");
                map.put("status", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "Delete Failed");
            map.put("status", false);
        }
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }
}
