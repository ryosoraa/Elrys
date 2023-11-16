package com.ryo.elrys.controller;

import com.ryo.elrys.model.DTO.LoginDTO;
import com.ryo.elrys.model.DTO.RegisterDTO;
import com.ryo.elrys.response.BodyResponse;
import com.ryo.elrys.service.interfaces.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Accounts {

    @Autowired
    AccountsService accountsService;

    @PostMapping("/register")
    public ResponseEntity<BodyResponse<Object>> register(RegisterDTO registerDTO) throws Exception {
        return ResponseEntity.ok()
                .body(accountsService.register(registerDTO));
    }

    @PostMapping("/login")
    public ResponseEntity<BodyResponse<Object>> login(LoginDTO loginDTO) throws Exception {
        return ResponseEntity.ok()
                .body(accountsService.login(loginDTO));
    }

    @GetMapping("/byEmail")
    public ResponseEntity<BodyResponse<Object>> findByEmail(@RequestParam String email) throws Exception {
        return ResponseEntity.ok()
                .body(accountsService.findByEmail(email));
    }

    @PutMapping
    public ResponseEntity<BodyResponse<Object>> update(@RequestBody RegisterDTO registerDTO) throws Exception {
        return ResponseEntity.ok()
                .body(accountsService.update(registerDTO));
    }

}
