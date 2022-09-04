package com.example.movie.converter;

import com.example.movie.model.Movie;
import com.example.movie.view.MovieView;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

@Component
public class MovieToMovieViewConverter implements Converter<Movie, MovieView> {

    @Override
    public MovieView convert(Movie movie) {
        return MovieView.builder()
                .id(movie.getId())
                .title(movie.getTitle())
                .year(movie.getYear())
                .genres(movie.getGenres())
                .director(movie.getDirector())
                .country(movie.getCountry())
                .minutes(movie.getMinutes())
                .build();
    }
}
