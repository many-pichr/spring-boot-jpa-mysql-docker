package com.many.exercise.service;
import com.many.exercise.entity.Movie;

import java.util.List;

public interface MovieService {
    List<Movie> findAll();
    Movie findById(Long id);
    Movie save(Movie movie);
    Boolean deleteById(Long id);
}
