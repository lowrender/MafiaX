package com.mafia.mafiax.mapper;

import com.mafia.mafiax.dto.PlayerDTO;
import com.mafia.mafiax.entity.Player;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface PlayerMapper {

    PlayerDTO toDTO(Player player);

    Player toPlayer(PlayerDTO playerDTO);

}
