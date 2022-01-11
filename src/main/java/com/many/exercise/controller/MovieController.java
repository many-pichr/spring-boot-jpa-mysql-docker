package com.many.exercise.controller;
import com.many.exercise.entity.Movie;
import com.many.exercise.service.MovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/movie")
public class MovieController {

    @Autowired
    MovieService movieService;

    @GetMapping()
    public ResponseEntity<Map<String, Object>> getMovies() {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            List<Movie> categories = movieService.findAll();
            map.put("data", categories);
            map.put("status", true);
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "Not found movies");
            map.put("status", false);
        }
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Map<String, Object>> getMovieById(@PathVariable("id") long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Movie movie = movieService.findById(id);
            if(movie.getId()>0){
                map.put("data", movie);
                map.put("status", true);
            }else{
                map.put("error", "Not found movie");
                map.put("status", false);
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "Not found movie");
            map.put("status", false);
        }
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }
    @PostMapping()
    public ResponseEntity<Map<String, Object>> saveMovie(@RequestBody Movie movie) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            if (movie.getRating()>=0.5 && movie.getRating()<=5) {
                Movie saved = movieService.save(movie);
                map.put("data", saved);
                map.put("status", true);
            }else{
                map.put("error", "Please enter rating between 0.5 to 5");
                map.put("status", false);
            }

        } catch (Exception e) {
            e.printStackTrace();
            map.put("error", "Create Failed");
            map.put("status", false);
        }
        return new ResponseEntity<Map<String, Object>>(map, HttpStatus.OK);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Map<String, Object>> updateMovie(@PathVariable("id") long id, @RequestBody Movie movie) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Movie findById = movieService.findById(id);
            if(findById.getId()>0){
                findById.setName(movie.getName());
                findById.setCategory(movie.getCategory());
                findById.setRating(movie.getRating());
                if (movie.getRating()>=0.5 && movie.getRating()<=5) {
                    Movie updated = movieService.save(findById);
                    map.put("data", updated);
                    map.put("status", true);
                }else{
                    map.put("error", "Please enter rating between 0.5 to 5");
                    map.put("status", false);
                }
            }else{
                map.put("error", "Not found movie");
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
    public ResponseEntity<Map<String, Object>> deleteMovie(@PathVariable("id") long id) {
        Map<String, Object> map = new HashMap<String, Object>();
        try {
            Movie findById = movieService.findById(id);
            if(findById.getId()>0){
                Boolean deleted = movieService.deleteById(findById.getId());
                map.put("status", deleted);
            }else{
                map.put("error", "Not found movie");
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
