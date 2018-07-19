package edu.inconcept.netflix.service;

import edu.inconcept.netflix.domain.CustomUserDetails;
import edu.inconcept.netflix.domain.SystemUser;
import edu.inconcept.netflix.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private UserRepository userRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<SystemUser> optionalUser = userRepository.findByName(userName) ;
        optionalUser
                .orElseThrow(()->new UsernameNotFoundException("UserName not found"));
        return optionalUser.map((SystemUser user)-> {
            return new CustomUserDetails(user);
        }).get();
    }
}

