package com.ryo.elrys.utils;

import com.ryo.elrys.model.DTO.AccountsDTO;
import org.apache.commons.codec.digest.DigestUtils;
import com.ryo.elrys.model.DTO.UserDTO;

public class Equipment {

    public String idEncoder(AccountsDTO accountsDTO) {
        String dataToEncode = accountsDTO.getEmail() + accountsDTO.getPassword();
        return DigestUtils.md5Hex(dataToEncode);
    }

    public String idEncoder(UserDTO userDTO) {
        String dataToEncode = userDTO.getEmail() + userDTO.getPassword();
        return DigestUtils.md5Hex(dataToEncode);
    }

}
