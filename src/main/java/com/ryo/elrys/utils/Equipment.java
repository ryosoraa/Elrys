package com.ryo.elrys.utils;

import org.apache.commons.codec.digest.DigestUtils;

import com.ryo.elrys.model.DTO.RegisterDTO;


public class Equipment {

    public String idEncoder(RegisterDTO registerDTO) {
        String dataToEncode = registerDTO.getEmail() + registerDTO.getPassword();
        return DigestUtils.md5Hex(dataToEncode);
    }
}
