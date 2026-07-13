package com.training.ott.controller;

import com.training.ott.entity.Movie;
import com.training.ott.repository.MovieRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class MovieController {

    private final MovieRepository movieRepository;

    public MovieController(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }


    @GetMapping("/movies")
    public List<Movie> getMovies() {
        return movieRepository.findAll();
    }
}
