package com.mafia.mafiax.mapper;

import com.mafia.mafiax.dto.VoteDTO;
import com.mafia.mafiax.entity.Vote;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface VoteMapper {

    @Mapping(source = "voter.id", target = "playerId")
    @Mapping(source = "target.id", target = "targetId")
    VoteDTO toDTO(Vote vote);

    @Mapping(source = "playerId", target = "voter.id")
    @Mapping(source = "targetId", target = "target.id")
    Vote toEntity(VoteDTO voteDTO);
}
