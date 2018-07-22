package edu.inconcept.netflix.service.impl;

import edu.inconcept.netflix.entity.Rating;
import edu.inconcept.netflix.repository.RatingRepository;
import edu.inconcept.netflix.service.RatingService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RatingServiceImpl implements RatingService {
    @Autowired
    private RatingRepository ratingRepository;
    public Rating add(Rating rating)  {
        return ratingRepository.save(rating);
    }
}
