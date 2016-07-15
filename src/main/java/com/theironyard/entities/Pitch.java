package com.theironyard.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.List;
import java.util.Set;

/**
 * Created by alexanderhughes on 7/15/16.
 */
@Entity
public class Pitch {
    @Id
    @GeneratedValue
    int id;

    @NotNull
    String title;

    @Column(nullable = false, length = 500)
    String description;

    @OneToOne
    User owner;

    @ManyToMany(cascade=CascadeType.ALL)
    @JoinTable(name="user_pitch", joinColumns=@JoinColumn(name="user_id"),
    inverseJoinColumns=@JoinColumn(name="pitch_id"))
    List<User> users;

    public Pitch() {
    }

    public Pitch(String title, String description, User owner) {
        this.title = title;
        this.description = description;
        this.owner = owner;
    }

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

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public User getOwner() {
        return owner;
    }

    public void setOwner(User owner) {
        this.owner = owner;
    }

    public List<User> getUsers() {
        return users;
    }

    public void setUsers(List<User> users) {
        this.users = users;
    }
}
