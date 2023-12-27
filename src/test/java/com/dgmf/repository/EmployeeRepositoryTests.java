package com.dgmf.repository;

import com.dgmf.entity.Employee;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.context.SpringBootTest;

// @DataJpaTest
@SpringBootTest
public class EmployeeRepositoryTests {
    @Autowired
    private EmployeeRepository employeeRepository;

    // JUnit Test for Save Employee Operations
    @Test
    @DisplayName("JUnit Test for Save Employee Operations")
    void givenEmployeeObject_whenSave_thenReturnedSavedEmployee() {
        // Given - Precondition or Setup
        Employee employee = Employee.builder()
                .firstName("John")
                .lastName("Doe")
                .email("johndoe@gmail.com")
                .password("12345")
                .build();

        // When - Action or the Behavior that we are going to test
        Employee savedEmployee = employeeRepository.save(employee);

        // Then - Verify the Output
        // Typically, one check is more than enough
        // Static Imports
        assertThat(savedEmployee).isNotNull();
        assertThat(savedEmployee.getId()).isGreaterThan(0);
        assertThat(savedEmployee.getFirstName()).isEqualTo("John");
        assertThat(savedEmployee.getLastName()).isEqualTo("Doe");
        assertThat(savedEmployee.getEmail()).isEqualTo("johndoe@gmail.com");
        assertThat(savedEmployee.getPassword()).isEqualTo("12345");
    }
}
