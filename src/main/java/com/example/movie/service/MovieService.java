package com.example.movie.service;

import com.example.movie.converter.MovieToMovieViewConverter;
import com.example.movie.error.EntityNotFoundException;
import com.example.movie.model.Movie;
import com.example.movie.repository.MovieRepository;
import com.example.movie.request.MovieRequest;
import com.example.movie.util.MessageUtil;
import com.example.movie.view.MovieView;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieService {
    private final MovieRepository movieRepository;
    private final MovieToMovieViewConverter converter;
    private final MessageUtil messageUtil;

    public MovieService(MovieRepository movieRepository, MovieToMovieViewConverter converter, MessageUtil messageUtil) {
        this.movieRepository = movieRepository;
        this.converter = converter;
        this.messageUtil = messageUtil;
    }

    public MovieView getMovie(Long id) {
        Movie movie = getMovieByIdOrElseThrow(id);
        return converter.convert(movie);
    }

    public Page<MovieView> getAllMovies(Pageable pageable) {
        Page<Movie> movies = movieRepository.findAll(pageable);
        List<MovieView> movieViews = new ArrayList<>();
        movies.forEach(movie -> {
            MovieView movieView = converter.convert(movie);
            movieViews.add(movieView);
        });
        return new PageImpl<>(movieViews, pageable, movies.getTotalElements());
    }

    public MovieView createMovie(MovieRequest movieRequest) {
        return converter.convert(movieRepository.save(buildMovie(movieRequest)));
    }

    @Transactional
    public void deleteMovie(Long id) {
        try {
            movieRepository.deleteById(id);
        } catch (EmptyResultDataAccessException erdax) {
            throw new EntityNotFoundException(messageUtil.getMessage("movie.NotFound", id));
        }
    }

    public MovieView update(Long id, MovieRequest movieRequest) {
        Movie movie = getMovieByIdOrElseThrow(id);
        return converter.convert(movieRepository.save(updateMovieValues(movie, movieRequest)));
    }

    /**
     * Update the field that are not empty
     *
     * @param movie        Movie
     * @param movieRequest MovieRequest
     * @return Movie
     */
    private Movie updateMovieValues(Movie movie, MovieRequest movieRequest) {
        if (!movieRequest.getTitle().isEmpty()) {
            movie.setTitle(movieRequest.getTitle());
        }
        if (0 != movieRequest.getYear()) {
            movie.setYear(movieRequest.getYear());
        }
        if (!movieRequest.getGenres().isEmpty()) {
            movie.setGenres(movieRequest.getGenres());
        }
        if (!movieRequest.getDirector().isEmpty()) {
            movie.setDirector(movieRequest.getDirector());
        }
        if (!movieRequest.getCountry().isEmpty()) {
            movie.setCountry(movieRequest.getCountry());
        }
        if (0 != movieRequest.getMinutes()) {
            movie.setMinutes(movieRequest.getMinutes());
        }
        return movie;
    }

    private Movie buildMovie(MovieRequest movieRequest) {
        return Movie.builder()
                .title(movieRequest.getTitle())
                .year(movieRequest.getYear())
                .genres(movieRequest.getGenres())
                .director(movieRequest.getDirector())
                .country(movieRequest.getCountry())
                .minutes(movieRequest.getMinutes()).build();
    }

    private Movie getMovieByIdOrElseThrow(Long id) {
        return movieRepository.findById(id).orElseThrow(() -> new EntityNotFoundException(messageUtil.getMessage("movie.NotFound", id)));
    }


}
