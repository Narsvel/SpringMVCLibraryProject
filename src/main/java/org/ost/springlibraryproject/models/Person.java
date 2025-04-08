package org.ost.springlibraryproject.models;

import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public class Person {

    private int personId;

    @NotEmpty(message = "Имя не должно быть пустым!")
    @Size(min = 2, max = 150, message = "ФИО должно быть между 2 и 150 символами.")
    @Pattern(regexp = "[А-Я][а-я]+, [А-Я][а-я]+, [А-Я][а-я]+",
            message = "Ваше ФИО должено быть указано в этом формате: Фамилия, Имя, Отчество")
    private String fullName;

    @Min(value = 1800, message = "Год рождения должен быть больше, чем 1800.")
    private int yearOfBirth;

    public Person(int personId, String fullName, int yearOfBirth) {
        this.personId = personId;
        this.fullName = fullName;
        this.yearOfBirth = yearOfBirth;
    }

    public Person() {}

    public int getPersonId() {
        return personId;
    }

    public void setPersonId(int personId) {
        this.personId = personId;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public int getYearOfBirth() {
        return yearOfBirth;
    }

    public void setYearOfBirth(int yearOfBirth) {
        this.yearOfBirth = yearOfBirth;
    }
}
