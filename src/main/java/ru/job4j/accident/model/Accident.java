package ru.job4j.accident.model;

import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "accident")
public class Accident {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String name;
    private String description;
    private String carNumber;
    private String address;
    private byte[] image;
    private String status;
    private Date created;

    @ManyToOne
    @JoinColumn(name = "type_id")
    private AccidentType type;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(
            name = "accident_rule",
            joinColumns = @JoinColumn(name = "accident_id"),
            inverseJoinColumns = @JoinColumn(name = "rules_id")
    )
    private Set<Rule> rules = new HashSet<>();

    public Accident(String name) {
        this.name = name;
    }

    public Accident() {
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

    public AccidentType getType() {
        return type;
    }

    public void setType(AccidentType type) {
        this.type = type;
    }

    public Set<Rule> getRules() {
        return rules;
    }

    public void setRules(Set<Rule> rules) {
        this.rules = rules;
    }

    public void addRule(Rule rule) {
        this.rules.add(rule);
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
                && Objects.equals(created, accident.created);
    }

    @Override
    public int hashCode() {
        int result = Objects.hash(id, name, description, address, status, created);
        result = 31 * result + Arrays.hashCode(image);
        return result;
    }
}
