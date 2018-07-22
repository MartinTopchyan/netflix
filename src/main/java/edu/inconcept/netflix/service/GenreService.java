package edu.inconcept.netflix.service;

import edu.inconcept.netflix.entity.Genre;
import org.springframework.stereotype.Service;

@Service
public interface GenreService {
    Genre findGenreByName(String name);

    Genre save(Genre genre);
}
