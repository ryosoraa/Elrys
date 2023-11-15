package com.ryo.elrys.controller;

import com.ryo.elrys.model.DTO.AccountsDTO;
import com.ryo.elrys.service.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class Accounts {

    @Autowired
    AccountsService accountsService;

    @PostMapping("/register")
    public ResponseEntity<String> register (AccountsDTO accountsDTO){
        accountsService.register(accountsDTO);
        return ResponseEntity.ok("ok");
    }

}
