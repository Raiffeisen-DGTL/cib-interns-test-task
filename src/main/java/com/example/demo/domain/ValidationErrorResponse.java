package com.example.demo.domain;

import lombok.Data;

import java.util.List;

@Data
public class ValidationErrorResponse {
    private final List<Violation> violations;
}
