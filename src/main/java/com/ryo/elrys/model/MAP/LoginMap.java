package com.ryo.elrys.model.MAP;

import com.ryo.elrys.model.DTO.LoginDTO;

import java.sql.Timestamp;
import java.util.HashMap;

public class LoginMap {

    private String id;
    private String username;
    private String email;
    private String timestamp;
    private String deviceType;
    private String deviceModel;

    public HashMap<String, Object> login(){
        HashMap<String, Object> login = new HashMap<>();

        login.put("Accounts_id", id);
        login.put("email", email);
        login.put("username", username);
        login.put("timesTamp", timestamp);
        login.put("device_info", device());

        return login;
    }

    public HashMap<String, Object> device(){
        HashMap<String, Object> device = new HashMap<>();

        device.put("device_type", deviceType);
        device.put("device_model", deviceModel);

        return device;
    }

    public LoginMap(LoginDTO loginDTO, String id, String username){
        System.out.println("username ->" + username);
        this.id = id;
        this.email = loginDTO.getEmail();
        this.username = username;
        this.deviceType = loginDTO.getDeviceType();
        this.deviceModel = loginDTO.getDeviceModel();
        this.timestamp =  String.valueOf(new Timestamp(System.currentTimeMillis()));

    }
}
