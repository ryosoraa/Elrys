package com.ryo.elrys.utils;

import com.ryo.elrys.model.AccountsModel;
import org.apache.commons.codec.digest.DigestUtils;
import com.ryo.elrys.model.DataModel;

public class Equipment {

    public String idEncoder(AccountsModel accountsModel) {
        String dataToEncode = accountsModel.getEmail() + accountsModel.getPassword();
        return DigestUtils.md5Hex(dataToEncode);
    }

    public String idEncoder(DataModel dataModel) {
        String dataToEncode = dataModel.getEmail() + dataModel.getPassword();
        return DigestUtils.md5Hex(dataToEncode);
    }

}
