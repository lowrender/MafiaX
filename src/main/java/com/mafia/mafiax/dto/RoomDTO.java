package com.mafia.mafiax.dto;

import com.mafia.mafiax.entity.RoomStatus;

import java.time.LocalDateTime;

public record RoomDTO(
        Long id,
        String code,
        Long hostId,
        RoomStatus status,
        int maxPlayers,
        LocalDateTime createdAt,
        List<PlayerDto> players
) {}
