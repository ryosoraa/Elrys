package com.ryo.elrys.configuration;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.ryo.elrys.utils.Equipment;
import com.sun.jersey.api.client.Client;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticConfiguration {

    /**
     * Mengkonfigurasi bean untuk objek Equipment.
     * 
     * @return Objek Equipment yang dikonfigurasi.
     */
    @Bean
    public Equipment equipment() {
        return new Equipment();
    }

    /**
     * Mengkonfigurasi bean untuk objek Client (Jersey API client).
     *
     * @return Objek Client yang dikonfigurasi.
     */
    @Bean
    public Client client() {
        return new Client();
    }

    /**
     * Mengkonfigurasi bean untuk objek ObjectMapper (Jackson).
     *
     * @return Objek ObjectMapper yang dikonfigurasi.
     */
    @Bean
    public ObjectMapper mapper() {
        return new ObjectMapper();
    }

}
