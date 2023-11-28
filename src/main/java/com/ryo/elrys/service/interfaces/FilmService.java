package com.ryo.elrys.service.interfaces;

import com.fasterxml.jackson.databind.JsonNode;
import com.ryo.elrys.model.FilmtModel;
import com.ryo.elrys.payload.BodyResponse;

public interface FilmService {

    BodyResponse<JsonNode> addProduct(FilmtModel filmtModel) throws Exception;

    BodyResponse<JsonNode> search(String type, String value) throws Exception;

    BodyResponse<JsonNode> analyst(String type) throws Exception;
}
