package pl.jawegiel.model;

import lombok.Data;


@Data
public class Book {

    private int year;
    private long id;
    private String title, author, personWhoLent;
    private boolean available;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book(long id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book(long id, String title, String author, int year, boolean available, String personWhoLent) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
        this.available = available;
        this.personWhoLent = personWhoLent;
    }
}
