package com.ryo.elrys.utils;

public class Query {
    public static String SearchByEmailAndPass(String email, String password){

        return String.format("""
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
                        """, email, password);
    }

    public static String SearchByEmail(String email){

        return String.format("""
                {
                  "size": 0,
                  "query": {
                    "wildcard": {
                      "email.keyword": {
                        "value": "%s"
                      }
                    }
                  }
                }
                """, email);
    }

    public static String UpdatePass(String email, String oldPassword, String newPassword){
        return String.format("""
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
    }

    public static String search(String type, String value){
        return String.format("""
                {
                  "query": {
                    "match": {
                      "%s": "%s"
                    }
                  }
                }
                """, type, value);
    }
    
    public static String categorizText(String type){
        return String.format("""
                {
                    "size":0,
                    "aggs":{
                        "text_categories":{
                            "categorize_text": {
                                "field": "%s"
                            }
                        }
                    }
                }
                """, type);
    }
}
