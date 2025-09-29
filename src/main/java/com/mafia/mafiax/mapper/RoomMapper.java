package com.mafia.mafiax.mapper;

import com.mafia.mafiax.dto.RoomDTO;
import com.mafia.mafiax.entity.Room;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring", uses = {PlayerMapper.class})
public interface RoomMapper {

    @Mapping(source = "host.id", target = "hostId")
    RoomDTO toDTO(Room room);

    @Mapping(source = "hostId", target = "host.id")
    Room toRoom(RoomDTO roomDTO);
}
