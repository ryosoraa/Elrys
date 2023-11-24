package com.ryo.elrys.utils.api;

import com.fasterxml.jackson.databind.JsonNode;

public interface RequestApi {
    JsonNode findById(String bodyUrl) throws Exception;
    JsonNode register(String bodyUrl, String request) throws Exception;
    JsonNode login(String bodyUrl, String request) throws Exception;
    JsonNode findByEmail(String bodyUrl, String request) throws Exception;
    JsonNode delete(String bodyUrl, String request) throws Exception;
    JsonNode findByRequest(String bodyUrl, String request) throws Exception;

    interface JerseyApiClient {
        JsonNode postRequest(String bodyUrl, String request) throws Exception;

        JsonNode getRequest(String bodyUrl) throws Exception;
        JsonNode deleteRequest(String bodyUrl, String request) throws Exception;
    }
}
