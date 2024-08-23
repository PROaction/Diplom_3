package com.example.models.responses;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserGetCreateResponseModel {
    private boolean success;
    private UserResponseModel user;
    private String accessToken;
    private String refreshToken;
}
