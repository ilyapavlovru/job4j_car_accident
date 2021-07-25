package ru.job4j.accident.model;

import java.util.Objects;

public class User {
    private int id;

    private String name;

    private String email;

    private String password;

    private String phone;

    private Role role;

    public User() {
    }

    public User(String name, String email) {
        this.name = name;
        this.email = email;
    }

    public static User of(String name, String email, String password, String phone, Role role) {
        User user = new User();
        user.name = name;
        user.email = email;
        user.password = password;
        user.phone = phone;
        user.role = role;
        return user;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        User user = (User) o;
        return id == user.id;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }
}
