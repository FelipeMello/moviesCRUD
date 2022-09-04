package com.example.movie.request;

import com.example.movie.base.BaseRequest;
import lombok.Builder;
import lombok.Getter;

import javax.validation.constraints.NotEmpty;

@Getter
@Builder
public class MovieRequest extends BaseRequest {

    @NotEmpty
    private String title;

    @NotEmpty
    private int year;

    @NotEmpty
    private String genres;

    @NotEmpty
    private String director;

    @NotEmpty
    private String country;

    @NotEmpty
    private int minutes;
}
