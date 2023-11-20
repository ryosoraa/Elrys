package com.ryo.elrys.controller;

import com.ryo.elrys.model.DTO.AccountsDTO;
import com.ryo.elrys.model.DTO.UserDTO;
import com.ryo.elrys.response.BodyResponse;
import com.ryo.elrys.service.interfaces.AccountsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Accounts {

    @Autowired
    AccountsService accountsService;

    @PostMapping("/register")
    public ResponseEntity<BodyResponse<Object>> register(@RequestBody AccountsDTO accountsDTO) throws Exception {
        BodyResponse<Object> response = accountsService.register(accountsDTO);
        if (response.getStatus().equals("Success")){
            return ResponseEntity.ok()
                    .body(response);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<BodyResponse<Object>> login(@RequestBody AccountsDTO accountsDTO) throws Exception {
        BodyResponse<Object> response = accountsService.login(accountsDTO);
        if (response.getStatus().equals("Success")){
            return ResponseEntity.ok()
                    .body(response);
        }
        return ResponseEntity.status(404)
                .body(response);
    }

    @GetMapping("/findByEmail")
    public ResponseEntity<BodyResponse<Object>> findByEmail(@RequestParam String email) throws Exception {
        BodyResponse<Object> response = accountsService.findByEmail(email);
        if (response.getStatus().equals("Success")){
            return ResponseEntity.ok()
                    .body(response);
        }
        return ResponseEntity.status(404)
                .body(response);

    }

    @PutMapping("/update")
    public ResponseEntity<BodyResponse<Object>> update(@RequestBody UserDTO userDTO) throws Exception {
        BodyResponse<Object> response = accountsService.update(userDTO);
        if (response.getStatus().equals("Success")){
            return ResponseEntity.ok()
                    .body(response);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<BodyResponse<Object>> delete(@RequestBody AccountsDTO accountsDTO) throws Exception {
        BodyResponse<Object> response = accountsService.delete(accountsDTO);
        if (response.getStatus().equals("Success")){
            return ResponseEntity.ok()
                    .body(response);
        }
        return ResponseEntity.status(404)
                .body(response);

    }

}
