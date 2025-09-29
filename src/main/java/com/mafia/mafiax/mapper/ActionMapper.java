package com.mafia.mafiax.mapper;

import com.mafia.mafiax.dto.ActionDTO;
import com.mafia.mafiax.entity.Action;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface ActionMapper {

    @Mapping(source = "player.id", target = "playerId")
    @Mapping(source = "target.id", target = "targetId")
    ActionDTO toDTO(Action action);

    @Mapping(source = "playerId", target = "player.id")
    @Mapping(source = "targetId", target = "target.id")
    Action toAction(ActionDTO actionDTO);
}
