package com.nhoclahola.bt212_graphql.models;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LoginUserModel
{
    private String email;
    private String password;
}
