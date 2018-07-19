package edu.inconcept.netflix.service;

import edu.inconcept.netflix.entity.Director;
import edu.inconcept.netflix.entity.Genre;
import edu.inconcept.netflix.entity.Movie;
import edu.inconcept.netflix.entity.TitleType;
import edu.inconcept.netflix.service.dto.Converter;
import edu.inconcept.netflix.service.dto.MovieDto;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;


public class MovieCSVParserService {


    public static List<MovieDto> parser(String path) throws IOException, ParseException {
        Reader reader = Files.newBufferedReader(Paths.get(path));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim());

        List<Movie> movies = new ArrayList<>();
        Movie movie = new Movie();
        for (CSVRecord csvRecord : csvParser) {

            String position = csvRecord.get("position");
            String constant = csvRecord.get("const");
            String created = csvRecord.get("created");
            String title = csvRecord.get("title");
            String titleType = csvRecord.get("title type");
            String director = csvRecord.get("directors");
            String rating = csvRecord.get("IMDb rating");
            String runtime = csvRecord.get("runtime (mins)");
            String genre = csvRecord.get("genres");
            String[] g = genre.split(",");
            List<Genre> genreList = new ArrayList<>() ;
            for(int i = 0; i < g.length ; i++){
                genreList.add(new Genre(g[i]));
            }
            String numberVotes = csvRecord.get("num. votes");
            String releaseDate = csvRecord.get("Release Date (month/day/year)");
            String url = csvRecord.get("url");


            movie.setId(Long.parseLong(position));
            movie.setConstant(constant);
            movie.setCreatedDate(new SimpleDateFormat("E, MMM dd yyyy HH:mm:ss").parse(created));
            movie.setTitle(title);
            movie.setTitleType(doesExistByName(movie, titleType) ? movie.getTitleType() : new TitleType(titleType));
            movie.setDirector(doesExistByName(movie, director) ? movie.getDirector() : new Director(director));
            movie.setRating(Double.parseDouble(rating));
            movie.setRuntime(Integer.parseInt(runtime));
            movie.setGenres(genreList) ;
            movie.setNumberVotes(Long.parseLong(numberVotes));
            movie.setReleaseDate((java.sql.Date) new SimpleDateFormat("dd-mm-yyyy").parse(releaseDate));
            movie.setUrl(url);

            movies.add(movie);
        }
        return Converter.mapMovieEntityToDtos(movies);

    }
    private static boolean doesExistByName(Movie movie,String name){
        return movie.getTitleType().getName().equals(name);
    }
}
