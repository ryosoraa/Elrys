package com.ryo.elrys.service.implementation;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryo.elrys.enums.BodyUrl;
import com.ryo.elrys.exceptions.UserNotFoundException;
import com.ryo.elrys.model.ProductModel;
import com.ryo.elrys.payload.BodyResponse;
import com.ryo.elrys.payload.DataResponse;
import com.ryo.elrys.service.interfaces.ProductService;
import com.ryo.elrys.utils.Equipment;
import com.ryo.elrys.utils.Query;
import com.ryo.elrys.utils.api.RequestApi;
import io.swagger.v3.oas.annotations.servers.Server;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductServiceImpl implements ProductService {

    @Autowired
    RequestApi requestApi;

    @Autowired
    ObjectMapper mapper;

    @Autowired
    Equipment equipment;

    @Override
    public BodyResponse<JsonNode> addProduct(ProductModel productModel) throws Exception{
        System.out.println(productModel.getEmail());
        System.out.println(requestApi.findByRequest(BodyUrl.MAIN_SEARCH.getUrl(), Query.SearchByEmail(productModel.getEmail())).toPrettyString());
        if(!String.valueOf(requestApi.findByRequest(BodyUrl.MAIN_SEARCH.getUrl(), Query.SearchByEmail(productModel.getEmail())).at("/hits/total/value")).equals("1")){
            throw new UserNotFoundException("User Not Found");
        }

        return BodyResponse.<JsonNode>builder()
                .status("Success")
                .data(requestApi.addProduct(BodyUrl.PRODUCT_DOC.getUrl(), mapper.writeValueAsString(productModel)))
                .message("Add Product Success")
                .build();

    }
}
