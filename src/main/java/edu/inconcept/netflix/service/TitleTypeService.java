package edu.inconcept.netflix.service;

import edu.inconcept.netflix.entity.TitleType;
import org.springframework.stereotype.Service;

@Service
public interface TitleTypeService {
    TitleType findTitleTypeByName(String titleType);
}
