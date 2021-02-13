package com.enigma.api.inventory.entities;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Table(name = "user")
@Entity
@SQLDelete(sql = "UPDATE user SET is_deleted = 1 WHERE id=?")
@Where(clause = "is_deleted=0")

public class User extends AbstractEntity<String>{

    @Id
    private String id;

    @Column(name = "first_name", length = 30)
    private String firstname;

    @Column(name = "last_name", length = 50)
    private String lastname;

    @Column(length = 20, nullable = false)
    private String role;

    @Override
    public String getId() {
        return this.id;
    }

    @Override
    public void setId(String s) {
        this.id = s;
    }

    public String getFirstname() {
        return firstname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getRole() {
        return role;
    }

    public void setRole(String role) {
        this.role = role;
    }
}
