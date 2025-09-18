package com.mafia.mafiax.dto;

import com.mafia.mafiax.entity.Roles;

public record PlayerDTO(
        Long id,
        Long userId,
        Long roomId,
        Roles role,
        boolean isAlive,
        int seatIndex,
        boolean isConnected
) {}
