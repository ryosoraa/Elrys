package com.ryo.elrys.controller;

import com.fasterxml.jackson.databind.JsonNode;
import com.ryo.elrys.model.ProductModel;
import com.ryo.elrys.payload.BodyResponse;
import com.ryo.elrys.service.interfaces.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/v1")
public class ProductController {

    @Autowired
    ProductService productService;

    @PostMapping("/addProduct")
    public ResponseEntity<BodyResponse<JsonNode>> addProduct(@RequestBody ProductModel productModel) throws Exception{
        return ResponseEntity.ok()
                .body(productService.addProduct(productModel));
    }

    @GetMapping("/getOne")
    public ResponseEntity<BodyResponse<JsonNode>> getOne(@Requ)

}
