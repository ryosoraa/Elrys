// package com.ryo.elrys.model.MAP;

// import com.ryo.elrys.model.DTO.AccountsDTO;
// import com.ryo.elrys.model.DTO.UpdateDTO;
// import com.ryo.elrys.security.BCrypt;
// import io.swagger.v3.oas.annotations.media.Schema;
// import lombok.Data;

// import java.sql.Timestamp;
// import java.util.HashMap;
// import java.util.List;

// @Data
// public class RegisterMAP {

//     private String email;
//     private String password;
//     private String username;
//     private String times;
//     private String fullName;
//     private String dob;

//     // Address
//     private String street;
//     private String city;
//     private Integer postalCode;
//     private String country;
//     private String coordinate;

//     // Additional info
//     private Integer phone;
//     private String gender;
//     private Boolean subscription;
//     private List<String> preference;
//     private String occupation;

//     // Education
//     private String degree;
//     private String major;
//     private String school;

//     private List<String> interests;
//     private List<String> skills;
//     private List<String> languages;

//     public HashMap<String, Object> user() {
//         HashMap<String, Object> user = new HashMap<>();

//         user.put("email", email);
//         user.put("password", BCrypt.hashpw(password, BCrypt.gensalt()));
//         user.put("registe", times);
//         user.put("username", username);
//         user.put("full_name", fullName);
//         user.put("date_of_birth", dob);
//         user.put("address", address());
//         user.put("phone_number", phone);
//         user.put("additional_info", info());

//         return user;
//     }

//     public HashMap<String, Object> address() {
//         HashMap<String, Object> address = new HashMap<>();

//         address.put("street", street);
//         address.put("city", city);
//         address.put("postal_Code", postalCode);
//         address.put("country", country);

//         return address;
//     }

//     public HashMap<String, Object> info() {
//         HashMap<String, Object> info = new HashMap<>();

//         info.put("gender", gender);
//         info.put("newsletter_subscription", subscription);
//         info.put("preference", preference);
//         info.put("country", country);
//         info.put("education", education());
//         info.put("occupation", occupation);
//         info.put("interests", interests);
//         info.put("skills", skills);
//         info.put("languages", languages);

//         return info;
//     }

//     public HashMap<String, Object> education() {
//         HashMap<String, Object> education = new HashMap<>();

//         education.put("degree", degree);
//         education.put("major", major);
//         education.put("school", school);

//         return education;
//     }

//     public RegisterMAP(AccountsDTO accountsDTO) {
//         this.email = accountsDTO.getEmail();
//         this.password = accountsDTO.getPassword();
//         this.times = String.valueOf(new Timestamp(System.currentTimeMillis()));
//         this.fullName = null;
//         this.dob = null;
//         this.street = null;
//         this.city = null;
//         this.postalCode = null;
//         this.country = null;
//         this.coordinate = null;
//         this.phone = null;
//         this.gender = null;
//         this.subscription = null;
//         this.preference = null;
//         this.occupation = null;
//         this.degree = null;
//         this.major = null;
//         this.school = null;
//         this.interests = null;
//         this.skills = null;
//         this.languages = null;
//     }

//     // public RegisterMAP(UpdateDTO updateDTO) {
//     // this.email = updateDTO.getEmail();
//     // this.password = updateDTO.getPassword();
//     // this.times = String.valueOf(new Timestamp(System.currentTimeMillis()));
//     // this.fullName = updateDTO.getFullName();
//     // this.dob = updateDTO.getDob();
//     // this.street = updateDTO.;
//     // this.city = updateDTO.get
//     // this.postalCode = updateDTO.get
//     // this.country = updateDTO.get
//     // this.coordinate = updateDTO.get
//     // this.phone = updateDTO.get
//     // this.gender = updateDTO.get
//     // this.subscription = updateDTO.get
//     // this.preference = updateDTO.get
//     // this.occupation = updateDTO.get
//     // this.degree = updateDTO.get
//     // this.major = updateDTO.get
//     // this.school = updateDTO.get
//     // this.interests = updateDTO.get
//     // this.skills = updateDTO.get
//     // this.languages = updateDTO.get
//     // }
// }
