package edu.inconcept.netflix.repository;

import edu.inconcept.netflix.entity.Movie;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MovieRepository extends JpaRepository<Movie,Long> {

    @Query(value = "select m from Movie m where m.id = :id")
    public Movie findMovieById(@Param("id") Long id);



    @Query(value = "select * from movie where imdbrating > ? ",nativeQuery = true)
    public List<Movie> findMoviesGreaterAvgRating(@Param("rating")Double rating);


    @Query(value = "select * from movie where title like %:title%",nativeQuery = true)
    public Movie findMovieByName(@Param("title")String title);


    @Query(value = "select * from movie LEFT join movies_genre on movie_genre.movie_id = movie.id  \n" +
            " LEFT join genre on genre.id = movies_genre.genre_id /n" +
            "where genre.name like %:genreName%",nativeQuery = true)
    public List<Movie> findAllByGenre(@Param("genreName")String genreName);


    @Query(value = "select * from movie LEFT JOIN movie_director ON movie.id = movie_director.movie_id   " +
            "LEFT JOIN director on   movie_director.director_id = director.id  WHERE  director.name like %:directorName% " ,nativeQuery = true)
    public List<Movie> findMoviesByDirector(@Param("directorName")String directorName);



}
