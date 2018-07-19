package edu.inconcept.netflix.service.impl;

import edu.inconcept.netflix.entity.Genre;
import edu.inconcept.netflix.repository.GenreRepository;
import edu.inconcept.netflix.service.GenreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GenreServiceImpl implements GenreService {

    @Autowired
    GenreRepository genreRepository;

    @Override
    public Genre findGenreByName(String name) {
        return genreRepository.findByName(name);
    }
}
