package com.mafia.mafiax.dto;

import java.time.LocalDateTime;

public record UserDTO(
        Long id,
        String username,
        LocalDateTime createdAt
){}
