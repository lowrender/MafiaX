package com.mafia.mafiax.dto;

import java.time.LocalDateTime;

public record ActionDTO(
        Long id,
        Long playerId,
        String actionType,
        Long targetId,
        int roundNumber,
        LocalDateTime createdAt
) {}
