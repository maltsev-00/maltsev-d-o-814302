package net.javaguides.springboot.springsecurity.model;

import lombok.*;

import java.time.LocalDateTime;

@Value
@Builder
public class ErrorDetails {
    LocalDateTime timestamp;
    String error;
}
