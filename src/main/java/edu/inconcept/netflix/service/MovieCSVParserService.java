package edu.inconcept.netflix.service;

import edu.inconcept.netflix.entity.*;
import edu.inconcept.netflix.service.impl.AccountServiceImpl;
import edu.inconcept.netflix.service.impl.DirectorServiceImpl;
import edu.inconcept.netflix.service.impl.GenreServiceImpl;
import edu.inconcept.netflix.service.impl.TitleTypeServiceImpl;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.io.Reader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.Date;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Service
public class MovieCSVParserService {

    @Autowired
    TitleTypeServiceImpl titleTypeService;

    @Autowired
    DirectorServiceImpl directorService;

    @Autowired
    GenreServiceImpl genereService;

    @Autowired
    MovieService movieService;

    @Autowired
    AccountServiceImpl accountService;

    public void parse(String path) throws IOException, ParseException {
        Reader reader = Files.newBufferedReader(Paths.get(path));
        CSVParser csvParser = new CSVParser(reader, CSVFormat.DEFAULT
                .withFirstRecordAsHeader()
                .withIgnoreHeaderCase()
                .withTrim());

        for (CSVRecord csvRecord : csvParser) {
            String position = csvRecord.get("position");
            String constant = csvRecord.get("const");
            String created = csvRecord.get("created");
            String title = csvRecord.get("title");
            String titleTypeName = csvRecord.get("title type");
            String directorName = csvRecord.get("directors");
            String imdbRating = csvRecord.get("IMDb rating");
            String blanaidRated = csvRecord.get("Blanaid rated");
            String runtime = csvRecord.get("runtime (mins)");
            String genre = csvRecord.get("genres");
            String numberVotes = csvRecord.get("num. votes");
            String releaseDate = csvRecord.get("Release Date (month/day/year)");
            String url = csvRecord.get("url");

            Movie movie = new Movie();
            movie.setId(Long.parseLong(position));
            movie.setConstant(constant);
            movie.setTitle(title);
            TitleType titleType = titleTypeService.findTitleTypeByName(titleTypeName);
            movie.setTitleType(titleType == null ? new TitleType(titleTypeName) : titleType);
            Director director = directorService.findDirectorByName(directorName);
            movie.setDirector(director == null ? new Director(directorName) : director);

            movie.setRating(Double.parseDouble(imdbRating));
            movie.setRuntime(Integer.parseInt(runtime));
            movie.setGenres(getGenreList(genre.split(",")));
            movie.setNumberVotes(Long.parseLong(numberVotes));
            movie.setReleaseDate(parseDate(releaseDate));
            movie.setUrl(url);

            movie = movieService.add(movie);

            Rating rating = new Rating();
            Account account = new Account();
            rating.setMovie(movie);
            rating.setCreatedDate(parseDate(created));
            rating.setBlanaidRated(Integer.parseInt(blanaidRated));
            rating.setAccount(accountService.findAccountByID(account.getId()));
        }
    }

    private List<Genre> getGenreList(String [] genres){
        List<Genre> genreList = new ArrayList<>();
        for (String genreNmae : genres){
            Genre genre = genereService.findGenreByName(genreNmae);
            genreList.add(genre == null ? new Genre(genreNmae) : genre);
        }
        return genreList;
    }

    private Date parseDate(String relesadate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate dateTime = LocalDate.parse(relesadate, formatter);
        return Date.valueOf(dateTime);
    }
    private java.util.Date parseJavaUtilData(String createdDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEE MMM dd  HH:mm:ss yyyy");
        LocalDate dateTime = LocalDate.parse(createdDate, formatter);
        return Date.valueOf(dateTime);
    }
}
