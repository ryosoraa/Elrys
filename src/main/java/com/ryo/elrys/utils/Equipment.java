package com.ryo.elrys.utils;

import com.ryo.elrys.model.DTO.LoginDTO;
import org.apache.commons.codec.digest.DigestUtils;

import com.ryo.elrys.model.DTO.RegisterDTO;


public class Equipment {

    public String idEncoder(RegisterDTO registerDTO) {
        String dataToEncode = registerDTO.getEmail() + registerDTO.getPassword();
        return DigestUtils.md5Hex(dataToEncode);
    }

    public String idEncoder(LoginDTO loginDTO) {
        String dataToEncode = loginDTO.getEmail() + loginDTO.getPassword();
        return DigestUtils.md5Hex(dataToEncode);
    }

    public String loginEncode(LoginDTO loginDTO) {
        String dataToEncode = loginDTO.getEmail() + loginDTO.getPassword() + loginDTO.getDeviceModel() + loginDTO.getDeviceType();
        return DigestUtils.md5Hex(dataToEncode);
    }
}
