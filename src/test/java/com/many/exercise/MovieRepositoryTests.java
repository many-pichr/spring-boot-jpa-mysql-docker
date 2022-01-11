package com.many.exercise;

import com.many.exercise.entity.Category;
import com.many.exercise.entity.Movie;
import com.many.exercise.repository.MovieRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.MethodOrderer;
import org.junit.jupiter.api.Order;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.TestMethodOrder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.annotation.Rollback;

import java.util.List;
import java.util.Optional;

@DataJpaTest
@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class MovieRepositoryTests {
    @Autowired
    private MovieRepository movieRepository;

    @Test
    @Order(1)
    @Rollback(value = false)
    public void saveMovieTest(){
        Movie movie = new Movie();
        Category category = new Category();
        movie.setName("Spider Man");
        movie.setRating(1.0);
        category.setId(1L);
        movie.setCategory(category);
        movieRepository.save(movie);
        Assertions.assertThat(movie.getId()).isGreaterThan(0);
    }

    @Test
    @Order(2)
    public void getMovieTest(){
        Movie movie = movieRepository.findById(1L).get();
        Assertions.assertThat(movie.getId()).isEqualTo(1L);
    }

    @Test
    @Order(3)
    public void getMovieListTest(){
        List<Movie> movieList = movieRepository.findAll();
        Assertions.assertThat(movieList.size()).isGreaterThan(0);
    }

    @Test
    @Order(4)
    @Rollback(value = false)
    public void updateMovieTest(){
        Movie movie = movieRepository.findById(1L).get();
        movie.setName("Fast and furious 9");
        movieRepository.save(movie);
        Assertions.assertThat(movie.getName()).isEqualTo("Fast and furious 9");
    }

    @Test
    @Order(5)
    @Rollback(value = false)
    public void deleteMovieTest(){
        Movie movie = movieRepository.findById(1L).get();
        movieRepository.delete(movie);
        Movie checkMovie = null;
        Optional<Movie> optionalMovie = movieRepository.findByName("ram@gmail.com");

        if(optionalMovie.isPresent()){
            checkMovie = optionalMovie.get();
        }
        Assertions.assertThat(checkMovie).isNull();
    }
}
