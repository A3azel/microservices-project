package com.example.mainservice.payload.response.authentication;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class InvalidLoginResponse {
    private String username;
    private String password;
}
