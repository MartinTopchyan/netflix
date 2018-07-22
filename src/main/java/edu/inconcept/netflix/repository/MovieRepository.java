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


    @Query(value = "select * from movie where rating like %:rating%",nativeQuery = true)
    public List<Movie> findMoviesByRating(@Param("rating")Double rating);

    @Query(value = "select * from movie where rating > avg (rating)",nativeQuery = true)
    public List<Movie> findMoviesGreaterAvgRating(@Param("rating")Double rating);

//    @Query(value = "select m from Movie m where m.rating > avg (m.rating)")
//    public List<Movie> findMoviesGreatAvgRating(@Param("rating")Double rating);

    @Query(value = "select * from movie where title like %:title%",nativeQuery = true)
    public Movie findMovieByName(@Param("title")String title);

    @Query(value = "select * from movie left join movies_genre on movie_genre.movie_id = movie.id  \n" +
            " left join genre on genre.id = movies_genre.genre_id /n" +
            "where genre.name like %:genreName%",nativeQuery = true)
    public List<Movie> findAllByGenre(@Param("genreName")String genreName);

    @Query(value = "select * from movie LEFT JOIN movie_director ON movie_director.movie_id = movie.id" +
            "left join director on director.id = movie_director.director_id  WHERE  director.name like %:directorName% " ,nativeQuery = true)
    public List<Movie> findMoviesByDirector(@Param("directorName")String directorName);

//    @Query(value = "select  m  from Movie m left join m.directors d left join d.movies mov where mov.name like concat('%',:name,'%')")
//    List<Movie> findMovieByDirectorName(@Param("directorName")String name);
//    @Query(value = "select  m from Movie m where m.title like concat('%',:title,'%' )")
//    public Movie findMovieByTitle(@Param("title")String title);


//    @Query(value = "select  m from Movie m where m.rating like concat('%',:rating,'%' )")
//    public List<Movie> findMoviesByIMDbrating(@Param("rating")Double rating);


}
