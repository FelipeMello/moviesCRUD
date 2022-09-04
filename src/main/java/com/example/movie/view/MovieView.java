package com.example.movie.view;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class MovieView {
    private long id;

    private String title;

    private int year;

    private String genres;

    private String director;

    private String country;

    private int minutes;

}
