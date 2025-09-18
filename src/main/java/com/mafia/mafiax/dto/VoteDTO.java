package com.mafia.mafiax.dto;

import java.time.LocalDateTime;

public record VoteDTO(
        Long id,
        Long playerId,
        Long targetId,
        int roundNumber,
        LocalDateTime createdAt
) {}
