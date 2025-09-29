package com.mafia.mafiax.mapper;

import com.mafia.mafiax.dto.UserDTO;
import com.mafia.mafiax.entity.Users;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {

    UserDTO toUserDTO(Users user);

    Users toUsers(UserDTO userDTO);

}
