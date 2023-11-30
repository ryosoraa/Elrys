package com.ryo.elrys.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception yang dilempar ketika mencoba membuat pengguna yang sudah ada.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class UserAlreadyExistsException extends RuntimeException {

    /**
     * Konstruktor untuk membuat objek Exception dengan pesan tertentu.
     *
     * @param message Pesan Exception yang akan ditampilkan.
     */
    public UserAlreadyExistsException(String message) {
        super(message);
    }
}
