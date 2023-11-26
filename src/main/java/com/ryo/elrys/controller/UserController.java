package com.ryo.elrys.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.ryo.elrys.model.AccountsModel;
import com.ryo.elrys.model.DataModel;
import com.ryo.elrys.payload.BodyResponse;
import com.ryo.elrys.payload.DataResponse;
import com.ryo.elrys.service.interfaces.UserService;
import io.swagger.annotations.Api;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api")
@Api(value = "User", tags = "User-Controller")
public class UserController {

    @Autowired
    UserService userService;

    @PostMapping("/register")
    public ResponseEntity<BodyResponse<DataResponse>> register(@RequestBody AccountsModel accountsModel) throws Exception {
        return ResponseEntity.ok()
                .body(userService.register(accountsModel));
    }

    @PostMapping("/login")
    public ResponseEntity<BodyResponse<DataResponse>> login(@RequestBody AccountsModel accountsModel, HttpServletRequest requestHeader) throws Exception {
            return ResponseEntity.ok()
                    .body(userService.login(accountsModel, requestHeader));

    }

    @GetMapping("/getInfo")
    public ResponseEntity<BodyResponse<JsonNode>> getInfo(@RequestParam String email) throws Exception {
            return ResponseEntity.ok()
                    .body(userService.getInfo(email));

    }

    @PutMapping("/update")
    public ResponseEntity<BodyResponse<JsonNode>> update(@RequestBody DataModel dataModel) throws Exception {
            return ResponseEntity.ok()
                    .body(userService.update(dataModel));

    }

    @DeleteMapping("/delete")
    public ResponseEntity<BodyResponse<JsonNode>> delete(@RequestBody AccountsModel accountsModel) throws Exception {
            return ResponseEntity.ok()
                    .body(userService.delete(accountsModel));

    }

    @GetMapping("/pass")
    public ResponseEntity<BodyResponse<JsonNode>> changePass(
            @RequestParam String email,
            @RequestParam String oldPass,
            @RequestParam String newPass
    ) throws Exception {
            return ResponseEntity.ok()
                    .body(userService.changePassword(email, oldPass, newPass));

    }

    @PutMapping("/getLog")
    public ResponseEntity<BodyResponse<JsonNode>> getLog(@RequestBody DataModel dataModel) throws Exception {
            return ResponseEntity.ok()
                    .body(userService.update(dataModel));

    }

}
