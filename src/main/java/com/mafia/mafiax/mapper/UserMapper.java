package com.mafia.mafiax.mapper;

import com.mafia.mafiax.dto.UserDTO;
import com.mafia.mafiax.entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toDTO(User user);

    User toEntity(UserDTO userDTO);

}
