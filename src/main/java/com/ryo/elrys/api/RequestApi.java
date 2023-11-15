package com.ryo.elrys.api;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

public class RequestApi {

    ObjectMapper objectMapper = new ObjectMapper();

    private Client client = new Client();

    public JsonNode findById(String url) throws JsonProcessingException {
        WebResource webResource = client.resource(url);

        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class);

        String respons = response.getEntity(String.class);
        return objectMapper.readValue(respons, JsonNode.class);
    }

    public JsonNode register(String url, HashMap<String, Object> jsonMap) throws JsonProcessingException {
        WebResource webResource = client.resource(url);

        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, objectMapper.writeValueAsString(jsonMap));

        String respons = response.getEntity(String.class);
        return objectMapper.readValue(respons, JsonNode.class);
    }

}
