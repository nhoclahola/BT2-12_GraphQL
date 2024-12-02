package com.nhoclahola.bt212_graphql.controllers.api;

import com.nhoclahola.bt212_graphql.entities.User;
import com.nhoclahola.bt212_graphql.services.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/users")
@RestController
@RequiredArgsConstructor
public class ApiUserController
{
    private final UserService userService;

    @GetMapping("/me")
    public ResponseEntity<User> authenticatedUser()
    {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String email = authentication.getName();
        return ResponseEntity.ok(userService.findByEmail(email));
    }
}
