package com.ryo.elrys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BodyUrl {
    MAIN_DOC("http://localhost:9200/elrys/_doc/"),
    MAIN_SEARCH("http://localhost:9200/elrys/_search/"),
    MAIN_CREATED("http://localhost:9200/elrys/_create/"),
    MAIN_DELETE_QUERY("http://localhost:9200/elrys/_delete_by_query/"),
    MAIN_UPDATE_QUERY("http://localhost:9200/elrys/_update_by_query/"),
    LOG_DOC("http://localhost:9200/elrys_log/_doc/"),
    LOG_SEARCH(""),
    LOG_DELETE_QUERY("http://localhost:9200/elrys_log/_delete_by_query/"),
    PRODUCT_DOC("http://localhost:9200/elrys_prod/_doc/"),
    PRODUCT_CREATED("http://localhost:9200/elrys_prod/_create/"),
    PRODUCT_SEARCH("http://localhost:9200/elrys/_search/"),
    PRODUCT_DELETE_QUERY("http://localhost:9200/elrys_prod/_delete_by_query/"),
    PRODUCT_UPDATE_QUERY("http://localhost:9200/elrys_prod/_delete_by_query/");
    private final String url;
}
