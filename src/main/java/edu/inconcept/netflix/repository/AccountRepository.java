package edu.inconcept.netflix.repository;

import edu.inconcept.netflix.entity.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.PathVariable;

@Repository
public interface AccountRepository extends JpaRepository<Account,Long>{
     Account findAccountByAccountImdbId(Long imdbAccountId);
}
