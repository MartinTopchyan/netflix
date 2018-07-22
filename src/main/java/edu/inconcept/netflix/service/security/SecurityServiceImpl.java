package edu.inconcept.netflix.service.security;

import edu.inconcept.netflix.domain.CustomUserDetails;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class SecurityServiceImpl implements SecurityService {


    @Autowired
    private CustomUserDetailService userDetailService;

    private static final org.slf4j.Logger logger = LoggerFactory.getLogger(SecurityServiceImpl.class);
    @Override
    public String findLoggedInUsername() {
        Object userDetails = SecurityContextHolder.getContext().getAuthentication().getDetails();
        if(userDetails instanceof CustomUserDetails){
            return ((CustomUserDetails)userDetails).getEmail();
        }
        return null;
    }
}


