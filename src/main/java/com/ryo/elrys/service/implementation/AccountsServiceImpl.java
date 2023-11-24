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
    public Object getInfo(String email) throws Exception {

        String request = String.format("""
                {
                  "query": {
                    "wildcard": {
                      "email.keyword": {
                        "value": "%s"
                      }
                    }
                  }
                }
                """, email);

        JsonNode response = requestApi.getInfo(BodyUrl.MAIN_SEARCH.getUrl(), request);
        if (!String.valueOf(response.at("/hits/hits/0/_source/email").asText()).equals(email)) {
            return "not found";
        }

        return response;
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


        JsonNode response = requestApi.findByRequest(BodyUrl.MAIN_SEARCH.getUrl(), request);

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
        String mainRequest = String.format("""
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

        String logRequest = String.format("""
                {
                  "query":{
                    "term":{
                      "email.keyword":"%s"
                    }
                  }
                }
                """, accountsModel.getEmail());

        JsonNode response = requestApi.delete(BodyUrl.MAIN_DELETE_QUERY.getUrl(), mainRequest);
        System.out.println(response.toPrettyString());

        if(String.valueOf(response.at("/deleted")).equals("0")){
            System.out.println("User Not Found");
            return "User Not Found";
        }
        return requestApi.delete(BodyUrl.LOG_DELETE_QUERY.getUrl(), logRequest);
    }


    @Override
    public Object changePassword(String email, String oldPassword, String newPassword) throws Exception {

        String request = String.format("""
                {
                  "query": {
                    "bool": {
                      "must": [
                        {
                          "term": {
                            "email.keyword": {
                              "value": "%s"
                            }
                          }
                        },
                        {
                          "term": {
                            "password.keyword": {
                              "value": "%s"
                            }
                          }
                        }
                      ]
                    }
                  },
                  "script": {
                    "source": "ctx._source.password = params.newPassword",
                    "lang": "painless",
                    "params": {
                      "newPassword": "%s"
                    }
                  }
                }
                """, email, oldPassword, newPassword);

        JsonNode response = requestApi.changePassword(BodyUrl.MAIN_UPDATE_QUERY.getUrl(), request);
        System.out.println(response.toPrettyString());
        return null;
    }

}


