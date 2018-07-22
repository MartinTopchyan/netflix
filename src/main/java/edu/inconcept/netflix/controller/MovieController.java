package edu.inconcept.netflix.controller;

import edu.inconcept.netflix.entity.Movie;
import edu.inconcept.netflix.service.ImportMovieService;
import edu.inconcept.netflix.service.impl.MovieService;
import edu.inconcept.netflix.service.dto.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;
import java.util.List;

@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private ImportMovieService importMovieService;

    @RequestMapping(method = RequestMethod.GET, value = "/movies/{id}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable Long id) {
        return new ResponseEntity<>(movieService.getById(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/movies")
    public ResponseEntity<List<MovieDto>> getMovies() {
        return new ResponseEntity<>(movieService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/movies/genre")
    public ResponseEntity<List<MovieDto>> getMoviesByGenre(@RequestParam String name) {
        return new ResponseEntity<>(movieService.getMoviesByGenre(name), HttpStatus.OK);
    }



    @RequestMapping(method = RequestMethod.GET, value = "/movies/provideRating")
    public ResponseEntity<List<Movie>> getMoviesGreaterAvgRating(@RequestParam Double rating) {
        return new ResponseEntity<>(movieService.getMoviesGreaterAvgRating(rating), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/movies/director")
    public ResponseEntity<List<MovieDto>> getMoviesByDirector(@RequestParam String name) {
        return new ResponseEntity<>(movieService.getMoviesByDirector(name), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/movies/title")
    public ResponseEntity<Movie> getMoviesByTitle(@RequestParam String title) {
        return new ResponseEntity<>(movieService.getMoviesByTitle(title), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/movies/page")
    public ResponseEntity<List<Movie>> getByPage(@RequestParam Integer pageNumber) {
        return new ResponseEntity<>(movieService.getByPage(pageNumber), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/movies/import")
    public ResponseEntity<List<Movie>> importMovies() {
        try {
            importMovieService.importMovie();
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException  e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
}
