package edu.inconcept.netflix.service.impl;

import edu.inconcept.netflix.entity.Account;
import edu.inconcept.netflix.repository.AccountRepository;
import edu.inconcept.netflix.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.List;

@Service
@Transactional
public class AccountServiceImpl implements AccountService{
    @Autowired
    AccountRepository accountRepository;
    @Override
    public Account findAccountByImdbId(Long id) {
        return accountRepository.findAccountByAccountImdbId(id);
    }
    @Override
    public Account add(Account account) {
        return accountRepository.save(account);
    }

    @Override
    public List<Account> bulkInsert(Collection<Account> values) {
        return accountRepository.saveAll(values);
    }
}
