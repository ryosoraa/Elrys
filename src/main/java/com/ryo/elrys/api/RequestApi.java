package com.ryo.elrys.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

public class RequestApi {

    ObjectMapper objectMapper = new ObjectMapper();

    private final Client client = new Client();

    ApiClient apiClient = new ApiClient();

    // FIND BY ID
    public JsonNode findById(String url) throws JsonProcessingException {
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        String responds = response.getEntity(String.class);
        return objectMapper.readValue(responds, JsonNode.class);
    }

    // REGISTER
    public JsonNode register(String url, Object map) throws JsonProcessingException {
        return apiClient.postRequest(url, map);
    }

    // LOGIN
    public JsonNode login(String bodyUrl, Object map) throws JsonProcessingException {
        WebResource webResource = client.resource(bodyUrl);
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, objectMapper.writeValueAsString(map));

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

    public JsonNode delete(String url) throws JsonProcessingException {
        WebResource webResource = client.resource(url);
        System.out.println(url);
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                .delete(ClientResponse.class);

        String responds = response.getEntity(String.class);
        return objectMapper.readValue(responds, JsonNode.class);
    }

}

/**
 * InnerRequestApi
 */
class ApiClient {
    ObjectMapper objectMapper = new ObjectMapper();
    private final Client client = new Client();

    public JsonNode postRequest(String url, Object map)
            throws JsonProcessingException, UniformInterfaceException, ClientHandlerException {
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, objectMapper.writeValueAsString(map));

        String responds = response.getEntity(String.class);
        return objectMapper.readValue(responds, JsonNode.class);
    }

    public JsonNode postRequest(String url) throws JsonMappingException, JsonProcessingException {
        WebResource webResource = client.resource(url);
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class);

        String responds = response.getEntity(String.class);
        return objectMapper.readValue(responds, JsonNode.class);
    }
}
