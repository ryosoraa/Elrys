package com.ryo.elrys.api;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.ryo.elrys.model.DTO.LoginDTO;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

public class RequestApi {

    ObjectMapper objectMapper = new ObjectMapper();

    private final Client client = new Client();

    // FIND BY ID
    public JsonNode findById(String url) throws JsonProcessingException {
        WebResource webResource = client.resource(url);
        System.out.println(url);

        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        String responds = response.getEntity(String.class);
        return objectMapper.readValue(responds, JsonNode.class);
    }

    // REGISTER
    public JsonNode register(String url, HashMap<String, Object> regisetrMap) throws JsonProcessingException {
        WebResource webResource = client.resource(url);

        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, objectMapper.writeValueAsString(regisetrMap));

        String responds = response.getEntity(String.class);
        return objectMapper.readValue(responds, JsonNode.class);

        /*return response.getEntity(JsonNode.class);*/
    }

    // LOGIN
    public JsonNode login(String bodyUrl, HashMap<String, Object> loginMap) throws JsonProcessingException {
        WebResource webResource = client.resource(bodyUrl);

        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, objectMapper.writeValueAsString(loginMap));

        String responds = response.getEntity(String.class);
        return objectMapper.readValue(responds, JsonNode.class);
    }

    public JsonNode findByEmail(String bodyUrl, String bodyRequest) throws JsonProcessingException {
        WebResource webResource = client.resource(bodyUrl);

        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, bodyRequest);

        String responds = response.getEntity(String.class);
        return objectMapper.readValue(responds, JsonNode.class);
    }

    public JsonNode update(String url, HashMap<String, Object> regisetrMap) throws JsonProcessingException {
        WebResource webResource = client.resource(url);

        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, objectMapper.writeValueAsString(regisetrMap));

        String responds = response.getEntity(String.class);
        return objectMapper.readValue(responds, JsonNode.class);

        /*return response.getEntity(JsonNode.class);*/
    }

}
