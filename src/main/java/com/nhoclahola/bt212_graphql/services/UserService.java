package com.nhoclahola.bt212_graphql.services;

import com.nhoclahola.bt212_graphql.entities.User;
import com.nhoclahola.bt212_graphql.repositories.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class UserService
{
    private final UserRepository userRepository;

    public User findByEmail(String email)
    {
        return userRepository.findByEmail(email).orElseThrow();
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User createUser(String fullName, String email, String password) {
        User user = new User();
        user.setFullName(fullName);
        user.setEmail(email);
        user.setPassword(password);
        return userRepository.save(user);
    }

    public User updateUser(Integer id, String fullName, String email, String password) {
        User user = userRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("User not found"));
        if (fullName != null) user.setFullName(fullName);
        if (email != null) user.setEmail(email);
        if (password != null) user.setPassword(password);
        return userRepository.save(user);
    }

    public boolean deleteUser(Integer id) {
        if (!userRepository.existsById(id)) {
            throw new RuntimeException("User not found");
        }
        userRepository.deleteById(id);
        return true;
    }
}
