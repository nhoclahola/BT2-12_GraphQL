package com.nhoclahola.bt212_graphql.services;

import com.nhoclahola.bt212_graphql.entities.User;
import com.nhoclahola.bt212_graphql.models.LoginUserModel;
import com.nhoclahola.bt212_graphql.models.RegisterUserModel;
import com.nhoclahola.bt212_graphql.repositories.UserRepository;
import com.nhoclahola.bt212_graphql.security.JwtUtils;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class AuthService
{
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    public String register(RegisterUserModel input) throws JOSEException
    {
        User user = User.builder()
                .fullName(input.getFullName())
                .email(input.getEmail())
                .password(passwordEncoder.encode(input.getPassword()))
                .build();
        userRepository.save(user);
        return JwtUtils.generateToken(input.getEmail());
    }

    public String login(LoginUserModel input) throws JOSEException
    {
        User user = userRepository.findByEmail(input.getEmail()).get();
        if (user != null && passwordEncoder.matches(input.getPassword(), user.getPassword())) {
            return JwtUtils.generateToken(input.getEmail());
        }
        throw new RuntimeException("Invalid credentials");
    }
}
