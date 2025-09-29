package com.mafia.mafiax.mapper;


import com.mafia.mafiax.dto.VoteDTO;
import com.mafia.mafiax.entity.Vote;
import org.mapstruct.Mapper;


@Mapper(componentModel = "spring")
public interface VoteMapper {

    VoteDTO toVoteDTO(Vote vote);

    Vote toVote(VoteDTO voteDTO);
}
