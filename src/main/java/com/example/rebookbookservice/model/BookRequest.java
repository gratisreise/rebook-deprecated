package com.example.rebookbookservice.model;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {
    private String title;
    private String author;
    private String publisher;
    private String isbn;
    private String description;
    private Integer price;
    private String publishedDate;
    private String cover;
}