package com.rosebankcollege.Payment.System.init;

import com.rosebankcollege.Payment.System.model.Employee;
import com.rosebankcollege.Payment.System.repo.EmployeeRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class EmployeeInitializer implements CommandLineRunner {

    private final EmployeeRepository employeeRepository;
    private final PasswordEncoder passwordEncoder;

    public EmployeeInitializer(EmployeeRepository employeeRepository, PasswordEncoder passwordEncoder) {
        this.employeeRepository = employeeRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {
        createEmployeeIfNotExists("admin@example.com", "Admin User", "Admin@123");
        createEmployeeIfNotExists("obakeng.pitse@example.com", "Obakeng Pitse", "Password@123");
        createEmployeeIfNotExists("jane.smith@example.com", "Jane Smith", "Securepass@123");
    }

    private void createEmployeeIfNotExists(String email, String name, String rawPassword) {
        if (employeeRepository.findByEmailAddress(email).isEmpty()) {
            Employee employee = new Employee();
            employee.setEmailAddress(email);
            employee.setFullName(name);
            employee.setPassword(passwordEncoder.encode(rawPassword));
            employeeRepository.save(employee);
            System.out.println("Employee " + name + " created.");
        } else {
            System.out.println("Employee with email " + email + " already exists.");
        }
    }

}
