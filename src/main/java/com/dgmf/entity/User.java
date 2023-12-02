package com.dgmf.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

// @Data @NoArgsConstructor @AllArgsConstructor @Builder
@Entity
@Table(
        name = "users",
        uniqueConstraints =
            @UniqueConstraint(
                    name = "unique_email", // Entity Attribute name
                    columnNames = "email" // DB Column name
            )
)
public class User {
    @Id @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    @ManyToMany(
            // Whenever we load Users, associated Roles will load also
            fetch = FetchType.EAGER,
            // All operations (save, update, delete, etc...) on User will
            // be implemented on associated Roles also
            cascade = CascadeType.ALL
    )
    // To customize the Join Table (optional)
    @JoinTable(
            // Join Table Name
            name = "users_roles",
            // Owner Entity (User)
            joinColumns =
                @JoinColumn(
                    // Foreign Key in the Join Table
                    name = "user_id",
                    // Reference the User Primary Key "id"
                    referencedColumnName = "id"
                ),
            // Related Entity (Role)
            inverseJoinColumns =
                @JoinColumn(
                    // Foreign Key in the Join Table
                    name = "role_id",
                    // Reference the Role Primary Key "id"
                    referencedColumnName = "id"
                )
    )
    private Set<Role> roles = new HashSet<>();

    public User() {
    }

    public User(String firstName, String lastName, String email, String password, Set<Role> roles) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.roles = roles;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Set<Role> getRoles() {
        return roles;
    }

    public void setRoles(Set<Role> roles) {
        this.roles = roles;
    }
}
