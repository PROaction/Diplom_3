package com.example.models;


import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class ErrorResponseModel {
    private boolean success;
    private String message;
}
