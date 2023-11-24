package com.ryo.elrys.service.implementation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryo.elrys.enums.BodyUrl;
import com.ryo.elrys.enums.Options;
import com.ryo.elrys.model.AccountsModel;
import com.ryo.elrys.model.DataModel;
import com.ryo.elrys.model.LoginModel;
import com.ryo.elrys.service.interfaces.AccountsService;
import com.ryo.elrys.utils.Equipment;

import com.ryo.elrys.utils.api.RequestApiImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.Random;

@Repository
public class AccountsServiceImpl implements AccountsService {

    @Autowired
    Equipment equipment;

    @Autowired
    RequestApiImpl requestApi;

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
        String request = String.format("""
                        {
                          "query": {
                            "bool": {
                              "must": [
                                {"term": {
                                  "email.keyword": {
                                    "value": "%s"
                                  }
                                }},
                                {
                                  "term": {
                                    "password.keyword": {
                                      "value": "%s"
                                    }
                                  }
                                }
                              ]
                            }
                          }
                        }
                        """, accountsModel.getEmail(), accountsModel.getPassword());

        JsonNode responds = requestApi.findByRequest(BodyUrl.MAIN_SEARCH.getUrl(), request);

        if(!String.valueOf(responds.at("/hits/total/value")).equals("1")){
            System.out.println("user not found");
            return "User not found";
        }

        requestApi.login(BodyUrl.LOG_DOC.getUrl(), mapper.writeValueAsString(new LoginModel(accountsModel)));

        return responds;

    }

    // Find By Email
    @Override
    public Object findByEmail(String email) throws Exception {

        String request = String.format("{\"query\": {\"wildcard\": {\"email.keyword\": \"%s\"}}}", email);

        JsonNode jsonNode = requestApi.findByEmail(BodyUrl.MAIN_SEARCH.getUrl(), request);
        if (!String.valueOf(jsonNode.at("/hits/hits/0/_source/email").asText()).equals(email)) {
            return "not found";
        }

        return jsonNode;
    }

    // Update
    @Override
    public Object update(DataModel dataModel) throws Exception {

        String request = String.format("""
                        {
                          "query": {
                            "bool": {
                              "must": [
                                {"term": {
                                  "email.keyword": {
                                    "value": "%s"
                                  }
                                }},
                                {
                                  "term": {
                                    "password.keyword": {
                                      "value": "%s"
                                    }
                                  }
                                }
                              ]
                            }
                          }
                        }
                        """, dataModel.getEmail(), dataModel.getPassword());


        JsonNode response = requestApi.findByEmail(BodyUrl.MAIN_SEARCH.getUrl(), request);

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
        String idEncode = equipment.idEncoder(accountsModel);
        String bodyUrl = BodyUrl.MAIN_SEARCH.getUrl().concat(idEncode);

        String request = String.format("{\"query\": {\"term\": {\"id.keyword\": \"%s\"}}}", idEncode);

        if (!requestApi.findById(BodyUrl.MAIN_DOC.getUrl().concat(idEncode)).get("found").asBoolean()) {
            return "Accounts not found";
        }
        requestApi.delete(BodyUrl.LOG_DELETE_QUERY.getUrl(), request);
        return requestApi.delete(bodyUrl, Options.WITHOUT_REQUEST.getOptions());
    }

    @Override
    public Object changePassword(String email, String oldPassword, String newPassowrd) throws Exception {

//        String request = String.format("{\"query\": {\"bool\": {\"must\": [{\"term\": {\"email.keyword\": {\"value\": \"%s\"}}}]}}}", dataModel.getEmail());
//        JsonNode response = requestApi.findByEmail(BodyUrl.MAIN_SEARCH.getUrl(), request);

//        if(!requestApi.findByEmail(BodyUrl.MAIN_SEARCH.getUrl(), request).at("/hits/total/value").toString().equals("1")){
//            return "Accounts not found";
//        }

//        return requestApi.register(BodyUrl.MAIN_DOC.getUrl().concat(response.at("/hits/hits/0/_source/uuid").asText()), mapper.writeValueAsString(dataModel));

    return null;
    }

}


