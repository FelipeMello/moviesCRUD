package com.example.movie.model;


import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "movie")
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Movie {
    @Id
    @Column(name = "id")
    @GenericGenerator(
            name = "movie_id_seq",
            strategy = "org.hibernate.id.enhanced.SequenceStyleGenerator",
            parameters = {
                    @org.hibernate.annotations.Parameter(name = "sequence_name", value = "movie_id_seq"),
                    @org.hibernate.annotations.Parameter(name = "INCREMENT", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MINVALUE", value = "1"),
                    @org.hibernate.annotations.Parameter(name = "MAXVALUE", value = "2147483647"),
                    @org.hibernate.annotations.Parameter(name = "CACHE", value = "1")
            }
    )
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "movie_id_seq")
    private long id;

    @Column(name = "title")
    private String title;

    @Column(name = "year")
    private int year;

    @Column(name = "genres")
    private String genres;

    @Column(name = "director")
    private String director;

    @Column(name = "country")
    private String country;

    @Column(name = "minutes")
    private int minutes;
}
