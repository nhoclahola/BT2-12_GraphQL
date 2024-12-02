package com.nhoclahola.bt212_graphql.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginResponse
{
    private String token;
    private long expiresIn;
}
