package com.ryo.elrys.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientHandlerException;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.UniformInterfaceException;
import com.sun.jersey.api.client.WebResource;

import javax.ws.rs.core.MediaType;

public class RequestApi {
    ApiClient client = new ApiClient();

    // FIND BY ID
    public JsonNode findById(String bodyUrl) throws JsonProcessingException {
//        System.out.println( client.postRequest(bodyUrl).toPrettyString());
        return client.getRequest(bodyUrl);
    }

    // REGISTER
    public JsonNode register(String bodyUrl, Object request) throws JsonProcessingException {
        return client.postRequest(bodyUrl, request);
    }

    // LOGIN
    public JsonNode login(String bodyUrl, Object request) throws JsonProcessingException {
        return client.postRequest(bodyUrl, request);
    }

    public JsonNode findByEmail(String bodyUrl, String request) throws JsonProcessingException {
        return client.findRequest(bodyUrl, request);
}

    public JsonNode delete(String bodyUrl) throws JsonProcessingException {
        return client.deleteRequest(bodyUrl);
    }

    public void delete(String bodyUrl, String request) throws JsonProcessingException {
        client.deleteRequest(bodyUrl, request);
    }

}

/**
 * ApiClient
 */
class ApiClient {
    private final ObjectMapper objectMapper = new ObjectMapper();
    private final Client client = new Client();

    /**
     * Mengirim Request HTTP POST dengan data JsonString dan mengembalikan hasilnya sebagai JsonNode.
     *
     * @param bodyUrl URL tujuan Request
     * @param request Objek yang akan dikirim sebagai payload JSON
     * @return JsonNode response dari Request
     */
    public JsonNode postRequest(String bodyUrl, Object request) throws JsonProcessingException, UniformInterfaceException, ClientHandlerException {
        WebResource webResource = client.resource(bodyUrl);
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, objectMapper.writeValueAsString(request));

        String responds = response.getEntity(String.class);
        return objectMapper.readValue(responds, JsonNode.class);
    }


    /**
     * Mengirim Request HTTP POST tanpa payload dan mengembalikan hasilnya sebagai JSON.
     *
     * @param bodyUrl URL tujuan Request
     * @return JsonNode response dari Request
     */
    public JsonNode postRequest(String bodyUrl) throws JsonProcessingException {
        WebResource webResource = client.resource(bodyUrl);
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class);

        String responds = response.getEntity(String.class);
        return objectMapper.readValue(responds, JsonNode.class);
    }


    /**
     * Mengirim Request HTTP GET tanpa payload dan mengembalikan hasilnya sebagai JSON.
     *
     * @param bodyUrl URL tujuan Request
     * @return JsonNode response dari Request
     */
    public JsonNode getRequest(String bodyUrl) throws JsonProcessingException {
        WebResource webResource = client.resource(bodyUrl);
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        String responds = response.getEntity(String.class);
        return objectMapper.readValue(responds, JsonNode.class);
    }


    /**
     * Melakukan pencarian data dengan mengirim Request HTTP POST
     * dengan data JsonString dan mengembalikan hasilnya sebagai JsonNode.
     *
     * @param bodyUrl URL tujuan Request
     * @param request Objek yang akan dikirim sebagai payload JSON
     * @return JsonNode response dari Request
     */
    public JsonNode findRequest(String bodyUrl, String request) throws JsonProcessingException {
        WebResource webResource = client.resource(bodyUrl);
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, request);

        String responds = response.getEntity(String.class);
        return objectMapper.readValue(responds, JsonNode.class);
    }


    /**
     * Menghapus Data dengan Mengirim Request HTTP DELETE
     * tanpa payload dan mengembalikan hasilnya sebagai JSON.
     *
     * @param bodyUrl URL tujuan Request
     * @return JsonNode response dari Request
     */
    public JsonNode deleteRequest(String bodyUrl) throws JsonProcessingException, UniformInterfaceException, ClientHandlerException {
        WebResource webResource = client.resource(bodyUrl);
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                .delete(ClientResponse.class);

        String responds = response.getEntity(String.class);
        return objectMapper.readValue(responds, JsonNode.class);
    }

    /**
     * Overloading
     * Menghapus Data dengan Mengirim Request HTTP DELETE
     * tanpa payload dan mengembalikan hasilnya sebagai JSON.
     *
     * @param bodyUrl URL tujuan Request
     * @param request Objek yang akan dikirim sebagai payload JSON
     */
    public void deleteRequest(String bodyUrl, String request) throws JsonProcessingException, UniformInterfaceException, ClientHandlerException {
        WebResource webResource = client.resource(bodyUrl);
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, request);

        String responds = response.getEntity(String.class);
        objectMapper.readValue(responds, JsonNode.class);
    }
}
