package edu.inconcept.netflix.repository;

import edu.inconcept.netflix.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleRepository extends JpaRepository<Role , Long> {
}
