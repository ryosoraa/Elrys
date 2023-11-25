package com.ryo.elrys.service.implementation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryo.elrys.enums.BodyUrl;
import com.ryo.elrys.model.AccountsModel;
import com.ryo.elrys.model.DataModel;
import com.ryo.elrys.model.LoginModel;
import com.ryo.elrys.service.interfaces.AccountsService;
import com.ryo.elrys.utils.Equipment;

import com.ryo.elrys.utils.Query;
import com.ryo.elrys.utils.api.RequestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

@Repository
public class AccountsServiceImpl implements AccountsService {

    @Autowired
    Equipment equipment;

    @Autowired
    RequestApi requestApi;

    @Autowired
    ObjectMapper mapper;

    // REGISTER
    @Override
    public Object register(AccountsModel accountsModel) throws Exception {
        JsonNode register = requestApi.register(BodyUrl.MAIN_CREATED.getUrl().concat(equipment.idEncoder(accountsModel.getEmail())), mapper.writeValueAsString(new DataModel(accountsModel)));

        if(register.has("error")){
            System.out.println("error");
            return "Accounts already exists";
        }

        return register;
    }

    // LOGIN
    @Override
    public Object login(AccountsModel accountsModel) throws Exception {

        JsonNode responds = requestApi.findByRequest(BodyUrl.MAIN_SEARCH.getUrl(), Query.SearchByEmailAndPass(accountsModel.getEmail(), accountsModel.getPassword()));

        if(!String.valueOf(responds.at("/hits/total/value")).equals("1")){
            System.out.println("user not found");
            return "User not found";
        }

        requestApi.login(BodyUrl.LOG_DOC.getUrl(), mapper.writeValueAsString(new LoginModel(accountsModel)));

        return responds;

    }

    // Find By Email
    @Override
    public Object getInfo(String email) throws Exception {

        JsonNode response = requestApi.getInfo(BodyUrl.MAIN_SEARCH.getUrl(), Query.SearchByEmail(email));
        if (!String.valueOf(response.at("/hits/hits/0/_source/email").asText()).equals(email)) {
            return "not found";
        }

        return response;
    }

    // Update
    @Override
    public Object update(DataModel dataModel) throws Exception {

        JsonNode response = requestApi.findByRequest(BodyUrl.MAIN_SEARCH.getUrl(), Query.SearchByEmailAndPass(dataModel.getEmail(), dataModel.getPassword()));

        System.out.println(response.at("/hits/hits/0/_id").asText());
        if(!String.valueOf(response.at("/hits/total/value")).equals("1")){
            System.out.println("user not found");
            return "User not found";
        }

        return requestApi.update(BodyUrl.MAIN_DOC.getUrl().concat(response.at("/hits/hits/0/_id").asText()), mapper.writeValueAsString(dataModel));
    }

    // Delete
    @Override
    public Object delete(AccountsModel accountsModel) throws Exception {

        JsonNode response = requestApi.delete(BodyUrl.MAIN_DELETE_QUERY.getUrl(), Query.SearchByEmailAndPass(accountsModel.getEmail(), accountsModel.getPassword()));
        System.out.println(response.toPrettyString());

        if(String.valueOf(response.at("/deleted")).equals("0")){
            System.out.println("User Not Found");
            return "User Not Found";
        }
        return requestApi.delete(BodyUrl.LOG_DELETE_QUERY.getUrl(), Query.SearchByEmail(accountsModel.getEmail()));
    }


    @Override
    public Object changePassword(String email, String oldPassword, String newPassword) throws Exception {

        JsonNode response = requestApi.changePassword(BodyUrl.MAIN_UPDATE_QUERY.getUrl(), Query.UpdatePass(email, oldPassword, newPassword));
        if(!String.valueOf(response.at("/total")).equals("1")){
            return "User Not Found";
        }
        return response;
    }

}


