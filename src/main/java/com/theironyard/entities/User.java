package com.theironyard.entities;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Arrays;
import java.util.HashSet;
import java.util.Set;

/**
 * Created by alexanderhughes on 7/15/16.
 */
@Entity
public class User {
    @Id
    @GeneratedValue
    int id;

    @NotNull
    String username;

    @NotNull
    String password;

    @NotNull
    String firstName;

    @NotNull
    String lastName;

    @OneToOne(mappedBy = "owner")
    Pitch pitch;

    public User() {
    }

    public User(int id, String username, String password, String firstName, String lastName) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Pitch getPitch() {
        return pitch;
    }

    public void setPitch(Pitch pitch) {
        this.pitch = pitch;
    }

    public boolean checkFields() {
        HashSet fields = new HashSet(Arrays.asList(username, password, firstName, lastName));
        if (!(fields.contains("") || fields.contains(null))) {
            return true;
        }
        return false;
    }
}
