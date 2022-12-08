package com.example;

/*
 * 3rd party libraries to convert JSONs into Java objects and vice versa
 * it requires a class to be set in specific standard: Java Bean Design Pattern
 * 
 * Java Bean Design Pattern requirements:
 *  all fields must be private
 *  all fields must have a getter and setter methods
 */
public class Book {
    
    private String title;
    private String author;
    private String genre;
    private int isbn;

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getGenre() {
        return genre;
    }

    public void setGenre(String genre) {
        this.genre = genre;
    }

    public int getIsbn() {
        return isbn;
    }
    
    public void setIsbn(int isbn) {
        this.isbn = isbn;
    }
}
