package com.ryo.elrys.service.interfaces;

import com.ryo.elrys.model.AccountsModel;
import com.ryo.elrys.model.DataModel;

public interface AccountsService {
    Object register(AccountsModel accountsModel) throws Exception;
    Object login(AccountsModel accountsModel) throws Exception;
    Object findByEmail(String email) throws Exception;
    Object update(DataModel dataModel) throws Exception;
    Object delete(AccountsModel accountsModel) throws Exception;

//    Object changePassword(DataModel dataModel) throws Exception;

    Object changePassword(String email, String oldPassword, String newPassowrd) throws Exception;
}
