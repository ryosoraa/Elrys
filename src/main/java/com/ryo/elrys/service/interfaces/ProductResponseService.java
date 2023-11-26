package com.ryo.elrys.service.interfaces;

import com.ryo.elrys.model.ProductModel;
import com.ryo.elrys.payload.BodyResponse;

public interface ProductResponseService {
    BodyResponse<Object> addProduct(ProductModel productModel) throws Exception;
    BodyResponse<Object> getProductOne(ProductModel productModel) throws Exception;
    BodyResponse<Object> getProduct(ProductModel productModel) throws Exception;
    BodyResponse<Object> updateProduct(ProductModel productModel) throws Exception;

}
