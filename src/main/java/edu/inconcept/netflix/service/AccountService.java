package edu.inconcept.netflix.service;

import edu.inconcept.netflix.entity.Account;
import org.springframework.stereotype.Service;

@Service
public interface AccountService {
    Account findAccountByImdbId(Long id);
    Account add(Account account);
}
