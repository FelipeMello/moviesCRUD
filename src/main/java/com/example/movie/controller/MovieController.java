package com.example.movie.controller;

import com.example.movie.request.MovieRequest;
import com.example.movie.service.MovieService;
import com.example.movie.view.MovieView;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/movie")
public class MovieController {

    private final MovieService movieService;

    public MovieController(MovieService movieService) {
        this.movieService = movieService;
    }

    @GetMapping("/{id}")
    @ResponseBody
    public MovieView getMovie(@PathVariable Long id) {
        return movieService.getMovie(id);
    }

    @GetMapping
    @ResponseBody
    public Page<MovieView> getAllMovies(@PageableDefault(sort = "id", direction = Sort.Direction.ASC) Pageable pageable) {
        return movieService.getAllMovies(pageable);
    }

    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    @ResponseBody
    public MovieView createMovie(@RequestBody @Valid MovieRequest movieRequest) {
        return movieService.createMovie(movieRequest);
    }

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteMovie(@PathVariable Long id) {
        movieService.deleteMovie(id);
    }

    @PutMapping("/{id}")
    public MovieView updatePlayer(@PathVariable(name = "id") Long id, @RequestBody @Valid MovieRequest movieRequest) {
        return movieService.update(id, movieRequest);
    }
}
