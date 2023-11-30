package com.ryo.elrys.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Exception yang dilempar ketika pengguna tidak ditemukan.
 */
@ResponseStatus(HttpStatus.NOT_FOUND)
public class UserNotFoundException extends RuntimeException {

    /**
     * Konstruktor untuk membuat objek Exception dengan pesan tertentu.
     *
     * @param message Pesan Exception yang akan ditampilkan.
     */
    public UserNotFoundException(String message) {
        super(message);
    }
}
