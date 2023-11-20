// package com.ryo.elrys.elastic;

// import com.ryo.elrys.model.MAP.RegisterMAP;
// import org.apache.http.HttpHost;
// import org.elasticsearch.action.index.IndexRequest;
// import org.elasticsearch.client.RestClient;
// import org.elasticsearch.client.RestHighLevelClient;

// import java.util.Random;

// public class Api {

//     public RestHighLevelClient client() {
//         return new RestHighLevelClient(
//                 RestClient.builder(
//                         new HttpHost("192.168.20.90", 9200, "http")));
//     }

//     public IndexRequest request(RegisterMAP registerMAP) {
//         Random random = new Random();
//         return new IndexRequest("elrys")
//                 .id(String.valueOf(random.nextLong()))
//                 .source(registerMAP.register());
//     }
// }
