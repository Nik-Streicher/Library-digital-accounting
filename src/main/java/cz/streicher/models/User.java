package cz.streicher.models;

public class User {

    private int user_id;
    private String full_name;
    private int year_of_birth;


    public User(String full_name, int year_of_birth, int user_id) {
        this.user_id = user_id;
        this.full_name = full_name;
        this.year_of_birth = year_of_birth;
    }

    public User() {
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
