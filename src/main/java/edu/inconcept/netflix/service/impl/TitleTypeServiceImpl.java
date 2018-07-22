package edu.inconcept.netflix.service.impl;

import edu.inconcept.netflix.entity.TitleType;
import edu.inconcept.netflix.repository.TitleTypeRepository;
import edu.inconcept.netflix.service.TitleTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TitleTypeServiceImpl implements TitleTypeService {
    @Autowired
    TitleTypeRepository titleTypeRepository;


    @Override
    public TitleType findTitleTypeByName(String titleType){
        return titleTypeRepository.findByName(titleType);
    }

    @Override
    public TitleType save(TitleType titleType) {
        return titleTypeRepository.save(titleType);
    }
}
