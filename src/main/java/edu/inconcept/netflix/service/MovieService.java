package edu.inconcept.netflix.service;


import edu.inconcept.netflix.entity.Movie;
import edu.inconcept.netflix.repository.MovieRepository;
import edu.inconcept.netflix.service.dto.Converter;
import edu.inconcept.netflix.service.dto.MovieDto;
import org.hibernate.service.spi.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.text.ParseException;
import java.util.List;

@Service
public class MovieService {
    private MovieRepository movieRepository;

    @Autowired
    public MovieService(MovieRepository movieRepository) {
        this.movieRepository = movieRepository;
    }
    private static final Integer PAGESIZE = 3;

    public Movie add(Movie movie)  {
      return movieRepository.save(movie);
    }

    public List<MovieDto> getAll() throws ServiceException {
        List<Movie> movies = movieRepository.findAll();
        return Converter.mapMovieEntityToDtos(movies);
    }

    public MovieDto getById(Long id) throws ServiceException {
        Movie employee = movieRepository.findMovieById(id);
        return Converter.mapMovieEntityToDto(employee);
    }

    public void delete(Long id) throws ServiceException {
        movieRepository.deleteById(id);
    }

    public List<MovieDto> getByPage(Integer pageNumber) {
        PageRequest request = new PageRequest( pageNumber - 1, PAGESIZE, Sort.Direction.DESC,"id");
        return Converter.mapMovieEntityToDtos( movieRepository.findAll(request).getContent());
    }

    public List<MovieDto> getMoviesByGenre(String genreName)throws ServiceException{
        List<Movie> movies = movieRepository.findAllByGenre(genreName);
        return Converter.mapMovieEntityToDtos(movies);
    }

    public List<MovieDto> getMoviesByRating(Double rating){
        List<Movie> movies = movieRepository.findMoviesByRating(rating);
        return Converter.mapMovieEntityToDtos(movies);
    }

    public List<MovieDto> getMoviesGreaterAvgRating(Double rating){
        List<Movie> movies = movieRepository.findMoviesGreaterAvgRating(rating);
        return Converter.mapMovieEntityToDtos(movies);
    }
    public List<MovieDto> getMoviesByDirector(String directorName){
        List<Movie> movies = movieRepository.findMoviesByDirector(directorName);
        return Converter.mapMovieEntityToDtos(movies);
    }

    public MovieDto getMoviesByTitle(String title){
        Movie movie = movieRepository.findMovieByName(title);
        return Converter.mapMovieEntityToDto(movie);
    }
}
