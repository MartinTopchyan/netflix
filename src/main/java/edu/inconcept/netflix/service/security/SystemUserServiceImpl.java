package edu.inconcept.netflix.service.security;

import edu.inconcept.netflix.entity.SystemUser;
import edu.inconcept.netflix.repository.RoleRepository;
import edu.inconcept.netflix.repository.SystemUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class SystemUserServiceImpl implements SystemUserService {

    @Autowired
    private SystemUserRepository systemUserRepository;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private BCryptPasswordEncoder bCryptPasswordEncoder;

    @Override
    public void save(SystemUser systemUser) {
        systemUser.setPassword(bCryptPasswordEncoder.encode(systemUser.getPassword()));
        systemUser.setRoles(new ArrayList<>(roleRepository.findAll()));
        systemUserRepository.save(systemUser);
    }

    @Override
    public Optional<SystemUser> findByUsername(String username) {
        return systemUserRepository.findByName(username);
    }
}
