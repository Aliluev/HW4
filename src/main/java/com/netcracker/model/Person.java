package com.netcracker.model;

import javax.validation.constraints.*;
import java.io.Serializable;

public class Person implements Serializable {

    @Pattern(regexp = "[a-zA-Z]{1,30}")
    @NotBlank
    private String surname;

    @Pattern(regexp = "[a-zA-Z]{1,30}")
    @NotBlank
    private String name;

    @Pattern(regexp = "[a-zA-Z]{1,30}")
    @NotBlank
    private String patronymic;

    @Min(16)
    @Max(110)
    private int age;

    @Min(1)
    private int salary;

    @Email
    @NotBlank
    private String emailAdress;

    @NotBlank
    @Pattern(regexp = "[a-zA-Z]{1,30}")
    private String workPlace;

    private static final long serialVersionUID = 1L;

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPatronymic() {
        return patronymic;
    }

    public void setPatronymic(String patronymic) {
        this.patronymic = patronymic;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public String getEmailAdress() {
        return emailAdress;
    }

    public void setEmailAdress(String emailAdress) {
        this.emailAdress = emailAdress;
    }

    public String getWorkPlace() {
        return workPlace;
    }

    public void setWorkPlace(String workPlace) {
        this.workPlace = workPlace;
    }
}
