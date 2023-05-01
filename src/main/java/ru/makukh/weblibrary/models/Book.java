package ru.makukh.weblibrary.models;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Book {

    private int id;
    @NotEmpty(message = "Название не должно быть пустым")
    @Size(max = 100, message = "Нзавание не должно превышать 100 символов")
    private String name;
    @Size(max = 100, message = "Имя автора не должно превышать 100 символов")
    @NotEmpty(message = "У книги должен быть автор")
    private String author;
    @Min(value = 0, message = "Введите год издания в формате YYYY")
    private int year;

    public Book(){}

    public Book(int id, String name, String author, int year) {
        this.id = id;
        this.name = name;
        this.author = author;
        this.year = year;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
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
