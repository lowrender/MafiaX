package com.mafia.mafiax.service;

import com.mafia.mafiax.dto.ActionDTO;
import com.mafia.mafiax.entity.ActionType;
import com.mafia.mafiax.mapper.ActionMapper;
import com.mafia.mafiax.repository.ActionRepository;
import com.mafia.mafiax.repository.PlayerRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
@Transactional
public class ActionService {


    private final ActionRepository actionRepository;
    private final PlayerRepository playerRepository;
    private final ActionMapper actionMapper;


}


