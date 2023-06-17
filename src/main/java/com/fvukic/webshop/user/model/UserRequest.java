package com.fvukic.webshop.user.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotBlank;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class UserRequest {

    @NotBlank
    private String username;

    @NotBlank
    private String password;
}
