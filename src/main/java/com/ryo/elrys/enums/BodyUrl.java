package com.ryo.elrys.enums;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum BodyUrl {
    MAIN_DOC("http://192.168.20.90:9200/elrys/_doc/"),
    MAIN_SEARCH("http://192.168.20.90:9200/elrys/_search/"),
    MAIN_CREATED("http://192.168.20.90:9200/elrys/_create/"),
    MAIN_DELETE_QUERY("http://192.168.20.90:9200/elrys/_delete_by_query/"),
    MAIN_UPDATE_QUERY("http://192.168.20.90:9200/elrys/_update_by_query/"),
    LOG_DOC("http://192.168.20.90:9200/elrys_log/_doc/"),
    LOG_SEARCH(""),
    LOG_DELETE_QUERY("http://192.168.20.90:9200/elrys_log/_delete_by_query/");
    private final String url;
}
