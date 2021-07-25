package ru.job4j.accident.model;

import java.util.Arrays;
import java.util.Date;
import java.util.Objects;

public class Accident {
    private int id;
    private String name;
    private String description;
    private String carNumber;
    private String address;
    private byte[] image;

    private String status;
    private Date created;
    private User user;

    public Accident(int id, String name, String description, String carNumber, String address,
                    byte[] image, String status, User user) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.carNumber = carNumber;
        this.address = address;
        this.image = image;
        this.status = status;
        this.created = new Date(System.currentTimeMillis());
        this.user = user;
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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getCarNumber() {
        return carNumber;
    }

    public void setCarNumber(String carNumber) {
        this.carNumber = carNumber;
    }


    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public byte[] getImage() {
        return image;
    }

    public void setImage(byte[] image) {
        this.image = image;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Date getCreated() {
        return created;
    }

    public void setCreated(Date created) {
        this.created = created;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Accident accident = (Accident) o;
        return id == accident.id
                && Objects.equals(name, accident.name)
                && Objects.equals(description, accident.description)
                && Objects.equals(address, accident.address)
                && Arrays.equals(image, accident.image)
                && Objects.equals(status, accident.status)
                && Objects.equals(created, accident.created)
                && Objects.equals(user, accident.user);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, description, address, status, created, user);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
