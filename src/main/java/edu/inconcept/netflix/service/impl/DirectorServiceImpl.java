package edu.inconcept.netflix.service.impl;

import edu.inconcept.netflix.entity.Director;
import edu.inconcept.netflix.repository.DirectorRepository;
import edu.inconcept.netflix.service.DirectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class DirectorServiceImpl implements DirectorService {

    @Autowired
    DirectorRepository diretorRepository;

    @Override
    public Director findDirectorByName(String name) {
        return diretorRepository.findByName(name);
    }
}
