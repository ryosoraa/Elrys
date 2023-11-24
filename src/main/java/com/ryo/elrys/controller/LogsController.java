package com.ryo.elrys.controller;

import com.ryo.elrys.payload.BodyResponse;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/logs")
public class LogsController {

    public ResponseEntity<BodyResponse<Object>>getAll(){

        return null;
    }
}
