package edu.inconcept.netflix.service;

import edu.inconcept.netflix.entity.Director;
import org.springframework.stereotype.Service;

@Service
public interface DirectorService {
    Director findDirectorByName(String name);
}
