package edu.inconcept.netflix.controller;

import edu.inconcept.netflix.entity.Movie;
import edu.inconcept.netflix.service.MovieCSVParserService;
import edu.inconcept.netflix.service.MovieService;
import edu.inconcept.netflix.service.dto.MovieDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.awt.*;
import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@RestController
public class MovieController {
    @Autowired
    private MovieService movieService;
    @Autowired
    private MovieCSVParserService movieCSVParserService;

    @RequestMapping(method = RequestMethod.GET, value = "/movies/{id}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable Long id) {
        return new ResponseEntity<>(movieService.getById(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/movies")
    public ResponseEntity<List<MovieDto>> getMovies() {
        return new ResponseEntity<>(movieService.getAll(), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/movies/genre/{name}")
    public ResponseEntity<List<MovieDto>> getMoviesByGenre(@PathVariable String name) {
        return new ResponseEntity<>(movieService.getMoviesByGenre(name), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/movies/{rating}")
    public ResponseEntity<List<MovieDto>> getMoviesByRating(@PathVariable Double rating) {
        return new ResponseEntity<>(movieService.getMoviesByRating(rating), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/movies/{greaterAvgRating}")
    public ResponseEntity<List<MovieDto>> getMoviesGreaterAvgRating(@PathVariable Double greaterAvgRating) {
        return new ResponseEntity<>(movieService.getMoviesGreaterAvgRating(greaterAvgRating), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/movies/director/{name}")
    public ResponseEntity<List<MovieDto>> getMoviesByDirector(@PathVariable String name) {
        return new ResponseEntity<>(movieService.getMoviesByDirector(name), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/movies/{title}")
    public ResponseEntity<MovieDto> getMoviesByTitle(@PathVariable String title) {
        return new ResponseEntity<>(movieService.getMoviesByTitle(title), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/movies/page/{pageNumber}")
    public ResponseEntity<List<MovieDto>> getByPage(@PathVariable Integer pageNumber) {
        return new ResponseEntity<>(movieService.getByPage(pageNumber), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/movies")
    public ResponseEntity<Movie> addMovie(@RequestBody Movie movie) {
        return new ResponseEntity<>(movieService.add(movie), HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/movies/delete/{id}")
    public ResponseEntity<MovieDto> removeMovie(@PathVariable Long id) {
        movieService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/movies/csvFile/{path}")
    public ResponseEntity<List<Movie>> addMoviesFromCSV(@PathVariable String path) {
        try {
            movieCSVParserService.parse(path);
            return new ResponseEntity<>(HttpStatus.CREATED);
        } catch (IOException | ParseException e) {
            e.printStackTrace();
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);

        }
    }
}
