package com.ryo.elrys.service.interfaces;

import com.fasterxml.jackson.databind.JsonNode;
import com.ryo.elrys.model.ProductModel;
import com.ryo.elrys.payload.BodyResponse;

public interface ProductService {

    BodyResponse<JsonNode> addProduct(ProductModel productModel) throws Exception;
}
