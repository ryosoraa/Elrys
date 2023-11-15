package com.ryo.elrys.configuration;

import com.ryo.elrys.elastic.Api;
import com.ryo.elrys.elastic.Client;
import com.ryo.elrys.elastic.Request;
import org.elasticsearch.client.RestHighLevelClient;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ElasticConfiguration {

    @Bean
    public Api api(){return new Api();}
}
