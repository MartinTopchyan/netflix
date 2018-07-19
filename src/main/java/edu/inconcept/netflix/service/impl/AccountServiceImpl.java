package edu.inconcept.netflix.service.impl;

import edu.inconcept.netflix.entity.Account;
import edu.inconcept.netflix.repository.AccountRepository;
import edu.inconcept.netflix.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AccountServiceImpl implements AccountService{
    @Autowired
    AccountRepository accountRepository;
    @Override
    public Account findAccountByID(Long id) {
        return accountRepository.findAccountById(id);
    }
    @Override
    public Account add(Account account) {
        return accountRepository.save(account);
    }
}
