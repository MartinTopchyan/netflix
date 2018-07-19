package edu.inconcept.netflix.repository;

import edu.inconcept.netflix.domain.SystemUser;
import edu.inconcept.netflix.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long>{

     Account findAccountById(Long id);
}
