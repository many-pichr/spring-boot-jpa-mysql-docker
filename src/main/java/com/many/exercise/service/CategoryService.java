package com.many.exercise.service;

import com.many.exercise.entity.Category;

import java.util.List;

public interface CategoryService {
    List<Category> findAll();
    Category findById(Long id);
    Category save(Category category);
    Boolean deleteById(Long id);
}
