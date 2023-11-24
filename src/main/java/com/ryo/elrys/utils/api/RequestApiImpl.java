package com.ryo.elrys.utils.api;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryo.elrys.enums.Options;
import com.ryo.elrys.utils.api.RequestApi;
import com.sun.jersey.api.client.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.ws.rs.core.MediaType;

@Service
public class RequestApiImpl implements RequestApi {

    @Autowired
    JerseyApiClient client;

    @Override
    public JsonNode findById(String bodyUrl) throws Exception {
        return client.getRequest(bodyUrl);
    }

    @Override
    public JsonNode register(String bodyUrl, String request) throws Exception {
        return client.postRequest(bodyUrl, request);
    }

    @Override
    public JsonNode login(String bodyUrl, String request) throws Exception {
        return client.postRequest(bodyUrl, request);
    }

    @Override
    public JsonNode findByEmail(String bodyUrl, String request) throws Exception {
        return client.postRequest(bodyUrl, request);
    }

    @Override
    public JsonNode delete(String bodyUrl, String request) throws Exception {
        return client.deleteRequest(bodyUrl, request);
    }

    @Override
    public JsonNode findByRequest(String bodyUrl, String request) throws Exception {
        return client.postRequest(bodyUrl, request);
    }


}

@Service
class JerseyApiClientImpl implements RequestApi.JerseyApiClient {

    @Autowired
    Client client;

    @Autowired
    ObjectMapper mapper;

    /**
     * Mengirim Request HTTP POST dengan data JsonString dan mengembalikan hasilnya sebagai JsonNode.
     *
     * @param bodyUrl URL tujuan Request
     * @param request Objek yang akan dikirim sebagai payload JSON
     * @return JsonNode response dari Request
     */
    @Override
    public JsonNode postRequest(String bodyUrl, String request) throws JsonProcessingException, UniformInterfaceException, ClientHandlerException {
        WebResource webResource = client.resource(bodyUrl);
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                .post(ClientResponse.class, request);

        String responds = response.getEntity(String.class);
        return mapper.readValue(responds, JsonNode.class);
    }

    /**
     * Mengirim Request HTTP GET tanpa payload dan mengembalikan hasilnya sebagai JSON.
     *
     * @param bodyUrl URL tujuan Request
     * @return JsonNode response dari Request
     */
    @Override
    public JsonNode getRequest(String bodyUrl) throws JsonProcessingException {
        WebResource webResource = client.resource(bodyUrl);
        ClientResponse response = webResource.type(MediaType.APPLICATION_JSON)
                .get(ClientResponse.class);

        String responds = response.getEntity(String.class);
        return mapper.readValue(responds, JsonNode.class);
    }

    /**
     * Overloading
     * Menghapus Data dengan Mengirim Request HTTP DELETE
     * tanpa payload dan mengembalikan hasilnya sebagai JSON.
     *
     * @param bodyUrl URL tujuan Request
     * @param request Objek yang akan dikirim sebagai payload JSON
     */
    @Override
    public JsonNode deleteRequest(String bodyUrl, String request) throws JsonProcessingException, UniformInterfaceException, ClientHandlerException {
        WebResource webResource = client.resource(bodyUrl);
        ClientResponse response;

        if (request.equals(Options.WITHOUT_REQUEST.getOptions())){
            response = webResource.type(MediaType.APPLICATION_JSON)
                    .delete(ClientResponse.class);
        } else {
            response = webResource.type(MediaType.APPLICATION_JSON)
                    .post(ClientResponse.class, request);
        }

        String responds = response.getEntity(String.class);
        return mapper.readValue(responds, JsonNode.class);
    }

}
