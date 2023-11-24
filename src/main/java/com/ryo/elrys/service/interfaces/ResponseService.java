package com.ryo.elrys.service.interfaces;

import com.ryo.elrys.model.AccountsModel;
import com.ryo.elrys.model.DataModel;
import com.ryo.elrys.payload.BodyResponse;

public interface ResponseService {

    BodyResponse<Object> register(AccountsModel accountsModel) throws Exception;

    BodyResponse<Object> login(AccountsModel accountsModel) throws Exception;

    BodyResponse<Object> findByEmail(String email) throws Exception;

    BodyResponse<Object> update(DataModel dataModel) throws Exception;

    BodyResponse<Object> delete(AccountsModel accountsModel) throws Exception;

}