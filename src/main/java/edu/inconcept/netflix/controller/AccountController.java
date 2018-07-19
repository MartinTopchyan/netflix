package edu.inconcept.netflix.controller;

import edu.inconcept.netflix.entity.Account;
import edu.inconcept.netflix.service.AccountService;
import edu.inconcept.netflix.service.impl.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class AccountController {
    @Autowired
    AccountServiceImpl accountServiceimpl;
    @RequestMapping(method = RequestMethod.POST , value = "/accounts")
    public ResponseEntity<Account> addMovie(@RequestBody Account account){
        return new ResponseEntity<>(accountServiceimpl.add(account), HttpStatus.CREATED);
    }

}
