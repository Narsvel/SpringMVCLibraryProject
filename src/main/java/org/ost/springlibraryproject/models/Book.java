package org.ost.springlibraryproject.models;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Book {

    private int id;

    @NotEmpty(message = "Название книги не должно быть пустым!")
    private String title;

    @NotEmpty(message = "Имя автора не должно быть пустым!")
    @Size(min = 2, max = 150, message = "Имя автора должно быть между 2 и 150 символами.")
    @Pattern(regexp = "[A-Z]\\w+, [A-Z]\\w+, [A-Z]\\w+",
            message = "Имя автора должено быть указано в этом формате: Фамилия, Имя, Отчество")
    private String author;

    @NotEmpty(message = "Год не должен быть пустым!")
    private int year;

    public Book(int id, String title, String author, int year) {
        this.id = id;
        this.title = title;
        this.author = author;
        this.year = year;
    }

    public Book() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
}
