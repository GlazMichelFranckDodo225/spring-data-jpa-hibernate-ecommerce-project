package com.dgmf.repository;

import com.dgmf.entity.Role;
import com.dgmf.entity.User;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ManyToManyUnidirectionalTest {
    @Autowired
    private UserRepository userRepository;

    // JUnit Test for
    @Test
    @DisplayName("JUnit Test for Save User Method")
    void saveUser() {
        // Create User
        User user = new User();
        user.setFirstName("John");
        user.setLastName("Doe");
        user.setEmail("johndoe@gmail.com");
        user.setPassword("secrete");

        // Create 2 Roles
        Role admin = new Role();
        admin.setName("ROLE_ADMIN");

        Role customer = new Role();
        customer.setName("ROLE_CUSTOMER");

        // Assign Roles to User
        user.getRoles().add(admin);
        user.getRoles().add(customer);

        // Save User
        User savedUser = userRepository.save(user);
    }
}
