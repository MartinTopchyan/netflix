package edu.inconcept.netflix.service.security;

import edu.inconcept.netflix.entity.SystemUser;

import java.util.Optional;

public interface SystemUserService {
    void save(SystemUser systemUser);
    Optional<SystemUser> findByUsername(String username);
}
