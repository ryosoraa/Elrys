package com.ryo.elrys.model.MAP;

import com.ryo.elrys.model.DTO.AdditionalInfoDTO;
import com.ryo.elrys.model.DTO.AddressDTO;
import com.ryo.elrys.model.DTO.RegisterDTO;
import com.ryo.elrys.security.BCrypt;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

@Data
public class RegisterMAP {

    private String email;
    private String password;
    private String username;
    private String times;
    private String fullName;
    private String dob;
    private String street;
    private String city;
    private Integer postalCode;
    private String country;
    private Integer phone;
    private String gender;
    private Boolean subscription;
    private List<String> preference;


    public HashMap<String, Object> register(){
        HashMap<String, Object> register = new HashMap<>();

        register.put("email", email);
        register.put("password", BCrypt.hashpw(password, BCrypt.gensalt()));
        register.put("register", times);
        register.put("username", username);
        register.put("full_name", fullName);
        register.put("date_of_birth", dob);
        register.put("address", address());
        register.put("phone_number", phone);
        register.put("additional_info", info());

        return register;
    }

    public HashMap<String, Object> address(){
        HashMap<String, Object> address = new HashMap<>();
        
        address.put("street", street);
        address.put("city", city);
        address.put("postal_Code", postalCode);
        address.put("country", country);

        return address;
    }

    public HashMap<String, Object> info(){
        HashMap<String, Object> address = new HashMap<>();

        address.put("gender", gender);
        address.put("newsletter_subscription", subscription);
        address.put("preference", preference);
        address.put("country", country);

        return address;
    }

    public RegisterMAP(RegisterDTO registerDTO){
        System.out.println("fulname -> " + registerDTO.getFullName());
        this.email = registerDTO.getEmail();
        this.password = registerDTO.getPassword();
        this.username = registerDTO.getUsername();
        this.times = String.valueOf(new Timestamp(System.currentTimeMillis()));
        this.fullName = registerDTO.getFullName();
        this.dob = registerDTO.getDateOfBirth();
        this.street = registerDTO.getStreet();
        this.city = registerDTO.getCity();
        this.postalCode = registerDTO.getPostalCode();
        this.country = registerDTO.getCountry();
        this.phone = registerDTO.getPhone();
        this.gender = registerDTO.getGender();
        this.subscription = registerDTO.getSubscription();
        this.preference = registerDTO.getPreference();
    }
}
