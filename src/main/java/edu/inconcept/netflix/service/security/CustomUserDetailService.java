package edu.inconcept.netflix.service.security;

import edu.inconcept.netflix.entity.SystemUser;
import edu.inconcept.netflix.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;


@Service
public class CustomUserDetailService implements UserDetailsService {
    @Autowired
    private SystemUserRepository systemUserRepository;
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
        Optional<SystemUser> optionalUser = systemUserRepository.findByName(userName) ;
        optionalUser
                .orElseThrow(()->new UsernameNotFoundException("UserName not found"));
        return optionalUser
                .map(CustomUserDetails::new).get();
    }
}

