package com.example.product_service.core.errorhandling;

import lombok.Value;

import java.util.Date;

@Value(staticConstructor = "of")
public class ErrorMessage {
    Date timestamp;
    String message;
}
