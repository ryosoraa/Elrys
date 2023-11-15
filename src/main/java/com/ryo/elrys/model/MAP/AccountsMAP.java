package com.ryo.elrys.model.MAP;

import com.ryo.elrys.security.BCrypt;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.util.HashMap;

@Builder
@Data
public class AccountsMAP {

    private String email;
    private String password;
    private String username;
    private String times;


    public HashMap<String, Object> register(){
        HashMap<String, Object> jsonMap = new HashMap<>();

        jsonMap.put("email", email);
        jsonMap.put("password", BCrypt.hashpw(password, BCrypt.gensalt()));
        jsonMap.put("username", username);
        jsonMap.put("register", times);

        return jsonMap;
    }
}
