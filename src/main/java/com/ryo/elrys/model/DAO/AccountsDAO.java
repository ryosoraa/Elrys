package com.ryo.elrys.model.DAO;


import com.ryo.elrys.model.DTO.AccountsDTO;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(indexName = "elrys")
public class AccountsDAO {

    @Id
    private String id;
    private String email;
    private String password;
    private String username;

    public AccountsDAO(AccountsDTO accountsDTO){
        this.email = accountsDTO.getEmail();
        this.password = accountsDTO.getPassword();
        this.username = accountsDTO.getUsername();
    }

}
