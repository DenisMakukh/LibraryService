package ru.makukh.weblibrary.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;

public class Person {

    private int id;
    @NotEmpty(message = "Имя не должно быть пустым")
    @Size(min = 5, max = 100, message = "Имя должно быть от 2 до 30 символов")
    private String name;
    @NotEmpty(message = "Дата рождения не может быть пустой")
    private String birthDate;

    public Person() {}

    public Person(int id, String name, String birthDate) {
        this.id = id;
        this.name = name;
        this.birthDate = birthDate;
    }

    public String getFullName(){
        return name + ", " + birthDate;
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

    public String getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(String birthDate) {
        this.birthDate = birthDate;
    }
}
