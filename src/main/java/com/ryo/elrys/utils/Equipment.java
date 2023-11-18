package com.ryo.elrys.utils;

import com.ryo.elrys.model.DTO.AccountsDTO;
import com.ryo.elrys.model.DTO.DeleteDTO;
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

    public String idEncoder(DeleteDTO deleteDTO) {
        String dataToEncode = deleteDTO.getEmail() + deleteDTO.getPassword();
        return DigestUtils.md5Hex(dataToEncode);
    }

    public String idEncoder(AccountsDTO accountsDTO) {
        String dataToEncode = accountsDTO.getEmail() + accountsDTO.getPassword();
        return DigestUtils.md5Hex(dataToEncode);
    }

}
