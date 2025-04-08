package org.ost.springlibraryproject.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Size;

public class Book {

    private int bookId;

    private Integer personId;

    @NotEmpty(message = "Название книги не должно быть пустым!")
    private String title;

    @NotEmpty(message = "Имя автора не должно быть пустым!")
    @Size(min = 2, max = 150, message = "Имя автора должно быть между 2 и 150 символами.")
    private String author;

    private int year;

    public Book(int bookId, Integer personId, String title, String author, int year) {
        this.bookId = bookId;
        this.personId = personId;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book() {}

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

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

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    public Integer getPersonId() {
        return personId;
    }

    public void setPersonId(Integer personId) {
        this.personId = personId;
    }

    public boolean isPersonId() {
        return personId == null;
    }
}
