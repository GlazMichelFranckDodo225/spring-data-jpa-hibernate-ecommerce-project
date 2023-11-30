package com.dgmf.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.HashSet;
import java.util.Set;

@Data @NoArgsConstructor @AllArgsConstructor @Builder
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
}
