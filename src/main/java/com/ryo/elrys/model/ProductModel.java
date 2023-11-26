package com.ryo.elrys.model;

import lombok.Data;

import java.util.List;

@Data
public class ProductModel {
    private String type;
    private String title;
    private String director;
    private String release;
    private List<String> genre;
    private Double rating;
    private String format;
    private Integer runtime;
    private String language;
    private String subtitles;
    private Double price;
}
