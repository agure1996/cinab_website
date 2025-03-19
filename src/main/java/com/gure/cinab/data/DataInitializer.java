package com.gure.cinab.data;

import com.gure.cinab.model.Role;
import com.gure.cinab.model.User;
import com.gure.cinab.repository.RoleRepository;
import com.gure.cinab.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

@Transactional
@Component
@RequiredArgsConstructor
public class DataInitializer implements ApplicationListener<ApplicationReadyEvent> {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    @Override
    public void onApplicationEvent(ApplicationReadyEvent event) {
        Set<String> defaultRoles = Set.of("ADMIN_ROLE", "USER_ROLE");
        createDefaultRoleIfNotExist(defaultRoles);
        createDefaultUserIfNotExists();
        createDefaultAdminIfNotExists();
    }

    private void createDefaultUserIfNotExists() {

        Role userRole = roleRepository.findByName("USER_ROLE").get();
        for (int i = 1; i <= 5; i++) {
            String defaultEmail = "user" + i + "@testmail.com";
            if (userRepository.existsByEmail(defaultEmail)) {
                continue;
            }
            User user = new User();

            user.setFirstName("The User");
            user.setLastName("User" + i);
            user.setRole(Set.of(userRole));
            user.setEmail(defaultEmail);
            user.setPassword(passwordEncoder.encode("12345"));
            userRepository.save(user);
            System.out.println("Default User " + i + " Created Successfully!");
        }
    }

    private void createDefaultAdminIfNotExists() {
        Role adminRole = roleRepository.findByName("ADMIN_ROLE").get();
        for (int i = 1; i <= 2; i++) {
            String defaultEmail = "admin" + i + "@testmail.com";
            if (userRepository.existsByEmail(defaultEmail)) {
                continue;
            }
            User admin = new User();
            admin.setFirstName("The Admin");
            admin.setLastName("Admin" + i);
            admin.setEmail(defaultEmail);
            admin.setRole(Set.of(adminRole));
            admin.setPassword(passwordEncoder.encode("pass"));
            userRepository.save(admin);
            System.out.println("Default Admin User " + i + " Created Successfully!");
        }
    }

    private void createDefaultRoleIfNotExist(Set<String> roles) {

        roles.stream()
                .filter(role -> roleRepository.findByName(role).isEmpty())
                .map(Role::new)
                .forEach(roleRepository::save);
    }
}
