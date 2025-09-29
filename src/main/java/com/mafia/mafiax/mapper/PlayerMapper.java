package com.mafia.mafiax.mapper;

import com.mafia.mafiax.dto.PlayerDTO;
import com.mafia.mafiax.entity.Player;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    @Mapping(source = "user.id", target = "userId")
    @Mapping(source = "room.id", target = "roomId")
    PlayerDTO toDTO(Player player);

    @Mapping(source = "userId", target = "user.id")
    @Mapping(source = "roomId", target = "room.id")
    Player toPlayer(PlayerDTO playerDTO);
}
