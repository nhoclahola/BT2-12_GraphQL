package com.nhoclahola.bt212_graphql.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterUserModel
{
    private String email;
    private String password;
    private String fullName;
}
