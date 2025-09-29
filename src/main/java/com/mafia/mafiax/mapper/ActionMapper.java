package com.mafia.mafiax.mapper;

import com.mafia.mafiax.dto.ActionDTO;
import com.mafia.mafiax.entity.Action;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface ActionMapper {

    ActionDTO toDTO(Action action);

    Action toAction(ActionDTO actionDTO);
}
