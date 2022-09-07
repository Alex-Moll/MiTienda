package com.miTienda.app.model.request;

import lombok.*;

import javax.validation.constraints.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class AuthenticationRequest {

    @NonNull
    @Email(message = "Incorrect email")
    private String username;
    @NonNull
    @NotEmpty(message = "Empty password")
    private String password;

}
