package edu.inconcept.netflix.repository;

import edu.inconcept.netflix.domain.SystemUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends JpaRepository<SystemUser,Long>{

    Optional<SystemUser> findByName(String userName);
}
