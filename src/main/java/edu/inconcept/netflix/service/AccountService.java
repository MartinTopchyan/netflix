package edu.inconcept.netflix.service;

import edu.inconcept.netflix.entity.Account;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.List;

@Service
public interface AccountService {
    Account findAccountByImdbId(Long id);
    Account add(Account account);

    List<Account> bulkInsert(Collection<Account> values);
}
