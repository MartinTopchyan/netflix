package edu.inconcept.netflix.service.impl;

import edu.inconcept.netflix.entity.*;
import edu.inconcept.netflix.service.*;
import edu.inconcept.netflix.service.ImportMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;
@Service
public class ImportMovieServiceImpl implements ImportMovieService {

    @Autowired
    ImdbCSVParserService imdbCSVParserService;

    @Autowired
    MovieService movieService;

    @Autowired
    RatingService ratingService;

    @Value("${application.moviecsv.path}")
    String csvPath;

    @Autowired
    TitleTypeService titleTypeService;

    @Autowired
    DirectorService directorService;

    @Autowired
    GenreService genreService;

    @Autowired
    AccountService accountService;

    @Override
    public void importMovie() throws IOException {
        List<ImdbcsvRecord> imdbcsvRecords = imdbCSVParserService.parseImdbCsv(csvPath);

        Map<String, TitleType> titleTypeMap = imdbcsvRecords.stream()
                .map(ImdbcsvRecord::getTitleType)
                .distinct()
                .collect(Collectors.toMap(Function.identity(), this::createTitleType));

        Map<String, Director> directorMap = imdbcsvRecords.stream()
                .flatMap(record -> record.getDirectors().stream())
                .distinct()
                .collect(Collectors.toMap(Function.identity(), this::createDirector));

        Map<String, Genre> genreMap = imdbcsvRecords.stream()
                .flatMap(record -> record.getGenres().stream())
                .distinct()
                .collect(Collectors.toMap(Function.identity(), this::createGenre));

        Map<Long,Account> accountMap = imdbcsvRecords.stream()
                .map(ImdbcsvRecord ::getAccountImdbId)
                .distinct()
                .collect(Collectors.toMap(Function.identity(), this::getAccountByAccountImdbId));

        Map<String ,Movie> movieMap = new HashMap<>();
        Movie movie;
        for (ImdbcsvRecord imdbcsvRecord : imdbcsvRecords) {
            Rating rating = new Rating();
            movie = movieMap.get(imdbcsvRecord.getConstant());
            if(movie == null) {
                movie = new Movie();
                movie.setId(imdbcsvRecord.getId());
                movie.setConstant(imdbcsvRecord.getConstant());
                movie.setTitle(imdbcsvRecord.getTitle());
                movie.setNumberVotes(imdbcsvRecord.getNumVotes());
                movie.setRating(imdbcsvRecord.getImdbRated());
                movie.setRuntime(imdbcsvRecord.getRuntime());
                movie.setUrl(imdbcsvRecord.getUrl());
                movie.setTitleType(titleTypeMap.get(imdbcsvRecord.getTitleType()));
                movie.setDirectors(imdbcsvRecord.getDirectors().stream().map(directorMap::get).collect(Collectors.toList()));
                movie.setGenres(imdbcsvRecord.getGenres().stream().map(genreMap::get).collect(Collectors.toList()));
                movie = movieService.add(movie);
                movieMap.put(movie.getConstant(),movie);
            }
            rating.setCreatedDate(imdbcsvRecord.getCreatdeDate());
            rating.setMovie(movie);
            rating.setAccountRated(imdbcsvRecord.getAccountRated());
            rating.setAccount(accountMap.get(imdbcsvRecord.getAccountImdbId()));
            ratingService.add(rating);
        }
    }

    private Account getAccountByAccountImdbId(Long accountImdbId) {
        return  accountService.findAccountByImdbId(accountImdbId);
    }


    private TitleType createTitleType(String name){
        TitleType titleType = new TitleType(name);
        titleType = titleTypeService.save(titleType);
        return titleType;
    }
    private Director createDirector(String name){
        Director director = new Director(name);
        director = directorService.save(director);
        return director;
    }
    private Genre createGenre(String name){
        Genre genre = new Genre(name);
        genre = genreService.save(genre);
        return genre;
    }

}
