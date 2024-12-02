package com.nhoclahola.bt212_graphql.controllers.api;

import com.nhoclahola.bt212_graphql.entities.User;
import com.nhoclahola.bt212_graphql.models.LoginResponse;
import com.nhoclahola.bt212_graphql.models.LoginUserModel;
import com.nhoclahola.bt212_graphql.models.RegisterUserModel;
import com.nhoclahola.bt212_graphql.security.JwtUtils;
import com.nhoclahola.bt212_graphql.services.AuthService;
import com.nimbusds.jose.JOSEException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
@RequiredArgsConstructor
public class ApiAuthController
{
    private final AuthService authenticationService;

    @PostMapping("/signup")
    @Transactional
    public ResponseEntity<String> register(@RequestBody RegisterUserModel registerUser) throws JOSEException
    {
        String token = authenticationService.register(registerUser);
        return ResponseEntity.ok(token);
    }

    @PostMapping("/login")
    @Transactional
    public ResponseEntity<LoginResponse> authenticate(@RequestBody LoginUserModel loginUser) throws JOSEException
    {
        String jwtToken = authenticationService.login(loginUser);
        LoginResponse response = LoginResponse.builder()
                .token(jwtToken)
                .expiresIn(JwtUtils.getExpiredTimeFromToken(jwtToken))
                .build();
        return ResponseEntity.ok(response);
    }
}
