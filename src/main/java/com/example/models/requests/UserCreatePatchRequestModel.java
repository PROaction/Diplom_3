package com.example.models.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserCreatePatchRequestModel {
    private String email;
    private String password;
    private String name;
}
