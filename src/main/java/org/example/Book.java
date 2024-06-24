package org.example;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Book {
    private int id;
    private String title;
    private String author;
    private String publisher;
    private String isbn10;
    private String isbn13;
    private LocalDate publicationDate;
}
