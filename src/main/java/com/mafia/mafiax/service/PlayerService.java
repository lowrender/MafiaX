package com.mafia.mafiax.service;

import com.mafia.mafiax.dto.PlayerDTO;
import com.mafia.mafiax.entity.Player;
import com.mafia.mafiax.entity.Room;
import com.mafia.mafiax.entity.Users;
import com.mafia.mafiax.exceptions.RoomNotFoundException;
import com.mafia.mafiax.exceptions.UserNotFoundException;
import com.mafia.mafiax.mapper.PlayerMapper;
import com.mafia.mafiax.repository.PlayerRepository;
import com.mafia.mafiax.repository.RoomRepository;
import com.mafia.mafiax.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class PlayerService {

    private final RoomRepository roomRepository;
    private final UserRepository userRepository;
    private final PlayerRepository playerRepository;
    private final PlayerMapper playerMapper;

    public PlayerDTO joinRoom(Long userId, Long roomId, int seatIndex) {
        Users user = userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFoundException("User not found"));
        Room room = roomRepository.findById(roomId)
                .orElseThrow(() -> new RoomNotFoundException("Room not found"));

        Player player = Player.builder()
                .user(user)
                .room(room)
                .seatIndex(seatIndex)
                .isAlive(true)
                .isConnected(true)
                .build();

        return playerMapper.toDTO(playerRepository.save(player));
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
