package com.ryo.elrys.response;

import java.util.HashMap;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BodyResponse<T> {

    private String status;
    private T data;

    public HashMap<String, Object> bodyResponse() {
        HashMap<String, Object> response = new HashMap<>();
        response.put("status", status);
        response.put("data", data);

        return response;
    }

}
