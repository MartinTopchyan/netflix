package edu.inconcept.netflix.controller;

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

    @RequestMapping(method = RequestMethod.GET , value = "/movies/{id}")
    public ResponseEntity<MovieDto> getMovie(@PathVariable Long id){
        return new  ResponseEntity<>(movieService.getById(id), HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/movies")
    public ResponseEntity<List<MovieDto>> getMovies(){
        return new ResponseEntity<>(movieService.getAll(),HttpStatus.OK);
    }
    @RequestMapping(method = RequestMethod.GET,value = "/movies/genre/{name}")
    public ResponseEntity<List<MovieDto>> getMoviesByGenre(@PathVariable String name){
        return new ResponseEntity<>(movieService.getMoviesByGenre(name),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/movies/{rating}")
    public ResponseEntity<List<MovieDto>> getMoviesByRating(@PathVariable Double rating){
        return new ResponseEntity<>(movieService.getMoviesByRating(rating),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/movies/{greaterAvgRating}")
    public ResponseEntity<List<MovieDto>> getMoviesGreaterAvgRating(@PathVariable Double greaterAvgRating){
        return new ResponseEntity<>(movieService.getMoviesGreaterAvgRating(greaterAvgRating),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/movies/director/{name}")
    public ResponseEntity<List<MovieDto>> getMoviesByDirector(@PathVariable String name){
        return new ResponseEntity<>(movieService.getMoviesByDirector(name),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/movies/{title}")
    public ResponseEntity<MovieDto> getMoviesByTitle(@PathVariable String title){
        return new ResponseEntity<>(movieService.getMoviesByTitle(title),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.GET,value = "/movies/page/{pageNumber}")
    public ResponseEntity<List<MovieDto>> getByPage(@PathVariable Integer pageNumber){
        return new ResponseEntity<>(movieService.getByPage(pageNumber),HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST , value = "/movies")
    public ResponseEntity<MovieDto> addMovie(@RequestBody MovieDto movieDto){
        return new ResponseEntity<>(movieService.add(movieDto),HttpStatus.CREATED);
    }

    @RequestMapping(method = RequestMethod.DELETE , value = "/movies/delete/{id}")
    public ResponseEntity<MovieDto> removeMovie(@PathVariable Long id){
        movieService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @RequestMapping(method = RequestMethod.POST , value = "/movies/csvFile/{path}")
    public ResponseEntity<List<MovieDto>> addMoviesFromCSV(@PathVariable String path){
        try {
            return new ResponseEntity<>(movieService.addMoviesFromCSV(path),HttpStatus.OK);
        } catch (IOException | ParseException e) {
            return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }
}