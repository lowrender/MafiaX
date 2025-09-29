package com.mafia.mafiax.service;


import com.mafia.mafiax.dto.PlayerDTO;
import com.mafia.mafiax.dto.RoomDTO;
import com.mafia.mafiax.entity.Player;
import com.mafia.mafiax.entity.Room;
import com.mafia.mafiax.entity.Users;
import com.mafia.mafiax.exceptions.RoomNotFoundException;
import com.mafia.mafiax.exceptions.UserNotFoundException;
import com.mafia.mafiax.mapper.PlayerMapper;
import com.mafia.mafiax.repository.PlayerRepository;
import com.mafia.mafiax.repository.RoomRepository;
import com.mafia.mafiax.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PlayerService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;


    public PlayerService(RoomRepository roomRepository, UserRepository userRepository, PlayerRepository playerRepository, PlayerMapper playerMapper) {
        this.roomRepository = roomRepository;
        this.userRepository = userRepository;
        this.playerRepository = playerRepository;
        this.playerMapper = playerMapper;
    }


    public PlayerDTO joinRoom(Long userId, Long roomId, int seatIndex) {

        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));

        Room room = roomRepository.findById(roomId)
                .orElseThrow( () -> new RoomNotFoundException("Room not found"));

        Player player = new Player();
        player.setUser(user);
        player.setRoom(room);
        player.setSeatIndex(seatIndex);
        player.setAlive(true);
        player.setConnected(true);

        Player savedPlayer = playerRepository.save(player);
        return playerMapper.toDTO(savedPlayer);


    }

    public List<PlayerDTO> getAllPlayersInRoom(Long roomId) {
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room not found"));

        return playerRepository.findByRoom(room)
                .stream()
                .map(playerMapper::toDTO)
                .collect(Collectors.toList());


    }
}
