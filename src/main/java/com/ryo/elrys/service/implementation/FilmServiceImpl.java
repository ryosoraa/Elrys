package com.ryo.elrys.service.implementation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryo.elrys.enums.BodyUrl;
import com.ryo.elrys.exceptions.UserNotFoundException;
import com.ryo.elrys.model.FilmtModel;
import com.ryo.elrys.payload.BodyResponse;
import com.ryo.elrys.service.interfaces.FilmService;
import com.ryo.elrys.utils.Equipment;
import com.ryo.elrys.utils.Query;
import com.ryo.elrys.utils.api.RequestApi;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FilmServiceImpl implements FilmService {

    @Autowired
    RequestApi requestApi;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    Equipment equipment;

    @Override
    public BodyResponse<JsonNode> addProduct(FilmtModel filmtModel) throws Exception {
        System.out.println(filmtModel.getEmail());
        System.out.println(
                requestApi.findByRequest(BodyUrl.MAIN_SEARCH.getUrl(), Query.SearchByEmail(filmtModel.getEmail()))
                        .toPrettyString());
        if (!String.valueOf(
                requestApi.findByRequest(BodyUrl.MAIN_SEARCH.getUrl(), Query.SearchByEmail(filmtModel.getEmail()))
                        .at("/hits/total/value"))
                .equals("1")) {
            throw new UserNotFoundException("User Not Found");
        }

        return BodyResponse.<JsonNode>builder()
                .status("Success")
                .data(requestApi.addFilm(BodyUrl.FILM_DOC.getUrl(), mapper.writeValueAsString(filmtModel)))
                .message("Add Product Success")
                .build();

    }

    @Override
    public BodyResponse<JsonNode> search(String type, String value) throws Exception {
        JsonNode response = requestApi.searchFilm(BodyUrl.FILM_SEARCH.getUrl(), Query.search(type, value));

        if (String.valueOf(response.at("/hits/total/value")).equals("0")) {
            throw new UserNotFoundException("Request Not Found");
        }

        return BodyResponse.<JsonNode>builder()
                .status("Success")
                .data(response.path("hits").path("hits"))
                .message("Data Founded")
                .build();
    }

    @Override
    public BodyResponse<JsonNode> analyst(String type) throws Exception {
        JsonNode response = requestApi.analyst(BodyUrl.FILM_SEARCH.getUrl(), Query.categorizText(type));

        if (String.valueOf(response.at("/hits/total/value")).equals("0")) {
            throw new UserNotFoundException("Request Not Found");
        }

        return BodyResponse.<JsonNode>builder()
                .status("Success")
                .data(response.path("aggregations").path("text_categories").path("buckets"))
                .message("Data Founded")
                .build();
    }
}
