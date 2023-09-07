package com.workintech.S7D3.controller;

import com.workintech.S7D3.entity.Account;
import com.workintech.S7D3.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/account")
public class AccountController {

    private AccountService accountService;
     @Autowired
    public AccountController(AccountService accountService) {
        this.accountService = accountService;
    }
    @GetMapping("/")
    public List<Account> findAll(){
         return accountService.findAll();
    }
    @GetMapping("/{id}")
    public Account findById(@PathVariable int id){
        return accountService.findById(id);
    }
    @PostMapping("/")
    public Account save(@RequestBody Account account){
        return accountService.save(account);
    }

    @PutMapping("/{id}")
    public Account save(@PathVariable int id,@RequestBody Account account){
        Account account1=accountService.findById(id);
        if(account1!=null){
            account.setId(id);
            accountService.save(account);
        }
        return null;
    }
    @DeleteMapping("/{id}")
    public Account delete(@PathVariable int id){
         return accountService.delete(id);
    }

}
