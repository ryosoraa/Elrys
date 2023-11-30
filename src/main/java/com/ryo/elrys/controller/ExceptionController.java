package com.ryo.elrys.controller;

import com.ryo.elrys.payload.BodyResponse;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;

import jakarta.servlet.http.HttpServletRequest;

/**
 * Controller advice untuk menangani semua pengecualian (exceptions) di seluruh
 * aplikasi.
 */
@ControllerAdvice
@RequestMapping("/api")
public class ExceptionController {

    /**
     * Method untuk menangani semua jenis pengecualian (Throwable)
     * 
     * @param exception objek Throwable yang terjadi
     * @param request   objek HttpServletRequest saat terjadi exception
     * @return objek ResponseEntity yang berisi status error dan pesan exception
     */
    @ExceptionHandler(Throwable.class)
    public ResponseEntity<BodyResponse<Object>> common(Throwable exception, HttpServletRequest request) {
        HttpStatus status = exception.getClass().getAnnotation(ResponseStatus.class).value();

        return ResponseEntity.status(status).body(BodyResponse.builder()
                .status("Failed")
                .message(exception.getMessage())
                .build());
    }
}