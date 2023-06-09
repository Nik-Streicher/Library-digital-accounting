package cz.streicher.models;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Pattern;

public class User {


    private int user_id;
    @NotEmpty(message = "Name should be not empty")
    @Pattern(regexp = "^[\\p{Lu}\\p{Lt}][\\p{L}\\p{M}\\s'-]+ [\\p{Lu}\\p{Lt}][\\p{L}\\p{M}\\s'-]+$",
            message = "Name should be in format: First_name Optional[Patronymic] Second_name")

    private String full_name;

    private int year_of_birth;

    public User(String full_name, int year_of_birth, int user_id) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.year_of_birth = year_of_birth;
    }

    public User() {
    }
    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public void setYear_of_birth(int year_of_birth) {
        this.year_of_birth = year_of_birth;
    }

    public String getFull_name() {
        return full_name;
    }

    public int getYear_of_birth() {
        return year_of_birth;
    }

    public int getUser_id() {
        return user_id;
    }



}
