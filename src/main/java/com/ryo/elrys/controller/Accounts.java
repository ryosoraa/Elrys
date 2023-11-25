package com.ryo.elrys.controller;

import com.ryo.elrys.model.AccountsModel;
import com.ryo.elrys.model.DataModel;
import com.ryo.elrys.payload.BodyResponse;
import com.ryo.elrys.service.interfaces.ResponseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class Accounts {

    @Autowired
    ResponseService responseService;

    @PostMapping("/register")
    public ResponseEntity<BodyResponse<Object>> register(@RequestBody AccountsModel accountsModel) throws Exception {
        BodyResponse<Object> response = responseService.register(accountsModel);
        if (response.getStatus().equals("Success")){
            return ResponseEntity.ok()
                    .body(response);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);
    }

    @PostMapping("/login")
    public ResponseEntity<BodyResponse<Object>> login(@RequestBody AccountsModel accountsModel) throws Exception {
        BodyResponse<Object> response = responseService.login(accountsModel);
        if (response.getStatus().equals("Success")){
            return ResponseEntity.ok()
                    .body(response);
        }
        return ResponseEntity.status(404)
                .body(response);
    }

    @GetMapping("/getInfo")
    public ResponseEntity<BodyResponse<Object>> getInfo(@RequestParam String email) throws Exception {
        BodyResponse<Object> response = responseService.getInfo(email);
        if (response.getStatus().equals("Success")){
            return ResponseEntity.ok()
                    .body(response);
        }
        return ResponseEntity.status(404)
                .body(response);

    }

    @PutMapping("/update")
    public ResponseEntity<BodyResponse<Object>> update(@RequestBody DataModel dataModel) throws Exception {
        BodyResponse<Object> response = responseService.update(dataModel);
        if (response.getStatus().equals("Success")){
            return ResponseEntity.ok()
                    .body(response);
        }
        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                .body(response);

    }

    @DeleteMapping("/delete")
    public ResponseEntity<BodyResponse<Object>> delete(@RequestBody AccountsModel accountsModel) throws Exception {
        BodyResponse<Object> response = responseService.delete(accountsModel);
        if (response.getStatus().equals("Success")){
            return ResponseEntity.ok()
                    .body(response);
        }
        return ResponseEntity.status(404)
                .body(response);

    }

    @GetMapping("/pass")
    public ResponseEntity<BodyResponse<Object>> changePass(
            @RequestParam String email,
            @RequestParam String oldPass,
            @RequestParam String newPass
    ) throws Exception {
        BodyResponse<Object> response = responseService.changePass(email, oldPass, newPass);
        if (response.getStatus().equals("Success")){
            return ResponseEntity.ok()
                    .body(response);
        }
        return ResponseEntity.status(404)
                .body(response);

    }

}
