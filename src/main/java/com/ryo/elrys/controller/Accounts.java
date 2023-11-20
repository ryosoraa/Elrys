package com.ryo.elrys.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryo.elrys.model.DTO.AccountsDTO;
import com.ryo.elrys.model.DTO.UpdateDTO;
import com.ryo.elrys.response.BodyResponse;
import com.ryo.elrys.service.interfaces.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
@RequestMapping("/api")
public class Accounts {

    @Autowired
    AccountsService accountsService;

    @PostMapping("/register")
    public ResponseEntity<BodyResponse<Object>> register(@RequestBody AccountsDTO accountsDTO) throws Exception {
        return ResponseEntity.ok()
                .body(accountsService.register(accountsDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<BodyResponse<Object>> login(@RequestBody AccountsDTO accountsDTO) throws Exception {
        return ResponseEntity.ok()
                .body(accountsService.login(accountsDTO));
    }

    @GetMapping("/byEmail")
    public ResponseEntity<BodyResponse<Object>> findByEmail(@RequestParam String email) throws Exception {
        return ResponseEntity.ok()
                .body(accountsService.findByEmail(email));
    }

    @PutMapping("/update")
    public ResponseEntity<BodyResponse<Object>> update(@RequestBody UpdateDTO updateDTO) throws Exception {
        return ResponseEntity.ok()
                .body(accountsService.update(updateDTO));
    }

    @DeleteMapping("/delete")
    public ResponseEntity<BodyResponse<Object>> delete(@RequestBody AccountsDTO accountsDTO) throws Exception {
        return ResponseEntity.ok()
                .body(accountsService.delete(accountsDTO));
    }

}
