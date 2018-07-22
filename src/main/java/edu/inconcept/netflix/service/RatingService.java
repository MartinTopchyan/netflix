package edu.inconcept.netflix.service;

import edu.inconcept.netflix.entity.Rating;
import org.springframework.stereotype.Service;

@Service
public interface RatingService {
    Rating add(Rating rating);
}
