package com.dgmf.repository;

import com.dgmf.entity.Role;
import com.dgmf.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
public class ManyToManyBidirectionalTest {
    @Autowired
    private RoleRepository roleRepository;

    // JUnit Test for Save Role
    @Test
    @DisplayName("JUnit Test for Save Role")
    void saveRole() {
        // Bidirectional Mapping ==> Whenever we save a Role, we
        // also save its associated Users
        // Create Users
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@gmail.com");
        user.setPassword("secrete");

        User admin = new User();
        admin.setFirstName("admin");
        admin.setLastName("admin");
        admin.setEmail("admin@gmail.com");
        admin.setPassword("admin");

        // Create Role
        Role roleAdmin = new Role();
        roleAdmin.setName("ROLE_ADMIN");

        // Add the 2 Users to the Role "roleAdmin"
        roleAdmin.getUsers().add(user);
        roleAdmin.getUsers().add(admin);

        // Because of the Bidirectional Mapping, we add
        // also the Role "roleAdmin" to the 2 Users
        user.getRoles().add(roleAdmin);
        admin.getRoles().add(roleAdmin);

        // Save the Role "roleAdmin"
        Role savedRole = roleRepository.save(roleAdmin);

    }

    // JUnit Test for Fetch all Roles
    @Test
    @DisplayName("JUnit Test for Fetch all Roles")
    void fetchRoles() {
        // Retrieved all Roles from the DB
        List<Role> roles = roleRepository.findAll();

        // Outputs (Nested ForEach)
        roles.forEach(role -> {
            // Display Roles Names
            System.out.println(role.getName());
            // Display Users Names
            role.getUsers().forEach(user ->
                    System.out.println(user.getFirstName())
                );
            }
        );
    }
}
